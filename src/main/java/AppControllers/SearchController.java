package AppControllers;

import Dictionary.*;
import java.io.IOException;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;


public class SearchController extends ContainerController{
  private MysqlManagagement Mysql = new MysqlManagagement();

//  private DictionaryManagement DM = new DictionaryManagement();
  @FXML
  private TextField input_search = new TextField();
  @FXML
  private Button btn_audio = new Button();
  @FXML
  private ListView<String> search_list_view = new ListView();
  @FXML
  private Text word_explain = new Text();
  @FXML
  private Text word_synonyms = new Text();
  @FXML
  private Text word_antonyms = new Text();
  @FXML
  public void HandleBtnVoice(ActionEvent e) {
    VoiceController voice = new VoiceController();
    voice.speakEnglish(input_search.getText().toLowerCase());
  }
  @FXML
  public void searchBtnHandler(ActionEvent e) throws IOException {

    Word w = DM.dictionaryLookup(input_search.getText().toLowerCase());
    if (input_search.getText() == "") {
      word_explain.setText("This left pane includes all words in Dictionary!");
      Set<String> set = DM.dictionarySearchPattern("");
      ObservableList<String> items = FXCollections.observableArrayList(set);
      search_list_view.setItems(items);
    } else if (w == null) {
      word_explain.setText("This word is not existed");
    } else {
      word_explain.setText(w.Display());
      word_synonyms.setText(Mysql.getSynonym(w.getWordTarget()));
      word_antonyms.setText(Mysql.getAntonym(w.getWordTarget()));
      System.out.println(word_synonyms.getText());
      System.out.println(word_antonyms.getText());
      Set<String> set = DM.dictionarySearchPattern(input_search.getText().toLowerCase());
      ObservableList<String> items = FXCollections.observableArrayList(set);
      search_list_view.setItems(items);
    }

  }

//  @FXML
//  public void handleSubSearcher (ActionEvent e) {
//    DictionaryManagement DM = new DictionaryManagement();
//
//  }

  @FXML
  public void handleSelectItemListView(MouseEvent event) {
    String spelling = search_list_view.getSelectionModel().getSelectedItem();
    if (spelling == null) {
      return;
    }
    Word word = DM.dictionaryLookup(spelling);
    word_explain.setText(word.Display());
    word_synonyms.setText(Mysql.getSynonym(word.getWordTarget()));
    word_antonyms.setText(Mysql.getAntonym(word.getWordTarget()));
  }

}

