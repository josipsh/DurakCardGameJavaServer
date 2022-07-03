package hr.algebra.sharedModel;

import java.util.ArrayList;
import java.util.List;

public class ChatServerSettings {
//    private static final String RMI_CLIENT = "client";
//    private static final String RMI_SERVER = "server";
//    private static final int REMOTE_PORT = 1099;
//    private static final int RANDOM_PORT_HINT = 0;

    private List<String> clients;
    private String serverName;
    private int remotePort;
    private int randomPort;

    public ChatServerSettings(List<String> clients, String serverName, int remotePort, int randomPort) {
        this.clients = clients;
        this.serverName = serverName;
        this.remotePort = remotePort;
        this.randomPort = randomPort;
    }

    public List<String> getClients() {
        return clients;
    }

    public void setClients(List<String> clients) {
        this.clients = clients;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public int getRemotePort() {
        return remotePort;
    }

    public void setRemotePort(int remotePort) {
        this.remotePort = remotePort;
    }

    public int getRandomPort() {
        return randomPort;
    }

    public void setRandomPort(int randomPort) {
        this.randomPort = randomPort;
    }
}
