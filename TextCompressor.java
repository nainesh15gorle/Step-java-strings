import java.util.*;

public class TextCompressor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter text to compress: ");
        String text = scanner.nextLine();
        
        if (text.isEmpty()) {
            System.out.println("No text entered!");
            return;
        }
        
        // Character frequency analysis
        char[] chars;
        int[] freqs;
        (chars = new char[256])[0] = 0;
        (freqs = new int[256])[0] = 0;
        int uniqueCount = countFrequency(text, chars, freqs);
        
        // Sort by frequency (descending)
        sortByFrequency(chars, freqs, uniqueCount);
        
        // Generate compression codes
        String[] codes = generateCodes(uniqueCount);
        
        // Create mapping table
        char[] sortedChars = Arrays.copyOf(chars, uniqueCount);
        String[] mapping = createMapping(sortedChars, codes, uniqueCount);
        
        // Compress text
        String compressed = compressText(text, mapping, sortedChars);
        String decompressed = decompressText(compressed, mapping, sortedChars);
        
        // Display results
        displayAnalysis(text, compressed, decompressed, sortedChars, freqs, mapping);
    }
    
    private static int countFrequency(String text, char[] chars, int[] freqs) {
        int[] asciiFreq = new int[256];
        int uniqueCount = 0;
        
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            asciiFreq[c]++;
        }
        
        for (int i = 0; i < 256; i++) {
            if (asciiFreq[i] > 0) {
                chars[uniqueCount] = (char) i;
                freqs[uniqueCount] = asciiFreq[i];
                uniqueCount++;
            }
        }
        return uniqueCount;
    }
    
    private static void sortByFrequency(char[] chars, int[] freqs, int size) {
        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; j++) {
                if (freqs[j] > freqs[i]) {
                    // Swap frequencies
                    int tempFreq = freqs[i];
                    freqs[i] = freqs[j];
                    freqs[j] = tempFreq;
                    
                    // Swap characters
                    char tempChar = chars[i];
                    chars[i] = chars[j];
                    chars[j] = tempChar;
                }
            }
        }
    }
    
    private static String[] generateCodes(int count) {
        String[] codes = new String[count];
        for (int i = 0; i < count; i++) {
            if (i < 10) {
                codes[i] = String.valueOf(i); // 0-9
            } else if (i < 36) {
                codes[i] = String.valueOf((char) ('a' + i - 10)); // a-z
            } else {
                codes[i] = "#" + (i - 36); // #0, #1, etc.
            }
        }
        return codes;
    }
    
    private static String[] createMapping(char[] chars, String[] codes, int count) {
        String[] mapping = new String[count];
        for (int i = 0; i < count; i++) {
            mapping[i] = codes[i];
        }
        return mapping;
    }
    
    private static String compressText(String text, String[] mapping, char[] chars) {
        StringBuilder compressed = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            for (int j = 0; j < chars.length; j++) {
                if (chars[j] == c) {
                    compressed.append(mapping[j]);
                    break;
                }
            }
        }
        return compressed.toString();
    }
    
    private static String decompressText(String compressed, String[] mapping, char[] chars) {
        StringBuilder decompressed = new StringBuilder();
        StringBuilder currentCode = new StringBuilder();
        
        for (int i = 0; i < compressed.length(); i++) {
            currentCode.append(compressed.charAt(i));
            
            for (int j = 0; j < mapping.length; j++) {
                if (mapping[j].equals(currentCode.toString())) {
                    decompressed.append(chars[j]);
                    currentCode.setLength(0);
                    break;
                }
            }
        }
        return decompressed.toString();
    }
    
    private static void displayAnalysis(String original, String compressed, String decompressed, 
                                      char[] chars, int[] freqs, String[] mapping) {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("COMPRESSION ANALYSIS");
        System.out.println("=".repeat(80));
        
        // Character frequency table
        System.out.println("\nCHARACTER FREQUENCY TABLE:");
        System.out.println("-".repeat(40));
        System.out.printf("%-10s %-10s %-10s%n", "Char", "Freq", "Code");
        System.out.println("-".repeat(40));
        for (int i = 0; i < chars.length; i++) {
            String charDisplay = (chars[i] == ' ') ? "[space]" : 
                                (chars[i] == '\t') ? "[tab]" : 
                                (chars[i] == '\n') ? "[newline]" : 
                                String.valueOf(chars[i]);
            System.out.printf("%-10s %-10d %-10s%n", charDisplay, freqs[i], mapping[i]);
        }
        
        // Text comparison
        System.out.println("\nTEXT COMPARISON:");
        System.out.println("-".repeat(40));
        System.out.println("Original:    " + original);
        System.out.println("Compressed:  " + compressed);
        System.out.println("Decompressed: " + decompressed);
        System.out.println("Match: " + (original.equals(decompressed) ? "✓ SUCCESS" : "✗ FAILED"));
        
        // Compression stats
        double originalSize = original.length() * 8.0; // Assuming 8 bits per char
        double compressedSize = compressed.length() * 4.0; // Average 4 bits per code
        double ratio = (1 - (compressedSize / originalSize)) * 100;
        
        System.out.println("\nCOMPRESSION STATISTICS:");
        System.out.println("-".repeat(40));
        System.out.printf("Original size:    %.0f bits%n", originalSize);
        System.out.printf("Compressed size:  %.0f bits%n", compressedSize);
        System.out.printf("Compression ratio: %.1f%%%n", ratio);
        System.out.printf("Space saved:      %.1f%%%n", ratio);
    }
}