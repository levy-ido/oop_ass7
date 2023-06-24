import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HypernymMap {
    private final String lemma;
    private final Map<String, Integer> map;
    public HypernymMap(String lemma) {
        this.lemma = lemma;
        this.map = new HashMap<>();
    }
    private void update(Matcher matcher) {
        while (matcher.find()) {
            if (matcher.hyponyms().contains(lemma)) {
                String hypernym = matcher.hypernym();
                map.put(hypernym, map.getOrDefault(hypernym, 0) + 1);
            }
        }
    }
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
