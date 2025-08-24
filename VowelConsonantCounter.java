import java.util.Scanner;


public class VowelConsonantCounter {


   public static String checkCharacter(char ch) {
       char lowerCh = ch;
       if (ch >= 'A' && ch <= 'Z') {
           lowerCh = (char) (ch + 32);
       }


       if (lowerCh >= 'a' && lowerCh <= 'z') {
           if (lowerCh == 'a' || lowerCh == 'e' || lowerCh == 'i' || lowerCh == 'o' || lowerCh == 'u') {
               return "Vowel";
           } else {
               return "Consonant";
           }
       } else {
           return "Not a Letter";
       }
   }
public static int[] countVowelsAndConsonants(String str) {
       int vowelCount = 0;
       int consonantCount = 0;


       for (int i = 0; i < str.length(); i++) {
           char ch = str.charAt(i);
           String result = checkCharacter(ch);


           if (result.equals("Vowel")) {
               vowelCount++;
           } else if (result.equals("Consonant")) {
               consonantCount++;
           }
       }
       return new int[]{vowelCount, consonantCount};
   }


   public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
       System.out.println("Enter a string to count vowels and consonants:");
       String userInput = scanner.nextLine();


       int[] counts = countVowelsAndConsonants(userInput);
       int vowelCount = counts[0];
       int consonantCount = counts[1];


       System.out.println("\nResults for the string: \"" + userInput + "\"");
       System.out.println("Number of vowels: " + vowelCount);
       System.out.println("Number of consonants: " + consonantCount);


       scanner.close();
   }
}
