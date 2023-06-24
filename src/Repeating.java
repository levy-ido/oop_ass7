import java.util.ArrayList;
import java.util.List;
public class Repeating implements Matcher {
    private final String regex;
    private String substring;
    private Type3 type3;
    private final int start;
    private int end;
    private final List<String> hyponyms;
    public Repeating(String regex, String substring, int start) {
        this.regex = regex;
        this.substring = substring;
        this.type3 = new Type3(regex, substring, start);
        this.start = start;
        hyponyms = new ArrayList<>();
    }
    @Override
    public boolean find() {
        while (type3.find()) {
            hyponyms.addAll(type3.hyponyms());
            end = type3.end();
            try {
                substring = substring.substring(end - type3.start());
            } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                break;
            }
            type3 = new Type3(regex, substring, end);
        }
        return !hyponyms.isEmpty();
    }
    @Override
    public int end() {
        return end;
    }
    @Override
    public int start() {
        return start;
    }
    @Override
    public List<String> hyponyms() {
        return hyponyms;
    }
    @Override
    public String hypernym() {
        return null;
    }
}
