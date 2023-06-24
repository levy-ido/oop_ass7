import java.util.regex.Pattern;
public abstract class Prototype implements Matcher {
    private final java.util.regex.Matcher delegate;
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
    public String group(int group) {
        return delegate.group(group);
    }
}
