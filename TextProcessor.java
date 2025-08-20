import java.util.Scanner;

public class TextProcessor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
         System.out.println("=== Text Processing Utility ===");
        System.out.print("Enter a text string: ");
        String input = scanner.nextLine();
             System.out.println("\nProcessing results:");
        System.out.println("1. Length: " + input.length());
        System.out.println("2. Uppercase: " + input.toUpperCase());
        System.out.println("3. Lowercase: " + input.toLowerCase());
        System.out.println("4. Trimmed: '" + input.trim() + "'");
        System.out.println("5. Contains 'java'? " + input.toLowerCase().contains("java"));
        System.out.println("6. Replaced spaces with underscores: " + input.replace(' ', '_'));
         if (!input.isEmpty()) {
            System.out.println("7. First character: " + input.charAt(0));
            System.out.println("8. Last character: " + input.charAt(input.length() - 1));
        }
        
        System.out.println("9. Substring (first 5 chars): " + 
                          (input.length() > 5 ? input.substring(0, 5) : input));
        
        String[] words = input.split("\\s+");
        System.out.println("10. Word count: " + words.length);
        
        System.out.println("\n11. Words separated:");
        for (int i = 0; i < words.length; i++) {
            System.out.println("  Word " + (i+1) + ": " + words[i]);
        }
        
        scanner.close();
    }
}

