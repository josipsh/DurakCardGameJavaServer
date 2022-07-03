package hr.algebra.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;

import java.util.Objects;

public class AlertUtil {
    public static void showInformationAlert(String title, String headerText, String contextText) {
        try {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(title);
            alert.setHeaderText(headerText);
            alert.setContentText(contextText);

            alert.showAndWait();
        } catch (Exception e) {
            showErrorAlert("Error occurred", "Unable to show information dialog", e.getMessage());
        }
    }

    public static void showErrorAlert(String title, String headerText, String contextText) {
        try {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(title);
            alert.setHeaderText(headerText);
            alert.setContentText(contextText);

            alert.showAndWait();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error occurred");
            alert.setHeaderText("Unable to show error dialog using custom style!");
            alert.setContentText(e.getStackTrace().toString());
            alert.showAndWait();
        }
    }
}
