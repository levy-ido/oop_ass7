import java.util.List;
public class Type1 extends Prototype {
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
