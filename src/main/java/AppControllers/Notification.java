package AppControllers;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Notification {

  public static void acceptAction() {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setContentText("Success: Everything is great!");
    alert.setHeaderText(null);
    alert.showAndWait();
  }

  public static void errorAction(String message) {
    Alert alert = new Alert(AlertType.WARNING);
    alert.setContentText("Error: " + message);
    alert.setHeaderText(null);
    alert.showAndWait();
  }
}