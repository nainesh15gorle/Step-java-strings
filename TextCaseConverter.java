import java.util.*;

public class TextCaseConverter {

   
    public static String toUpperASCII(String text) {myself 
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                result.append((char)(c - 32)); 
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    // Convert to Lowercase using ASCII
    public static String toLowerASCII(String text) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                result.append((char)(c + 32)); // convert uppercase -> lowercase
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    // Convert to Title Case using ASCII
    public static String toTitleCaseASCII(String text) {
        StringBuilder result = new StringBuilder();
        boolean newWord = true;
        for (char c : text.toCharArray()) {
            if (c == ' ') {
                result.append(c);
                newWord = true; // next character will be new word
            } else {
                if (newWord) {
                    // Make first char uppercase
                    if (c >= 'a' && c <= 'z') {
                        result.append((char)(c - 32));
                    } else {
                        result.append(c);
                    }
                    newWord = false;
                } else {
                    // Make other chars lowercase
                    if (c >= 'A' && c <= 'Z') {
                        result.append((char)(c + 32));
                    } else {
                        result.append(c);
                    }
                }
            }
        }
        return result.toString();
    }

    // Compare with built-in methods
    public static void compareResults(String text, String manualUpper, String manualLower) {
        System.out.println("\nComparison with Built-in Methods:");
        System.out.println("Manual Upper == Built-in Upper ? " + manualUpper.equals(text.toUpperCase()));
        System.out.println("Manual Lower == Built-in Lower ? " + manualLower.equals(text.toLowerCase()));
    }

    // Main Method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter text:");
        String text = sc.nextLine();

        // Manual conversions
        String upper = toUpperASCII(text);
        String lower = toLowerASCII(text);
        String title = toTitleCaseASCII(text);

        // Built-in conversions
        String builtinUpper = text.toUpperCase();
        String builtinLower = text.toLowerCase();

        // Display results in tabular format
        System.out.println("\n================= Results =================");
        System.out.printf("%-20s %-30s\n", "Conversion Type", "Result");
        System.out.println("--------------------------------------------------");
        System.out.printf("%-20s %-30s\n", "Original", text);
        System.out.printf("%-20s %-30s\n", "Manual Uppercase", upper);
        System.out.printf("%-20s %-30s\n", "Manual Lowercase", lower);
        System.out.printf("%-20s %-30s\n", "Manual Title Case", title);
        System.out.printf("%-20s %-30s\n", "Built-in Uppercase", builtinUpper);
        System.out.printf("%-20s %-30s\n", "Built-in Lowercase", builtinLower);

        // Compare
        compareResults(text, upper, lower);

        sc.close();
    }
}
