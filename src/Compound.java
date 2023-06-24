import java.util.ArrayList;
import java.util.List;
/**
 * This class is used for identifying the first four Hearst patterns. To that end, it combines Type1, Repeating and
 * Type3 to create a composition of regular expressions not achievable by regular means. First, it tries to match with
 * Type1 once, then with Repeating as much as possible and finally attempts to match with Type3 once.
 */
public class Compound implements Matcher {
    private final Type1 type1;
    private final String input;
    private final String repeating;
    private final String additional;
    private List<String> hyponyms;
    private int end;
    /**
     * Constructs a new Compound with the given regular expression, input string, repeating regular expression and an
     * additional regular expression.
     * @param regex (String) A regular expression to find matches with.
     * @param input (String) The input string to search for matches.
     * @param repeating (String) A regular expression that will be used to create a Repeating.
     * @param additional (String) A regular expression that will be used to create a Type3.
     */
    public Compound(String regex, String input, String repeating, String additional) {
        type1 = new Type1(regex, input);
        this.input = input;
        this.repeating = repeating;
        this.additional = additional;
    }
    @Override
    public boolean find() {
        hyponyms = new ArrayList<>();
        if (!type1.find()) {
            return false;
        }
        hyponyms.addAll(type1.hyponyms());
        end = type1.end();
        Repeating repeating = new Repeating(this.repeating, input.substring(end), end);
        if (repeating.find()) {
            hyponyms.addAll(repeating.hyponyms());
            end = repeating.end();
        }
        Type3 additional = new Type3(this.additional, input.substring(end), end);
        if (additional.find()) {
            hyponyms.addAll(additional.hyponyms());
            end = additional.end();
        }
        return true;
    }
    @Override
    public String hypernym() {
        return type1.hypernym();
    }
    @Override
    public List<String> hyponyms() {
        return hyponyms;
    }
    @Override
    public int start() {
        return type1.start();
    }
    @Override
    public int end() {
        return end;
    }
}