import java.util.List;

public class MutationDetector {
    public static void detectMutation(String dnaSequence, String mutationPattern) {
        List<Integer> positions = BWT.backwardSearch(dnaSequence, mutationPattern);
        if (positions.isEmpty()) {
            System.out.println("Mutation pattern '" + mutationPattern + "' NOT found.");
        } else {
            System.out.print("Mutation pattern '" + mutationPattern + "' detected at positions: ");
            for (int pos : positions) {
                System.out.print(pos + " ");
            }
            System.out.println("\nTotal Occurrences: " + positions.size());
        }
    }
}