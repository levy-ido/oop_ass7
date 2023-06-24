import java.util.List;
/**
 * This class assumes a previous match with an instance of Type1 or Type2, and tries to find a match starting at the
 * previous match's end index.
 */
public class Type3 extends Prototype {
    private final int start;
    /**
     * Constructs a new Type3 with the given regular expression, the previous match's end index and the substring of the
     * original string starting at that index.
     * @param regex (String) A regular expression to find matches with.
     * @param substring (String) The substring of the original string starting at the index denoted by start.
     * @param start (int) The previous match's end index.
     */
    public Type3(String regex, String substring, int start) {
        super(regex, substring);
        this.start = start;
    }
    @Override
    public boolean find() {
        if (!super.find()) {
            return false;
        }
        return super.start() == 0;
    }
    @Override
    public int start() {
        return start;
    }
    @Override
    public int end() {
        return start + super.end();
    }
    @Override
    public List<String> hyponyms() {
        return List.of(group(1));
    }
    @Override
    public String hypernym() {
        return null;
    }
}
