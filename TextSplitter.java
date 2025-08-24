import java.util.Scanner;

public class TextSplitter {
    
    // Method to find the length of a String without using built-in length()
    public static int stringLength(String str) {
        int length = 0;
        try {
            while (true) {
                str.charAt(length);
                length++;
            }
        } catch (IndexOutOfBoundsException e) {
            return length;
        }
    }
    
    // Method to split text into words without using split()
    public static String[] customSplit(String text) {
        int wordCount = 0;
        boolean inWord = false;
        
        for (int i = 0; i < stringLength(text); i++) {
            if (text.charAt(i) != ' ') {
                if (!inWord) {
                    wordCount++;
                    inWord = true;
                }
            } else {
                inWord = false;
            }
        }
        
        if (wordCount == 0) {
            return new String[0];
        }
        
        int[] spaceIndexes = new int[wordCount * 2];
        int currentWord = 0;
        inWord = false;
        
        for (int i = 0; i < stringLength(text); i++) {
            if (text.charAt(i) != ' ') {
                if (!inWord) {
                    spaceIndexes[currentWord * 2] = i;
                    inWord = true;
                }
            } else {
                if (inWord) {
                    spaceIndexes[currentWord * 2 + 1] = i;
                    currentWord++;
                    inWord = false;
                }
            }
        }
        
        if (inWord) {
            spaceIndexes[currentWord * 2 + 1] = stringLength(text);
        }
        
        String[] words = new String[wordCount];
        for (int i = 0; i < wordCount; i++) {
            int start = spaceIndexes[i * 2];
            int end = spaceIndexes[i * 2 + 1];
            words[i] = text.substring(start, end);
        }
        
        return words;
    }
    
    // Method to compare two String arrays
    public static boolean compareStringArrays(String[] arr1, String[] arr2) {
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (!arr1[i].equals(arr2[i])) {
                return false;
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter a text to split into words:");
        String inputText = scanner.nextLine();
        
        // Split using custom method
        String[] customWords = customSplit(inputText);
        System.out.println("\nCustom split result:");
        for (String word : customWords) {
            System.out.println(word);
        }
        
        // Split using built-in method
        String[] builtInWords = inputText.trim().split("\\s+");
        System.out.println("\nBuilt-in split result:");
        for (String word : builtInWords) {
            System.out.println(word);
        }
        
        // Compare the results
        boolean areEqual = compareStringArrays(customWords, builtInWords);
        System.out.println("\nComparison result: " + (areEqual ? "Both splits are identical" : "Splits are different"));
        
        scanner.close();
    }
}
