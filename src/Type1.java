import java.util.List;
/**
 * This class is used for matching with the first four Hearst patterns.
 */
public class Type1 extends Prototype {
    /**
     * Constructs a new Type1 with the given regular expression and input string.
     * @param regex (String) A regular expression to find matches with.
     * @param input (String) The input string to search for matches.
     */
    public Type1(String regex, String input) {
        super(regex, input);
    }
    @Override
    public List<String> hyponyms() {
        return List.of(group(2));
    }
    @Override
    public String hypernym() {
        return group(1);
    }
}
