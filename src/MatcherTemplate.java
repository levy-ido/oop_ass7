import java.util.regex.Pattern;

public abstract class MatcherTemplate implements Matcher {
    private final java.util.regex.Matcher delegate;
    public MatcherTemplate(String regex, String input) {
        Pattern pattern = Pattern.compile(regex);
        delegate = pattern.matcher(input);
    }
    @Override
    public boolean find() {
        return delegate.find();
    }
    public java.util.regex.Matcher getDelegate() {
        return delegate;
    }
    @Override
    public int start() {
        return delegate.start();
    }
    @Override
    public int end() {
        return delegate.end();
    }
}
