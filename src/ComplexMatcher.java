import java.util.ArrayList;
import java.util.List;

public class ComplexMatcher implements Matcher {
    private List<Matcher> matchers;
    public ComplexMatcher() {
        matchers = new ArrayList<>();
    }
    @Override
    public boolean find() {
        Matcher mandatory = matchers.get(0);
        if (!mandatory.find()) {
            return false;
        }
        int end = mandatory.en
        for (int i = 1; i < matchers.size(); ++i) {
            matchers.get(i).find();
        }
    }
}
