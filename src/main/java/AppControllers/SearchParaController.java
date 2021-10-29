package AppControllers;

import Dictionary.GoogleApiTranslate;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

public class SearchParaController {

  private ObservableList<String> LanguagesList = FXCollections.observableArrayList("Language","Vietnamese",
      "English","Chinese","Korean","Japanese","Hindi","Spanish","French");
  private String[] LangList = {"","vi","en","zh","ko","ja","hi","es","fr"};
  private GoogleApiTranslate ggApi = new GoogleApiTranslate();
  @FXML
  private TextArea para_target = new TextArea();
  @FXML
  private TextArea para_explain = new TextArea();
  @FXML
  private Button btn_translate = new Button();
  @FXML
  private ChoiceBox<String> choice_lang1 = new ChoiceBox<>();
  @FXML
  private ChoiceBox<String> choice_lang2 = new ChoiceBox<>();

  @FXML
  private void initialize() {
    choice_lang1.setValue("Language");
    choice_lang2.setValue("Language");
    choice_lang1.setItems(LanguagesList);
    choice_lang2.setItems(LanguagesList);
  }

  @FXML
  public void HandleBtnTranslate() throws IOException {
    String langFrom = LangList[choice_lang1.getSelectionModel().getSelectedIndex()];
    String langTo = LangList[choice_lang2.getSelectionModel().getSelectedIndex()];
    String res = ggApi.translate(langFrom, langTo, para_target.getText());
    para_explain.setText(res);
    System.out.println("Translate complete");
  }
  @FXML
  public void HandleBtnCopy() {
    final Clipboard clipboard = Clipboard.getSystemClipboard();
    final ClipboardContent content = new ClipboardContent();
    content.putString(para_explain.getText());
    clipboard.setContent(content);
    System.out.println("Copy to clipboard system success");
    return;
  }
}
