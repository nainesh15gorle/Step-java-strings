import java.util.Scanner;

public class UniqueCharactersFinder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();
        
        int length = calculateLength(input);
        System.out.println("Length of string: " + length);
        
        char[] uniqueChars = findUniqueCharacters(input);
        
        System.out.print("Unique characters: ");
        for (char c : uniqueChars) {
            System.out.print(c + " ");
        }
        
        scanner.close();
    }
    
    // Method to calculate length of string without using length()
    public static int calculateLength(String str) {
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
    
    // Method to find unique characters using charAt()
    public static char[] findUniqueCharacters(String str) {
        int length = calculateLength(str);
char[] allUnique = new char[length]; // Temporary array with max possible size
        int uniqueCount = 0;
        
        for (int i = 0; i < length; i++) {
            char currentChar = str.charAt(i);
            boolean isUnique = true;
            
            // Check if this character appeared before
            for (int j = 0; j < uniqueCount; j++) {
                if (allUnique[j] == currentChar) {
                    isUnique = false;
                    break;
   }
            }
            
            // If unique, add to our array
            if (isUnique) {
                allUnique[uniqueCount] = currentChar;
                uniqueCount++;
            }
        }
        
        // Create properly sized array for the result
        char[] result = new char[uniqueCount];
        System.arraycopy(allUnique, 0, result, 0, uniqueCount);
        
        return result;
    }
}
