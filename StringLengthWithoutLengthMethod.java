import java.util.Scanner;

public class StringLengthWithoutLengthMethod {

       public static int findLength(String str) {
        int count = 0;
        try {
            while (true) {
                                str.charAt(count);
                count++;
            }
        } catch (StringIndexOutOfBoundsException e) {
                      return count;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String input = sc.nextLine();

                int lengthWithoutMethod = findLength(input);

               int lengthWithMethod = input.length();

        System.out.println("Length found without length(): " + lengthWithoutMethod);
        System.out.println("Length found with length(): " + lengthWithMethod);

        sc.close();
    }
}
