import java.util.*;

public class TextFormatter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter text: ");
        String text = scanner.nextLine();
        
        System.out.print("Enter width: ");
        int width = Math.max(scanner.nextInt(), 10);
        
        String[] words = splitText(text);
        System.out.println("\nORIGINAL TEXT:\n" + text + "\nWords: " + words.length);
        
        // Performance tests
        long start = System.nanoTime();
        List<String> justified = justifyText(words, width);
        long sbTime = System.nanoTime() - start;
        
        start = System.nanoTime();
        List<String> centered = centerText(words, width);
        long sbCenterTime = System.nanoTime() - start;
        
        start = System.nanoTime();
        justifyTextConcat(words, width);
        long concatTime = System.nanoTime() - start;
        
        start = System.nanoTime();
        centerTextConcat(words, width);
        long concatCenterTime = System.nanoTime() - start;
        
        // Display results
        System.out.println("\nLEFT-JUSTIFIED:");
        displayText(justified);
        
        System.out.println("\nCENTERED:");
        displayText(centered);
        
        System.out.printf("\nPERFORMANCE:\nStringBuilder: %.3fms\nConcat: %.3fms\nSpeed: %.1fx\n",
            sbTime/1_000_000.0, concatTime/1_000_000.0, (double)concatTime/sbTime);
    }
    
    private static String[] splitText(String text) {
        List<String> words = new ArrayList<>();
        int start = 0;
        for (int i = 0; i < text.length(); i++) {
            if (Character.isWhitespace(text.charAt(i))) {
                if (i > start) words.add(text.substring(start, i));
                start = i + 1;
            }
        }
        if (start < text.length()) words.add(text.substring(start));
        return words.toArray(new String[0]);
    }
    
    private static List<String> justifyText(String[] words, int width) {
        List<String> lines = new ArrayList<>();
        List<String> lineWords = new ArrayList<>();
        int lineLen = 0;
        
        for (String word : words) {
            if (lineLen + word.length() + lineWords.size() > width) {
                lines.add(buildLine(lineWords, lineLen, width, false));
                lineWords.clear();
                lineLen = 0;
            }
            lineWords.add(word);
            lineLen += word.length();
        }
        if (!lineWords.isEmpty()) lines.add(buildLine(lineWords, lineLen, width, true));
        return lines;
    }
    
    private static String buildLine(List<String> words, int len, int width, boolean lastLine) {
        StringBuilder sb = new StringBuilder();
        int spaces = width - len;
        int gaps = words.size() - 1;
        
        if (lastLine || gaps == 0) {
            for (int i = 0; i < words.size(); i++) {
                sb.append(words.get(i));
                if (i < gaps) sb.append(' ');
            }
            while (sb.length() < width) sb.append(' ');
        } else {
            int baseSpaces = spaces / gaps;
            int extra = spaces % gaps;
            for (int i = 0; i < words.size(); i++) {
                sb.append(words.get(i));
                if (i < gaps) {
                    for (int j = 0; j < baseSpaces; j++) sb.append(' ');
                    if (i < extra) sb.append(' ');
                }
            }
        }
        return sb.toString();
    }
    
    private static List<String> centerText(String[] words, int width) {
        List<String> lines = new ArrayList<>();
        List<String> lineWords = new ArrayList<>();
        int lineLen = 0;
        
        for (String word : words) {
            if (lineLen + word.length() + lineWords.size() > width) {
                lines.add(buildCenteredLine(lineWords, lineLen, width));
                lineWords.clear();
                lineLen = 0;
            }
            lineWords.add(word);
            lineLen += word.length();
        }
        if (!lineWords.isEmpty()) lines.add(buildCenteredLine(lineWords, lineLen, width));
        return lines;
    }
    
    private static String buildCenteredLine(List<String> words, int len, int width) {
        StringBuilder sb = new StringBuilder();
        int padding = (width - len - (words.size() - 1)) / 2;
        for (int i = 0; i < padding; i++) sb.append(' ');
        for (int i = 0; i < words.size(); i++) {
            sb.append(words.get(i));
            if (i < words.size() - 1) sb.append(' ');
        }
        while (sb.length() < width) sb.append(' ');
        return sb.toString();
    }
    
    // Concat versions for performance comparison
    private static List<String> justifyTextConcat(String[] words, int width) {
        List<String> lines = new ArrayList<>();
        List<String> lineWords = new ArrayList<>();
        int lineLen = 0;
        
        for (String word : words) {
            if (lineLen + word.length() + lineWords.size() > width) {
                lines.add(buildLineConcat(lineWords, lineLen, width, false));
                lineWords.clear();
                lineLen = 0;
            }
            lineWords.add(word);
            lineLen += word.length();
        }
        if (!lineWords.isEmpty()) lines.add(buildLineConcat(lineWords, lineLen, width, true));
        return lines;
    }
    
    private static String buildLineConcat(List<String> words, int len, int width, boolean lastLine) {
        String line = "";
        int spaces = width - len;
        int gaps = words.size() - 1;
        
        if (lastLine || gaps == 0) {
            for (int i = 0; i < words.size(); i++) {
                line += words.get(i);
                if (i < gaps) line += ' ';
            }
            while (line.length() < width) line += ' ';
        } else {
            int baseSpaces = spaces / gaps;
            int extra = spaces % gaps;
            for (int i = 0; i < words.size(); i++) {
                line += words.get(i);
                if (i < gaps) {
                    for (int j = 0; j < baseSpaces; j++) line += ' ';
                    if (i < extra) line += ' ';
                }
            }
        }
        return line;
    }
    
    private static List<String> centerTextConcat(String[] words, int width) {
        List<String> lines = new ArrayList<>();
        List<String> lineWords = new ArrayList<>();
        int lineLen = 0;
        
        for (String word : words) {
            if (lineLen + word.length() + lineWords.size() > width) {
                lines.add(buildCenteredLineConcat(lineWords, lineLen, width));
                lineWords.clear();
                lineLen = 0;
            }
            lineWords.add(word);
            lineLen += word.length();
        }
        if (!lineWords.isEmpty()) lines.add(buildCenteredLineConcat(lineWords, lineLen, width));
        return lines;
    }
    
    private static String buildCenteredLineConcat(List<String> words, int len, int width) {
        String line = "";
        int padding = (width - len - (words.size() - 1)) / 2;
        for (int i = 0; i < padding; i++) line += ' ';
        for (int i = 0; i < words.size(); i++) {
            line += words.get(i);
            if (i < words.size() - 1) line += ' ';
        }
        while (line.length() < width) line += ' ';
        return line;
    }
    
    private static void displayText(List<String> lines) {
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i).replace(' ', '·');
            System.out.printf("%2d: %s (%d)%n", i + 1, line, line.length());
        }
    }
}