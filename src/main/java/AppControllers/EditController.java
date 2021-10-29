package AppControllers;

import Dictionary.DictionaryManagement;
import Dictionary.Word;
import java.io.IOException;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class EditController extends ContainerController {

  //  private DictionaryManagement DM = new DictionaryManagement();
  @FXML
  private Button btn_nav_add = new Button();
  @FXML
  private Button btn_nav_change = new Button();
  @FXML
  private Button btn_nav_delete = new Button();
  @FXML
  private TextField textField_new_word_target = new TextField();
  @FXML
  private TextField textField_new_phonetics = new TextField();
  @FXML
  private TextArea textArea_new_explain = new TextArea();
  @FXML
  private BorderPane main_pane = new BorderPane();

  @FXML
  public void BtnOnFinished(ActionEvent e) {
    DM.addWordToDictionary(
        new Word(textField_new_word_target.getText().toLowerCase(), textArea_new_explain.getText(),
            textField_new_phonetics.getText()));
    DM.exportToFile();
  }

  @FXML
  private TextField textField_changed_word_target = new TextField();
  @FXML
  private TextArea textArea_changed_explain = new TextArea();
  @FXML
  private TextField textField_changed_word_phonetics = new TextField();
  @FXML
  private ListView<String> list_view_changed = new ListView<>();

  @FXML
  public void BtnOnChanged(ActionEvent e) {
    DM.updateToDictionary(
        new Word(textField_changed_word_target.getText(), textArea_changed_explain.getText(),
            textField_changed_word_phonetics.getText()));
    DM.exportToFile();
  }
  @FXML
  public void handleSearchChanged(ActionEvent e) {
    Set<String> set = DM.dictionarySearchPattern(
        textField_changed_word_target.getText().toLowerCase());
    ObservableList<String> items = FXCollections.observableArrayList(set);
    list_view_changed.setItems(items);
  }

  @FXML
  public void handleListViewChanged(MouseEvent e) {
    String spelling = list_view_changed.getSelectionModel().getSelectedItem();
    if (spelling == null) {
      return;
    }
    Word word = DM.dictionaryLookup(spelling);
    textField_changed_word_target.setText(word.getWordTarget());
    textArea_changed_explain.setText(word.getWordExplain());
    textField_changed_word_phonetics.setText(word.getPhonetics());
  }

  @FXML
  private TextField textField_deleted_word_target = new TextField();
  @FXML
  private ListView<String> list_view = new ListView();

  @FXML
  public void BtnOnDeleted(ActionEvent e) {
    DM.deleteWordToDictionary(textField_deleted_word_target.getText());
    DM.exportToFile();
  }

  @FXML
  public void handleSearch(ActionEvent e) {
    Set<String> set = DM.dictionarySearchPattern(
        textField_deleted_word_target.getText().toLowerCase());
    ObservableList<String> items = FXCollections.observableArrayList(set);
    list_view.setItems(items);
  }

  @FXML
  public void handleListView(MouseEvent e) {
    String spelling = list_view.getSelectionModel().getSelectedItem();
    if (spelling == null) {
      return;
    }
    Word word = DM.dictionaryLookup(spelling);
    textField_deleted_word_target.setText(word.getWordTarget());
  }

  public void HandlePane(String NamePane) throws IOException {
    FxmlLoader object = new FxmlLoader();
    Pane view = object.getPane(NamePane);
    main_pane.setCenter(view);
  }

  public void HandleAddingWord(ActionEvent e) throws IOException {
    HandlePane("edit_add_pane");
  }

  public void HandleDeletingWord(ActionEvent e) throws IOException {
    HandlePane("edit_delete_pane");
  }

  public void HandleChangingWord(ActionEvent e) throws IOException {
    HandlePane("edit_change_pane");
  }
}
