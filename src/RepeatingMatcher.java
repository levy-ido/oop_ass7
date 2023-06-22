import java.util.ArrayList;
import java.util.List;

public class RepeatingMatcher extends MatcherTemplate implements Matcher {
    private final List<String> collection;
    private int end;
    public RepeatingMatcher(String regex, String input) {
        super(regex, input);
        collection = new ArrayList<>();
    }
    @Override
    public boolean find() {
        while (super.find()) {
            if (super.start() != end + 1) {
                break;
            }
            collection.add(hyponym());
            end = super.end();
        }
        return end != 0;
    }

    @Override
    public String hypernym() {
        return null;
    }

    @Override
    public String hyponym() {
        return super.getDelegate().group(1);
    }
}
