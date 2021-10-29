package AppControllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import project.dictionaryproject.DictionaryApplication;

public class FxmlLoader {

  private Pane view;

  public Pane getPane(String fileName) throws IOException {
    try {
      URL fileUrl = DictionaryApplication.class.getResource("Interface/" + fileName + ".fxml");
      if (fileUrl == null) {
        throw new java.io.FileNotFoundException("FXML file can't be found");
      }
      view = new FXMLLoader().load(fileUrl);
    } catch (FileNotFoundException e) {
      System.out.println("No page" + fileName + " please check FxmlLoader");
    }
    return view;
  }
}
