import java.util.*;

public class FindAndReplace {
    
    public static List<Integer> findOccurrences(String text, String find) {
        List<Integer> positions = new ArrayList<>();
        int index = text.indexOf(find);
        while (index != -1) {
            positions.add(index);
            index = text.indexOf(find, index + find.length());
        }
        return positions;
    }

    public static String manualReplace(String text, String find, String replace) {
        List<Integer> positions = findOccurrences(text, find);
        StringBuilder result = new StringBuilder();
        int i = 0;
        while (i < text.length()) {
            // If current index is one of the found positions
            if (positions.contains(i)) {
                result.append(replace);
                i += find.length(); // skip over the substring
            } else {
                result.append(text.charAt(i));
                i++;
            }
        }
        return result.toString();
    }

    public static boolean compareResults(String text, String find, String replace, String manualResult) {
        String builtInResult = text.replace(find, replace);
        return builtInResult.equals(manualResult);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the main text:");
        String text = sc.nextLine();

        System.out.println("Enter the substring to find:");
        String find = sc.nextLine();

        System.out.println("Enter the replacement substring:");
        String replace = sc.nextLine();

        String manualResult = manualReplace(text, find, replace);
        String builtInResult = text.replace(find, replace);

        boolean isSame = compareResults(text, find, replace, manualResult);

        System.out.println("\nManual Replace Result: " + manualResult);
        System.out.println("Built-in Replace Result: " + builtInResult);
        System.out.println("Do both match? " + isSame);

        sc.close();
    }
}
