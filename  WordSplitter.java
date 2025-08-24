import java.util.Scanner;
import java.util.ArrayList;

public class WordSplitter {

    public static String[] splitText(String text) {
        ArrayList<String> words = new ArrayList<>();
        String currentWord = "";
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c == ' ' || c == '\t' || c == '\n') {
                if (!currentWord.isEmpty()) {
                    words.add(currentWord);
                    currentWord = "";
                }
            } else {
                currentWord += c;
            }
        }
        if (!currentWord.isEmpty()) {
            words.add(currentWord);
        }
        return words.toArray(new String[0]);
    }

    public static int getStringLength(String str) {
        int length = 0;
        try {
            while (true) {
                str.charAt(length);
                length++;
            }
        } catch (StringIndexOutOfBoundsException e) {
            return length;
        }
    }

    public static String[][] get2DArray(String[] words) {
        String[][] result = new String[words.length][2];
        for (int i = 0; i < words.length; i++) {
            result[i][0] = words[i];
            result[i][1] = String.valueOf(getStringLength(words[i]));
        }
        return result;
    }

    public static int[] findShortestAndLongestLengths(String[][] wordsAndLengths) {
        if (wordsAndLengths.length == 0) {
            return new int[]{0, 0};
        }

        int minLength = Integer.parseInt(wordsAndLengths[0][1]);
        int maxLength = Integer.parseInt(wordsAndLengths[0][1]);

        for (int i = 1; i < wordsAndLengths.length; i++) {
            int currentLength = Integer.parseInt(wordsAndLengths[i][1]);
            if (currentLength < minLength) {
                minLength = currentLength;
            }
            if (currentLength > maxLength) {
                maxLength = currentLength;
            }
        }
        return new int[]{minLength, maxLength};
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a line of text:");
        String userInput = scanner.nextLine();

        String[] wordsArray = splitText(userInput);
        String[][] wordsAndLengths = get2DArray(wordsArray);

        int[] lengths = findShortestAndLongestLengths(wordsAndLengths);
        int shortestLength = lengths[0];
        int longestLength = lengths[1];
        String shortestWord = "";
        String longestWord = "";

        for (String[] entry : wordsAndLengths) {
            int currentLength = Integer.parseInt(entry[1]);
            if (currentLength == shortestLength && shortestWord.isEmpty()) {
                shortestWord = entry[0];
            }
            if (currentLength == longestLength && longestWord.isEmpty()) {
                longestWord = entry[0];
            }
        }

        System.out.println("\nWords and their lengths:");
        System.out.printf("%-20s %-10s\n", "Word", "Length");
        System.out.println("------------------------------");
        for (String[] entry : wordsAndLengths) {
            String word = entry[0];
            int length = Integer.parseInt(entry[1]);
            System.out.printf("%-20s %-10d\n", word, length);
        }

        System.out.println("\nSummary:");
        System.out.println("Shortest word: \"" + shortestWord + "\" with length " + shortestLength);
        System.out.println("Longest word: \"" + longestWord + "\" with length " + longestLength);

        scanner.close();