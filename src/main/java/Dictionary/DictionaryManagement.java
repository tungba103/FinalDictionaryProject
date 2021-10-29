package Dictionary;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import java.io.*;
import java.util.Set;


public class DictionaryManagement {

  private Dictionary dictionary;
  private TrieNode trieNode;

  public Dictionary getDictionary() {
    return dictionary;
  }

  public DictionaryManagement() {
    this.dictionary = new Dictionary();
    this.trieNode = new TrieNode();
    this.insertFromFile();
  }

  public TrieNode getTrieNode() {
    return trieNode;
  }

  public void setTrieNode(TrieNode trieNode) {
    this.trieNode = trieNode;
  }

  public Word dictionaryLookup(String word) {
    return this.dictionary.getDictionary().get(word);
  }

  public void insertFromFile() {
    try {
      Path path = Paths.get("src/main/resources/project/dictionaryproject/data/Dictionary.txt");
      List<String> dataList = Files.readAllLines(path);
      ListIterator<String> input = dataList.listIterator();

      Word word = new Word();
      while (input.hasNext()) {
        String p = input.next();

        if (p.startsWith("@")) {
          word = new Word();
          String[] part = p.split(" /", 2);

          String target = part[0].substring(1).trim();
          word.setWordTarget(target);

          if (part.length < 2) {
            word.setPhonetics("/null/");
          } else
            word.setPhonetics("/" + part[1]);
          while (input.hasNext()) {
            String p1 = input.next();

            if (!p1.startsWith("@")) {
              word.addWordExplain(p1);

            } else {
              this.dictionary.addWord(word);
              input.previous();
              break;
            }
          }
        }
      }
      this.dictionary.addWord(word);
      trieNode.buildTrie(this.dictionary.getDictionary().keySet());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public boolean addWordToDictionary(Word word) {
    if (dictionaryLookup(word.getWordTarget()) != null) System.out.println("Your word exists!");
    else {
      this.dictionary.addWord(word);
      trieNode.insert(word.getWordTarget());
      return true;
    }
    return false;
  }

  public void deleteWordToDictionary(String word) {
    if (dictionaryLookup(word) == null) System.out.println("Your word doesn't exists!");
    else {
      this.dictionary.deleteWord(word);
      trieNode.delete(word);
      System.out.println("delete succeed");
    }
  }

  public boolean updateToDictionary(Word word) {
    if (dictionaryLookup(word.getWordTarget()) == null) System.out.println("Your word doesn't exists!");
    else {
      this.dictionary.updateWord(word);
      return true;
    }
    return false;
  }

  public Set<String> dictionarySearchPattern(String pattern) {
    return trieNode.findAllWords(pattern);
  }

  public void exportToFile() {
    PrintWriter pw;
    try {
      pw = new PrintWriter("src/main/resources/project/dictionaryproject/data/Dictionary.txt");
      for (String key : this.dictionary.getDictionary().keySet()) {
        Word word = this.dictionary.getDictionary().get(key);
        pw.printf("%s%s\n", "@" + word.getWordTarget() + " ", word.getPhonetics());
        pw.printf("%s", word.getWordExplain());
      }
      pw.flush();
      System.out.print("Success\n");
    } catch (IOException e) {
      System.err.println(e);
    }
  }

  public static void main(String[] agrs) {
    DictionaryManagement DM = new DictionaryManagement();
    Set<String> s = DM.trieNode.findAllWords("");
  }
}
