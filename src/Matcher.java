import java.util.List;

public interface Matcher {
    boolean find();
    public int start();
    public int end();
    public List<String> hyponyms();
    public String hypernym();
}
