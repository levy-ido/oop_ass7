import java.util.List;
/**
 * This class is used for matching with fifth Hearst pattern.
 */
public class Type2 extends Prototype {
    /**
     * Constructs a new Type2 with the given regular expression and input string.
     * @param regex (String) A regular expression to find matches with.
     * @param input (String) The input string to search for matches.
     */
    public Type2(String regex, String input) {
        super(regex, input);
    }
    @Override
    public List<String> hyponyms() {
        return List.of(group(1));
    }
    @Override
    public String hypernym() {
        return group(2);
    }
}
