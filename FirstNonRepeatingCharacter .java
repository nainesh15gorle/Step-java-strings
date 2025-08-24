import java.util.Scanner;

public class FirstNonRepeatingCharacter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();
        
        char result = findFirstNonRepeatingChar(input);
        
        if (result != '\0') {
            System.out.println("First non-repeating character: " + result);
        } else {
            System.out.println("No non-repeating character found");
        }
        
        scanner.close();
    }
    
    public static char findFirstNonRepeatingChar(String str) {
        // Array to store frequency of each ASCII character (256 possible)
        int[] charCount = new int[256];
        
        // First pass: count frequency of each character
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            charCount[c]++;
        }
        
        // Second pass: find first character with count = 1
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (charCount[c] == 1) {
                return c;
            }
        }
        
        // If no non-repeating character found
        return '\0';
    }
}
