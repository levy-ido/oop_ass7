import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * This class is used for counting how many times an arbitrary hypernym appears as a given lemma's hypernym, and
 * displaying the results in descending order.
 */
public class HypernymMap {
    private final String lemma;
    private final Map<String, Integer> map;
    /**
     * Constructs a new HypernymMap with the given lemma.
     * @param lemma (String) A lemma to count hypernyms for.
     */
    public HypernymMap(String lemma) {
        this.lemma = lemma;
        this.map = new HashMap<>();
    }
    /**
     * Gets a Matcher and updates this HypernymMap's hypernym count accordingly.
     * @param matcher (Matcher) A Matcher to update this HypernymMap's hypernym count with.
     */
    private void update(Matcher matcher) {
        while (matcher.find()) {
            if (matcher.hyponyms().contains(lemma)) {
                String hypernym = matcher.hypernym();
                map.put(hypernym, map.getOrDefault(hypernym, 0) + 1);
            }
        }
    }
    /**
     * Counts this HypernymMap's lemma's hypernyms across the different patterns and all files.
     * @param files (File[]) An array of Files to iterate over.
     * @return (boolean) true if no IOException was raised, false otherwise.
     */
    private boolean count(File[] files) {
        for (File file : files) {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file.getPath()))) {
                String paragraph;
                while ((paragraph = bufferedReader.readLine()) != null) {
                    if (!paragraph.contains(lemma)) {
                        continue;
                    }
                    for (Matcher matcher : Matchers.create(paragraph)) {
                        update(matcher);
                    }
                }
            } catch (IOException ioException) {
                return false;
            }
        }
        return true;
    }
    /**
     * Counts this HypernymMap's lemma's hypernyms, sorts this HypernymMap's entries in descending order, and prints
     * them.
     * @param files (File[]) An array of Files to iterate over.
     * @return (boolean) true if no IOException was raised, false otherwise.
     */
    public boolean print(File[] files) {
        if (!count(files)) {
            return false;
        }
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(map.entrySet());
        Comparator<Map.Entry<String, Integer>> cmp = Map.Entry.comparingByValue();
        entries.sort(cmp.reversed());
        for (Map.Entry<String, Integer> entry : entries) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        return true;
    }
}
