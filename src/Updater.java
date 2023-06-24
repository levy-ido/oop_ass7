import java.util.Map;
public class Updater {
    private final Map<String, Integer> hypernyms;
    private final String lemma;
    public Updater(Map<String, Integer> hypernyms, String lemma) {
        this.hypernyms = hypernyms;
        this.lemma = lemma;
    }
    public void count(Matcher matcher) {
        while (matcher.find()) {
            if (matcher.hyponyms().contains(lemma)) {
                String hypernym = matcher.hypernym();
                hypernyms.put(hypernym, hypernyms.getOrDefault(hypernym, 0) + 1);
            }
        }
    }
    public String getLemma() {
        return lemma;
    }
}
