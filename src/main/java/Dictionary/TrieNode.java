package Dictionary;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class TrieNode {
  private TrieNode head;
  private String word;
  private boolean isWord = false;
  private Map<Character, TrieNode> map = new HashMap<>();
  private static Set<String> result;

  public TrieNode() {
  }

  public TrieNode(TrieNode head) {
    this.head = head;
  }

  public void insert(String word) {
    TrieNode curr = head;
    for (char c : word.toCharArray()) {
      curr.map.putIfAbsent(c, new TrieNode(head));
      curr = curr.map.get(c);
    }

    curr.isWord = true;
    curr.word = word;
  }

  private boolean delete(TrieNode curr, String word, int index) {
    if (index == word.length()) {
      curr.isWord = false;
      curr.word = null;
      return curr.map.isEmpty();
    }
    char ch = word.charAt(index);
    TrieNode child = curr.map.get(ch);
    boolean shouldDeleteCurr = delete(child, word, index + 1) && !child.isWord;
    if (shouldDeleteCurr) {
      curr.map.remove(ch);
      return curr.map.isEmpty();
    }
    return false;
  }

  public void delete(String word) {
    delete(head, word, 0);
  }

  private void printAllWords(TrieNode root) {
    if (root == null) {
      return;
    }
    if (root.isWord) {
      result.add(root.word);
    }
    for (var pair : root.map.entrySet()) {
      TrieNode child = pair.getValue();
      printAllWords(child);
    }
  }

  public void buildTrie(Set<String> dictionary) {
    head = this;
    for (String s : dictionary) insert(s);
  }

  public Set<String> findAllWords(String pattern) {
    TrieNode cur = head;
    result = new TreeSet<>();
    for (char c : pattern.toCharArray()) {
      cur = cur.map.get(c);
      if (cur == null) return result;
    }
    printAllWords(cur);
    return result;
  }
}