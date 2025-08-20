public class StringMethodsDemo {
    public static void main(String[] args) {
        String text = "  hello myself nainesh";
        
        System.out.println("Original String: \"" + text + "\"");
        System.out.println("Length: " + text.length());

        String trimmed = text.trim();
        System.out.println("After trim: \"" + trimmed + "\"");

        System.out.println("Uppercase: " + trimmed.toUpperCase());
        System.out.println("Lowercase: " + trimmed.toLowerCase());

        System.out.println("Character at index 6: " + trimmed.charAt(6));

        System.out.println("Contains 'Java'? " + trimmed.contains("Java"));

        System.out.println("Index of 'Programming': " + trimmed.indexOf("Programming"));
        System.out.println("Last index of 'o': " + trimmed.lastIndexOf("o"));

        String sub = trimmed.substring(6, 10); // from index 6 to 9
        System.out.println("Substring(6,10): " + sub);

        String replaced = trimmed.replace("World", "Universe");
        System.out.println("After replace: " + replaced);

        String[] words = trimmed.split(" ");
        System.out.println("Words in string:");
        for (String w : words) {
            System.out.println(" - " + w);
        }

        String str1 = "hello";
        String str2 = "Hello";
        System.out.println("Equals? " + str1.equals(str2));
        System.out.println("EqualsIgnoreCase? " + str1.equalsIgnoreCase(str2));
    }
}