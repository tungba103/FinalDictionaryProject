package Dictionary;

public class Explain {
    private StringBuilder explain;

    public Explain() {
        this.explain = new StringBuilder();
    }

    @Override
    public String toString() {
        return explain + "";
    }

    public void Add(String s) {
        explain.append(s).append("\n");
    }
}
