import java.util.Scanner;

public class StringMethodsDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

       
        System.out.println("Original String: " + input);

        
        System.out.println("Uppercase: " + input.toUpperCase());

        
        System.out.println("Lowercase: " + input.toLowerCase());

        
        System.out.println("Trimmed: '" + input.trim() + "'");

        
        System.out.println("Length: " + input.length());

      
        System.out.println("Contains 'java'? " + input.toLowerCase().contains("java"));

        
        System.out.println("Replace spaces with underscores: " + input.replace(" ", "_"));

       
        int endIndex = Math.min(5, input.length());
        System.out.println("Substring (0 to 5): " + input.substring(0, endIndex));

       
        System.out.println("Starts with 'Hello'? " + input.startsWith("Hello"));

        scanner.close();
    }
}
