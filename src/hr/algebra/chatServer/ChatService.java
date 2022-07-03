package hr.algebra.chatServer;

import hr.algebra.sharedModel.MessageDto;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatService extends Remote {
    void sendMessage(MessageDto message) throws RemoteException;
}
