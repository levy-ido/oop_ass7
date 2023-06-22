import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * .
 */
public class DiscoverHypernym {
    private static Pattern[] createPatterns() {
        String np = "<np>([^>]*)</np>";
        String optionalComma = "(?: ,)? ";
        Pattern pattern1 = Pattern.compile(np + optionalComma + "such as " + np + " ");
        Pattern pattern2 = Pattern.compile("such " + np + " as " + np + " ");
        Pattern pattern3 = Pattern.compile(np + optionalComma + "including " + np + " ");
        Pattern pattern4 = Pattern.compile(np + optionalComma + "especially " + np + " ");
        Pattern additionalNps = Pattern.compile(", <np>([^>]*)</np>");
        Pattern lastNp = Pattern.compile(", (and|or) <np>([^>]*)</np>");
        String pattern5Opt = "(?:(?:an example|a kind|a class)? of)? ";
        Pattern pattern5 = Pattern.compile(np + optionalComma + "which is " + pattern5Opt + np);
        Pattern[] patterns = {pattern1, pattern2, pattern3, pattern4, additionalNps, lastNp, pattern5};
    }
    /**
     * .
     * @param args .
     */
    public static void main(String[] args) {
        File folder = new File(args[0]);
        Map<String, Integer> hypernyms = new HashMap<>();
        for (File file : Objects.requireNonNull(folder.listFiles())) {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file.getPath()))) {
                String paragraph;
                while ((paragraph = bufferedReader.readLine()) != null) {
                    // Update the map according to this paragraph, every hearst pattern, and the lemma
                    for (Pattern pattern : patterns) {
                        Matcher patM = pattern.matcher(paragraph);
                        while (patM.find()) {
                            String hypernym = patM.group(1);
                            if (patM.group(2).equals(args[1])) {
                                hypernyms.put(hypernym, hypernyms.getOrDefault(hypernym, 0) + 1);
                            }
                            int patMEnd = patM.end();
                            Matcher addM = additionalNps.matcher(paragraph.substring(patMEnd));
                            int addMEnd = 0;
                            while (addM.find()) {
                                addMEnd = addM.end();
                                if (patM.group(1).equals(args[1])) {
                                    hypernyms.put(hypernym, hypernyms.getOrDefault(hypernym, 0) + 1);
                                }
                            }
                            Matcher lastM = lastNp.matcher(paragraph.substring(patMEnd + addMEnd));
                            if (lastM.find()) {
                                if (patM.group(1).equals(args[1])) {
                                    hypernyms.put(hypernym, hypernyms.getOrDefault(hypernym, 0) + 1);
                                }
                            }
                        }
                    }
                    Matcher patM = pattern5.matcher(paragraph);
                    while (patM.find()) {
                        String hypernym = patM.group(2);
                        if (patM.group(1).equals(args[1])) {
                            hypernyms.put(hypernym, hypernyms.getOrDefault(hypernym, 0) + 1);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        Set<Map.Entry<String, Integer>> entrySet = hypernyms.entrySet();
        List<Map.Entry<String, Integer>> list = new ArrayList<>(entrySet);
        Comparator<Map.Entry<String, Integer>> cmp = (e1, e2) -> e1.getValue().compareTo(e2.getValue());
        list.sort(cmp.reversed());
        for (Map.Entry<String, Integer> e : list) {
            System.out.println(e.getKey() + ": " + e.getValue());
        }
    }
}
