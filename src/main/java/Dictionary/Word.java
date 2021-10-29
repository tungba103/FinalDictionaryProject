package Dictionary;

public class Word {

  private String word_target;
  private Explain word_explain;
  private String phonetics;

  public Word() {
    this.word_explain = new Explain();
  }

  public Word(String word, String explain) {
    this.word_explain = new Explain();
    addWordExplain(explain);
    this.word_target = word;
    this.phonetics = "/null/";
  }

  public Word(String word, String explain, String phonetics) {
    this.word_explain = new Explain();
    addWordExplain(explain);
    this.word_target = word;
    if (phonetics == null) this.phonetics = "/null/";
    else this.phonetics = phonetics;
  }

  public Word(String word_target, Explain word_explain, String phonetics) {
    this.word_target = word_target;
    this.word_explain = word_explain;
    this.phonetics = phonetics;
  }

  public String getWordTarget() {
    return word_target;
  }

  public void setWordTarget(String word_target) {
    this.word_target = word_target;
  }

  public String getWordExplain() {
    return word_explain + "";
  }

  public void addWordExplain(String word_explain) {
    this.word_explain.Add(word_explain);
  }

  public String getPhonetics() {
    return this.phonetics;
  }

  public void setPhonetics(String phonetics) {
    this.phonetics = phonetics;
  }

  public String Display() {
    return "@" + this.getWordTarget()
      + " " + this.getPhonetics()
      + "\n" + this.getWordExplain();
  }
}