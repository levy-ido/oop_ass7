import java.util.List;
public class Type2 extends Prototype {
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
