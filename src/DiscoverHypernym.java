import java.io.File;

/**
 * The final flick of a finger in this assignment.
 */
public class DiscoverHypernym {
    /**
     * The entry point of the program.
     * @param args (String[]) The program expects two arguments. The first, a path to a directory holding a corpus of
     * data to read. The second, a lemma to search hypernyms for.
     */
    public static void main(String[] args) {
        File[] files = new File(args[0]).listFiles();
        if (files == null) {
            System.out.println("The provided argument does not denote a directory.");
            return;
        }
        HypernymMap hypernymMap = new HypernymMap(args[1]);
        if (!hypernymMap.print(files)) {
            System.out.println("An I/O Error has occurred.");
        }
    }
}
