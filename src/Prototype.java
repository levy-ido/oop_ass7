import java.util.regex.Pattern;
/**
 * A prototype for Matchers.
 */
public abstract class Prototype implements Matcher {
    private final java.util.regex.Matcher delegate;
    /**
     * A constructor for extending classes.
     * @param regex (String) A regular expression to find matches with.
     * @param input (String) The input string to search for matches.
     */
    public Prototype(String regex, String input) {
        delegate = Pattern.compile(regex).matcher(input);
    }
    @Override
    public boolean find() {
        return delegate.find();
    }
    @Override
    public int start() {
        return delegate.start();
    }
    @Override
    public int end() {
        return delegate.end();
    }
    /**
     * Returns the string captured by the given capturing group in the last match.
     * @param group (int) An index denoting a capturing group index.
     * @return (String) The string captured by the given capturing group in the last match.
     */
    public String group(int group) {
        return delegate.group(group);
    }
}
