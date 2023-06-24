import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * .
 */
public class DiscoverHypernym {
    private static boolean count(File[] files, Updater updater) {
        for (File file : files) {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file.getPath()))) {
                String paragraph;
                while ((paragraph = bufferedReader.readLine()) != null) {
                    if (!paragraph.contains(updater.getLemma())) {
                        continue;
                    }
                    for (Matcher matcher : Matchers.create(paragraph)) {
                        updater.count(matcher);
                    }
                }
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }
    /**
     * .
     * @param args .
     */
    public static void main(String[] args) {
        File[] files = new File(args[0]).listFiles();
        if (files == null) {
            System.out.println("The provided argument does not denote a directory.");
            return;
        }
        Map<String, Integer> hypernyms = new HashMap<>();
        Updater updater = new Updater(hypernyms, args[1]);
        if (!count(files, updater)) {
            System.out.println("An error has occured while counting. Eze basa.");
        }
        List<Map.Entry<String, Integer>> mappings = new ArrayList<>(hypernyms.entrySet());
        Comparator<Map.Entry<String, Integer>> cmp = Map.Entry.comparingByValue();
        mappings.sort(cmp.reversed());
        for (Map.Entry<String, Integer> e : mappings) {
            System.out.println(e.getKey() + ": " + e.getValue());
        }
    }
}
