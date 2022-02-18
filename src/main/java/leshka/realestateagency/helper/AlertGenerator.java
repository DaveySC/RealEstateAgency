package leshka.realestateagency.helper;

import javafx.scene.control.Alert;

public class AlertGenerator {

    private static Alert alert = new Alert(null);

    public static void showAlert( String contentText, Alert.AlertType alertType, String headerText) {
        alert.setAlertType(alertType);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }


}
