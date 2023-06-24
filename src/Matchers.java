import java.util.ArrayList;
import java.util.List;

public class Matchers {
    private static final String NP = "<np>([^<]*)</np>";
    private static final String OPT_COMMA = "(?: | , )";
    private static final String ONE = NP + OPT_COMMA + "such as " + NP + " ";
    private static final String TWO = "such " + NP + " as " + NP + " ";
    private static final String THREE = NP + OPT_COMMA + "including " + NP + " ";
    private static final String FOUR = NP + OPT_COMMA + "especially " + NP + " ";
    private static final String FIVE = NP + OPT_COMMA + "which is (?:(?:an example|a kind|a class )?of)? " + NP;
    private static final String REP = ", " + NP;
    private static final String ADD = ", and|or " + NP;
    public static List<Matcher> create(String paragraph) {
        List<Matcher> matchers = new ArrayList<>();
        matchers.add(new Compound(ONE, paragraph, REP, ADD));
        matchers.add(new Compound(TWO, paragraph, REP, ADD));
        matchers.add(new Compound(THREE, paragraph, REP, ADD));
        matchers.add(new Compound(FOUR, paragraph, REP, ADD));
        matchers.add(new Type2(FIVE, paragraph));
        return matchers;
    }
}
