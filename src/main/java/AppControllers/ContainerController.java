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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class ContainerController {

  protected DictionaryManagement DM = new DictionaryManagement();
  @FXML
  private BorderPane main_pane = new BorderPane();
  @FXML
  private Button btn_nav_search_off = new Button();
  @FXML
  private Button btn_nav_search_onl = new Button();
  @FXML
  private Button btn_nav_search_para = new Button();
  @FXML
  private Button btn_nav_edit = new Button();


  public void HandlePane(String NamePane) throws IOException {
    FxmlLoader object = new FxmlLoader();
    Pane view = object.getPane(NamePane);
    main_pane.setCenter(view);
  }

  @FXML
  public void handleClickSidebar(ActionEvent event) throws IOException {
    if (event.getSource() == btn_nav_search_onl) {
      HandlePane("search_online_pane");
    } else if (event.getSource() == btn_nav_search_off) {
      HandlePane("search_offline_pane");
    } else if (event.getSource() == btn_nav_search_para) {
      HandlePane("search_para_pane");
    } else if (event.getSource() == btn_nav_edit) {
      HandlePane("edit_pane");
    }
  }


  @FXML
  private TextField input_search = new TextField();
  @FXML
  private Button btn_audio = new Button();
  @FXML
  private ListView<String> search_list_view = new ListView();
  @FXML
  private Text word_explain = new Text();

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
  }

}
