public class DNAUtils {
    public static boolean isValidDNA(String seq) {
        return seq.matches("[ACGT]+");
    }
}
