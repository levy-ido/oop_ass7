import java.io.File;

/**
 * .
 */
public class DiscoverHypernym {
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
        HypernymMap hypernymMap = new HypernymMap(args[1]);
        if (!hypernymMap.print(files)) {
            System.out.println("An I/O Error has occurred.");
        }
    }
}
