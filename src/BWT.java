import java.util.*;

public class BWT {
    // Function to construct BWT
    public static String buildBWT(String sequence) {
        sequence += "$"; // Sentinel character
        int n = sequence.length();
        String[] rotations = new String[n];

        // Generate all rotations
        for (int i = 0; i < n; i++) {
            rotations[i] = sequence.substring(i) + sequence.substring(0, i);
        }

        // Sort rotations lexicographically
        Arrays.sort(rotations);

        // Build BWT by taking last chars
        StringBuilder bwt = new StringBuilder();
        for (String s : rotations) {
            bwt.append(s.charAt(n - 1));
        }
        return bwt.toString();
    }

    // Function to perform backward search
    public static List<Integer> backwardSearch(String sequence, String pattern) {
        String bwt = buildBWT(sequence);
        int n = bwt.length();

        // Step 1: Calculate First Column
        char[] firstCol = bwt.toCharArray();
        Arrays.sort(firstCol);

        // Step 2: Count occurrences of each character
        Map<Character, Integer> countMap = new HashMap<>();
        Map<Character, int[]> occCountMap = new HashMap<>();
        for (char ch : "ACGT$".toCharArray()) {
            countMap.put(ch, 0);
            occCountMap.put(ch, new int[n]);
        }

        for (int i = 0; i < n; i++) {
            char ch = bwt.charAt(i);
            countMap.put(ch, countMap.get(ch) + 1);
            for (char c : "ACGT$".toCharArray()) {
                if (i > 0) {
                    occCountMap.get(c)[i] = occCountMap.get(c)[i - 1];
                }
                if (ch == c) {
                    occCountMap.get(c)[i]++;
                }
            }
        }

        // Step 3: Compute starting index of each char in first column
        Map<Character, Integer> startIndex = new HashMap<>();
        int sum = 0;
        for (char c : "ACGT$".toCharArray()) {
            startIndex.put(c, sum);
            sum += countMap.get(c);
        }

        // Step 4: Backward Search
        int top = 0;
        int bottom = n - 1;
        for (int i = pattern.length() - 1; i >= 0; i--) {
            char ch = pattern.charAt(i);
            if (!countMap.containsKey(ch)) return new ArrayList<>(); // Not found
            int topCount = (top > 0) ? occCountMap.get(ch)[top - 1] : 0;
            int bottomCount = occCountMap.get(ch)[bottom];
            int occ = bottomCount - topCount;
            if (occ == 0) return new ArrayList<>(); // Not found
            top = startIndex.get(ch) + topCount;
            bottom = startIndex.get(ch) + bottomCount - 1;
        }

        // Step 5: Collect positions
        List<Integer> positions = new ArrayList<>();
        for (int i = top; i <= bottom; i++) {
            positions.add(getOriginalIndex(sequence, bwt, i));
        }
        Collections.sort(positions);
        return positions;
    }

    // Recover original index from BWT
    private static int getOriginalIndex(String sequence, String bwt, int idx) {
        int n = bwt.length();
        int[] lfMapping = new int[n];
        char[] firstCol = bwt.toCharArray();
        Arrays.sort(firstCol);

        Map<Character, Queue<Integer>> posMap = new HashMap<>();
        for (char ch : "ACGT$".toCharArray()) posMap.put(ch, new LinkedList<>());

        for (int i = 0; i < n; i++) posMap.get(bwt.charAt(i)).add(i);

        for (int i = 0; i < n; i++) {
            char ch = firstCol[i];
            lfMapping[posMap.get(ch).poll()] = i;
        }

        int steps = 0;
        while (bwt.charAt(idx) != '$') {
            idx = lfMapping[idx];
            steps++;
        }
        return sequence.length() - steps;
    }
}