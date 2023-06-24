import java.util.ArrayList;
import java.util.List;
/**
 * This class behaves similarly to Type3, only it tries to concatenate multiple matches instead of just one.
 */
public class Repeating implements Matcher {
    private final String regex;
    private String substring;
    private Type3 type3;
    private final int start;
    private int end;
    private final List<String> hyponyms;
    /**
     * Constructs a new Repeating with the given regular expression, substring and start index.
     * @param regex (String) A regular expression to find matches with.
     * @param substring (String) The substring of the original string starting at the index denoted by start.
     * @param start (int) The previous match's end index.
     */
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
