public class Matcher1 extends MatcherTemplate implements Matcher {
    public Matcher1(String regex, String input) {
        super(regex, input);
    }

    @Override
    public String hypernym() {
        return super.getDelegate().group(1);
    }

    @Override
    public String hyponym() {
        return super.getDelegate().group(2);
    }
}
