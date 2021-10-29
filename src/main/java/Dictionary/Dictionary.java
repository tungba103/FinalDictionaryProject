package Dictionary;

import java.util.Map;
import java.util.TreeMap;

public class Dictionary {
  private Map<String, Word> dictionary = new TreeMap<>();

  public Dictionary() {
  }

  public Dictionary(Map<String, Word> dictionary) {
    this.dictionary = dictionary;
  }

  public Map<String, Word> getDictionary() {
    return this.dictionary;
  }

  public void setDictionary(Map<String, Word> dictionary) {
    this.dictionary = dictionary;
  }

  public void addWord(Word word) {
    this.dictionary.putIfAbsent(word.getWordTarget(), word);
  }

  public void deleteWord(String word) {
    this.dictionary.remove(word);
  }

  public void updateWord(Word word) {
    this.dictionary.put(word.getWordTarget(), word);
  }

}