import java.util.Scanner;

public class TextProcessor {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== TEXT PROCESSOR ===");
        System.out.print("Enter a sentence: ");
        String input = scanner.nextLine();

        // 1. Convert to uppercase
        String upper = input.toUpperCase();
        System.out.println("\n1. Uppercase: " + upper);

        // 2. Convert to lowercase
        String lower = input.toLowerCase();
        System.out.println("2. Lowercase: " + lower);

        // 3. Get substring (first 5 chars)
        String substring = input.length() > 5 ? input.substring(0, 5) + "..." : input;
        System.out.println("3. First 5 chars: " + substring);

        // 4. Replace spaces with hyphens
        String replaced = input.replace(" ", "-");
        System.out.println("4. Spaces → Hyphens: " + replaced);

        // 5. Split into words
        String[] words = input.split(" ");
        System.out.println("5. Split into words:");
        for (String word : words) {
            System.out.println("   - " + word);
        }

        // 6. Reverse the string (using StringBuilder)
        String reversed = new StringBuilder(input).reverse().toString();
        System.out.println("6. Reversed: " + reversed);

        // 7. Check if contains a word
        System.out.print("\nEnter a word to search: ");
        String searchWord = scanner.next();
        boolean contains = input.toLowerCase().contains(searchWord.toLowerCase());
        System.out.println("7. Contains '" + searchWord + "'? " + contains);

        // 8. Trim whitespace
        String trimmed = input.trim();
        System.out.println("8. Trimmed: \"" + trimmed + "\"");
   // 9. Join words with a delimiter
        String joined = String.join(" | ", words);
        System.out.println("9. Joined with ' | ': " + joined);

        scanner.close();
    }
}

