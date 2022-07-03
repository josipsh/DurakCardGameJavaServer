package hr.algebra.mainWindow.model;

import hr.algebra.sharedModel.ChatServerSettings;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;

public interface Context {
    void postChatLogMessage(String severity, String logMessage);
    void postGameLogMessage(String severity, String logMessage);

    ChatServerSettings getChatSettings();
}
