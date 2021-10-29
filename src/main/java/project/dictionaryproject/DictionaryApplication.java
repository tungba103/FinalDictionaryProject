package project.dictionaryproject;

import static jdk.xml.internal.SecuritySupport.getResourceAsStream;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class DictionaryApplication extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader();
    fxmlLoader.setLocation(getClass().getResource("Interface/container.fxml"));
    Scene scene = new Scene(fxmlLoader.load(),1080,720);
    primaryStage.setTitle("Dictionary");
    primaryStage.setResizable(false);

//    primaryStage.getIcons().add(new Image("src/main/resources/project/dictionaryproject/data/image/j.png"));

    primaryStage.setScene(scene);
    primaryStage.show();
  }
}

