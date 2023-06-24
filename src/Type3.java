import java.util.List;
public class Type3 extends Prototype {
    private final int start;
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
