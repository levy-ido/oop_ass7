import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * .
 */
public class DiscoverHypernym {
    /**
     * .
     * @param args .
     */
    public static void main(String[] args) {
        File folder = new File(args[0]);
        Map<String, Integer> hypernymMap = new HashMap<>();
        for (File file : folder.listFiles()) {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file.getPath()))) {
                String line;
                String np = "<np>([^>]*)</np>";
                String optionalComma = "(?:, )? ";
                Pattern pattern1 = Pattern.compile(np + optionalComma + "such as " + np + " ");
                Pattern pattern2 = Pattern.compile("such " + np + " as " + np + " ");
                Pattern pattern3 = Pattern.compile(np + optionalComma + "including " + np + " ");
                Pattern pattern4 = Pattern.compile(np + optionalComma + "especially " + np + " ");
                String pattern5String = np + optionalComma + "which is ";
                pattern5String += "(?:(?:an example|a kind|a class)? of)? " + np;
                Pattern pattern5 = Pattern.compile(pattern5String);
                Pattern[] patterns = {pattern1, pattern2, pattern3, pattern4};
                Pattern additionalNps = Pattern.compile(", <np>([^>]*)</np>");
                Pattern lastNp = Pattern.compile(", (and|or) <np>([^>]*)</np>");
                while ((line = bufferedReader.readLine()) != null) {
                    if (line.equals("") || !line.contains(args[1])) {
                        continue;
                    }
                    for (Pattern p : patterns) {
                        Matcher patM = p.matcher(line);
                        while (patM.find()) {
                            String hypernym = patM.group(1);
                            if (patM.group(2).equals(args[1])) {
                                if (hypernymMap.containsKey(hypernym)) {
                                    hypernymMap.put(hypernym, hypernymMap.get(hypernym) + 1);
                                } else {
                                    hypernymMap.put(hypernym, 1);
                                }
                            }
                            int patMEnd = patM.end();
                            Matcher addM = additionalNps.matcher(line.substring(patMEnd));
                            int addMEnd = 0;
                            while (addM.find()) {
                                addMEnd = addM.end();
                                if (patM.group(1).equals(args[1])) {
                                    if (hypernymMap.containsKey(hypernym)) {
                                        hypernymMap.put(hypernym, hypernymMap.get(hypernym) + 1);
                                    } else {
                                        hypernymMap.put(hypernym, 1);
                                    }
                                }
                            }
                            Matcher lastM = lastNp.matcher(line.substring(patMEnd + addMEnd));
                            if (lastM.find()) {
                                if (patM.group(1).equals(args[1])) {
                                    if (hypernymMap.containsKey(hypernym)) {
                                        hypernymMap.put(hypernym, hypernymMap.get(hypernym) + 1);
                                    } else {
                                        hypernymMap.put(hypernym, 1);
                                    }
                                }
                            }
                        }
                    }
                    Matcher patM = pattern5.matcher(line);
                    while (patM.find()) {
                        String hypernym = patM.group(2);
                        if (patM.group(1).equals(args[1])) {
                            if (hypernymMap.containsKey(hypernym)) {
                                hypernymMap.put(hypernym, hypernymMap.get(hypernym) + 1);
                            } else {
                                hypernymMap.put(hypernym, 1);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        Set<Map.Entry<String, Integer>> entrySet = hypernymMap.entrySet();
        List<Map.Entry<String, Integer>> list = new ArrayList<>(entrySet);
        Comparator<Map.Entry<String, Integer>> cmp = (e1, e2) -> e1.getValue().compareTo(e2.getValue());
        list.sort(cmp.reversed());
        for (Map.Entry<String, Integer> e : list) {
            System.out.println(e.getKey() + ": " + e.getValue());
        }
    }
}
