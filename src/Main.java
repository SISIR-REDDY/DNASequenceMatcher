import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String dnaSequence = "";

        System.out.println("=== DNA Sequence Matcher using BWT ===");

        while (true) {
            System.out.println("\n1. Input DNA Sequence");
            System.out.println("2. Detect Mutation Pattern");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter DNA Sequence: ");
                    String seq = sc.nextLine();
                    if (DNAUtils.isValidDNA(seq)) {
                        dnaSequence = seq;
                        System.out.println("Sequence Stored Successfully.");
                    } else {
                        System.out.println("Invalid DNA Sequence! Use A, C, G, T only.");
                    }
                    break;

                case 2:
                    if (dnaSequence.isEmpty()) {
                        System.out.println("Please input DNA sequence first!");
                        break;
                    }
                    System.out.print("Enter Mutation Pattern: ");
                    String pattern = sc.nextLine();
                    if (!DNAUtils.isValidDNA(pattern)) {
                        System.out.println("Invalid Pattern! Use A, C, G, T only.");
                        break;
                    }
                    MutationDetector.detectMutation(dnaSequence, pattern);
                    break;

                case 3:
                    System.out.println("Exiting... Bye!");
                    return;
            }
        }
    }
}
