import java.util.List;
/**
 * Inspired by java.util.regex.Matcher, classes implementing this interface can search input strings for matches using
 * regular expression. More specifically, this interface was designed for identifying Hearst patterns.
 */
public interface Matcher {
    /**
     * Attempts to find the next match in the given input string using the given regular expression. If a match was
     * found, updates the internal state accordingly. Users can then query this internal state with start(), end(),
     * hyponyms(), and hypernym(). Querying the internal state if no match was made makes no sense and behaves
     * unpredictably.
     * @return (boolean) true if and only if a match, as defined by this Matcher, was found.
     */
    boolean find();
    /**
     * Returns the start index of the subsequence matched in the last call to find().
     * @return (int) The start index of the subsequence matched in the last call to find().
     */
    int start();
    /**
     * Returns the index immediately after the end index of the subsequence matched in the last call to find().
     * @return (int) The index immediately after the end index of the subsequence matched in the last call to find().
     */
    int end();
    /**
     * Returns the list of hyponyms captured in the last call to find().
     * @return (List[String]) The list of hyponyms captured in the last call to find().
     */
    List<String> hyponyms();

    /**
     * Returns the hyponym captured in the last call to find().
     * @return (String) The hyponym captured in the last call to find().
     */
    String hypernym();
}
