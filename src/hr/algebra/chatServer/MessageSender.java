package hr.algebra.chatServer;

import hr.algebra.sharedModel.MessageDto;

public interface MessageSender {
    void sendMessage(MessageDto message);
}
