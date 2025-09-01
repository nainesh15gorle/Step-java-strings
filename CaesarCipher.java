import java.util.*;

public class CaesarCipher {

    // Encrypt method
    public static String encrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (c >= 'A' && c <= 'Z') { // uppercase letters
                char ch = (char) (((c - 'A' + shift) % 26 + 26) % 26 + 'A');
                result.append(ch);
            } else if (c >= 'a' && c <= 'z') { // lowercase letters
                char ch = (char) (((c - 'a' + shift) % 26 + 26) % 26 + 'a');
                result.append(ch);
            } else {
                result.append(c); // keep other chars same
            }
        }
        return result.toString();
    }

    // Decrypt method
    public static String decrypt(String text, int shift) {
        return encrypt(text, -shift); // just reverse shift
    }

    // Display ASCII values of a string
    public static void displayASCII(String label, String text) {
        System.out.println("\n" + label + ": " + text);
        System.out.print("ASCII Values : ");
        for (char c : text.toCharArray()) {
            System.out.print((int)c + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input
        System.out.println("Enter text to encrypt:");
        String text = sc.nextLine();
        System.out.println("Enter shift value:");
        int shift = sc.nextInt();

        // Process
        String encrypted = encrypt(text, shift);
        String decrypted = decrypt(encrypted, shift);

        // Display ASCII values
        displayASCII("Original Text", text);
        displayASCII("Encrypted Text", encrypted);
        displayASCII("Decrypted Text", decrypted);

        // Validation
        System.out.println("\nValidation: " + decrypted.equals(text));

        sc.close();
    }
}
