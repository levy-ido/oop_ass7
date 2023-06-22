public class Matcher2 extends MatcherTemplate implements Matcher {
    public Matcher2(String regex, String input) {
        super(regex, input);
    }

    @Override
    public String hypernym() {
        return super.getDelegate().group(2);
    }

    @Override
    public String hyponym() {
        return super.getDelegate().group(1);
    }
}
