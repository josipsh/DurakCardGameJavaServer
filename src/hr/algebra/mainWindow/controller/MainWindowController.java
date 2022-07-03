package hr.algebra.mainWindow.controller;


import hr.algebra.chatServer.MessageSender;
import hr.algebra.chatServer.RmiChatServer;
import hr.algebra.mainWindow.model.Context;
import hr.algebra.sharedModel.ChatServerSettings;
import hr.algebra.utils.AlertUtil;
import hr.algebra.utils.file.XmlUtils;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable, Context {
    private final String TIME_FORMAT = "HH:mm:ss";
    private static final String MESSAGE_FORMAT = "%s (%s): %s";

    @FXML
    public ListView<Label> chatLogListView;
    @FXML
    public ListView<Label> gameLogListView;
    @FXML
    public Button settingsBtn;

    private final ObservableList<Label> chatLogHolder = FXCollections.observableArrayList();
    private final ObservableList<Label> gameLogHolder = FXCollections.observableArrayList();

    private MessageSender messageSender;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initListViews();
        startChatServer();
    }

    private void startChatServer() {
        messageSender = new RmiChatServer(this);
//
//        Thread thread = new Thread(() -> {
//            int counter = 0;
//            while (true) {
//                messageSender.sendMessage(new MessageDto(
//                    new UserDto(1, "Marko", "Maric", "Marica"),
//                        "This is message " + counter,
//                        false
//                ));
//                counter++;
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    Platform.runLater(() -> postChatLogMessage("ERROR", e.getMessage()));
//                }
//            }
//        });
//
//        thread.setDaemon(true);
//        thread.start();
    }

    private void initListViews() {
        Bindings.bindContentBidirectional(chatLogHolder, chatLogListView.getItems());
        Bindings.bindContentBidirectional(gameLogHolder, gameLogListView.getItems());
    }

    @Override
    public void postChatLogMessage(String severity, String logMessage) {
        Platform.runLater(() -> chatLogHolder.add(new Label(
                String.format(
                        MESSAGE_FORMAT,
                        LocalTime.now().format(DateTimeFormatter.ofPattern(TIME_FORMAT)),
                        severity,
                        logMessage)
        )));
    }

    @Override
    public void postGameLogMessage(String severity, String logMessage) {
        gameLogHolder.add(new Label(
                String.format(
                        MESSAGE_FORMAT,
                        LocalTime.now().format(DateTimeFormatter.ofPattern(TIME_FORMAT)),
                        severity,
                        logMessage)
        ));
    }

    @Override
    public ChatServerSettings getChatSettings() {
        return XmlUtils.loadRmiSetting();
    }
}
