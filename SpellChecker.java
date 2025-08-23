import java.util.*;

public class SpellChecker {
    public static void main(String[] args) {
        String[] dictionary = {"hello", "world", "java", "program", "string", 
                              "distance", "calculation", "spell", "check", "sentence"};
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a sentence: ");
        String sentence = scanner.nextLine().toLowerCase();
        
        String[] words = splitSentence(sentence);
        System.out.println("\nSPELL CHECK RESULTS:");
        System.out.println("=".repeat(50));
        System.out.printf("%-15s %-15s %-10s %-10s%n", "Original", "Suggestion", "Distance", "Status");
        System.out.println("-".repeat(50));
        
        for (String word : words) {
            if (word.isEmpty()) continue;
            
            String suggestion = findClosestMatch(word, dictionary);
            int distance = calculateDistance(word, suggestion);
            String status = distance == 0 ? "Correct" : "Misspelled";
            
            System.out.printf("%-15s %-15s %-10d %-10s%n", 
                word, suggestion, distance, status);
        }
    }
    
    private static String[] splitSentence(String sentence) {
        List<String> words = new ArrayList<>();
        int start = 0;
        
        for (int i = 0; i < sentence.length(); i++) {
            char c = sentence.charAt(i);
            if (!Character.isLetter(c)) {
                if (i > start) {
                    words.add(sentence.substring(start, i));
                }
                start = i + 1;
            }
        }
        if (start < sentence.length()) {
            words.add(sentence.substring(start));
        }
        return words.toArray(new String[0]);
    }
    
    private static int calculateDistance(String word1, String word2) {
        int len1 = word1.length(), len2 = word2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        
        for (int i = 0; i <= len1; i++) dp[i][0] = i;
        for (int j = 0; j <= len2; j++) dp[0][j] = j;
        
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                int cost = word1.charAt(i - 1) == word2.charAt(j - 1) ? 0 : 1;
                dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), dp[i - 1][j - 1] + cost);
            }
        }
        return dp[len1][len2];
    }
    
    private static String findClosestMatch(String word, String[] dictionary) {
        int minDistance = Integer.MAX_VALUE;
        String bestMatch = word;
        
        for (String dictWord : dictionary) {
            int distance = calculateDistance(word, dictWord);
            if (distance < minDistance) {
                minDistance = distance;
                bestMatch = dictWord;
            }
        }
        return minDistance <= 2 ? bestMatch : word;
    }
}