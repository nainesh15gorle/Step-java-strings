public class ASCIIDemo {
    public static void main(String[] args) {
        char letter = 'A';
        int asciiValue = (int) letter; // Convert char to ASCII

        System.out.println("ASCII Manipulation Demo");
        System.out.println("-----------------------");
        System.out.println("Char '" + letter + "' → ASCII: " + asciiValue);

        // Convert ASCII back to char
        int newAscii = asciiValue + 3; // Shift by 3 (A → D)
        char newChar = (char) newAscii;
        System.out.println("ASCII " + newAscii + " → Char: '" + newChar + "'");

        // Check if a character is lowercase
        char testChar = 'g';
        boolean isLower = (testChar >= 'a' && testChar <= 'z');
        System.out.println("Is '" + testChar + "' lowercase? " + isLower);

        // Convert lowercase to uppercase using ASCII
        char upperChar = (char) (testChar - 32); // 'g' (103) → 'G' (71)
        System.out.println("Lowercase '" + testChar + "' → Uppercase: '" + upperChar + "'");
    }
}