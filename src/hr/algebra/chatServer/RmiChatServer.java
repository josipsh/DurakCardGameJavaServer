package hr.algebra.chatServer;

import hr.algebra.mainWindow.model.Context;
import hr.algebra.sharedModel.ChatServerSettings;
import hr.algebra.sharedModel.MessageDto;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class RmiChatServer implements MessageSender {
    private List<String> clientsName;
    private Map<String, ChatService> clients = new TreeMap<>();
    private ChatService server;
    private Registry registry;

    private final Context context;
    private final ChatServerSettings settings;

    public RmiChatServer(Context context) {
        this.context = context;
        settings = context.getChatSettings();
        clientsName = settings.getClients();

        publishServer();
        waitForClient();
    }

    private void publishServer() {
        server = message -> {
            sendMessage(message);
            context.postChatLogMessage("INFO", "This is message => " + message);
        };

        try {
            registry = LocateRegistry.createRegistry(settings.getRemotePort());
            ChatService stub = (ChatService) UnicastRemoteObject.exportObject(server, settings.getRandomPort());
            registry.rebind(settings.getServerName(), stub);

        } catch (RemoteException e) {
            context.postChatLogMessage("ERROR", e.getMessage());
        }
    }

    private void waitForClient() {
        Thread thread = new Thread(() -> {
            while (true) {
                for (String clientName : settings.getClients()) {
                    try {
                        if (!clients.containsKey(clientName)) {
                            ChatService client = (ChatService) registry.lookup(clientName);
                            clients.put(clientName, client);
                            context.postChatLogMessage("INFO", "Client connected" + clientName);
                        }
                    } catch (RemoteException | NotBoundException e) {
                        context.postChatLogMessage("INFO", "Waiting for client " + clientName);
                    }
                }

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    context.postChatLogMessage("ERROR", e.getMessage());
                }
            }
        });

        thread.setDaemon(true);
        thread.start();
    }

    @Override
    public void sendMessage(MessageDto message) {
        for (String clientName : clientsName) {
            if (clients.containsKey(clientName)) {
                try {
                    clients.get(clientName).sendMessage(message);
                } catch (RemoteException e) {
                    context.postChatLogMessage("ERROR", e.getMessage());
                }
            }
        }
    }
}
