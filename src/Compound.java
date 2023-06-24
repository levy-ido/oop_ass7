import java.util.ArrayList;
import java.util.List;
public class Compound implements Matcher {
    private final String input;
    private final Type1 type1;
    private final String repeating;
    private final String additional;
    private List<String> hyponyms;
    private int end;
    public Compound(String regex, String input, String repeating, String additional) {
        this.input = input;
        type1 = new Type1(regex, input);
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
            Type3 additional = new Type3(this.additional, input.substring(end), end);
            if (additional.find()) {
                hyponyms.addAll(additional.hyponyms());
                end = additional.end();
            }
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
