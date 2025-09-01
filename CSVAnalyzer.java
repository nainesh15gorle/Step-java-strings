import java.util.*;

public class CSVAnalyzer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter CSV data (comma-separated, end with empty line):");
        
        StringBuilder input = new StringBuilder();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.isEmpty()) break;
            input.append(line).append("\n");
        }
        
        String[][] data = parseCSV(input.toString());
        if (data.length > 0) {
            String[][] cleaned = cleanData(data);
            displayTable(cleaned);
            generateReport(cleaned);
        }
    }
    
    private static String[][] parseCSV(String text) {
        List<String[]> rows = new ArrayList<>();
        int start = 0, rowStart = 0;
        List<String> currentRow = new ArrayList<>();
        boolean inQuotes = false;
        
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c == '"') inQuotes = !inQuotes;
            else if (c == ',' && !inQuotes) {
                currentRow.add(text.substring(start, i).replace("\"", "").trim());
                start = i + 1;
            } else if (c == '\n' && !inQuotes) {
                currentRow.add(text.substring(start, i).replace("\"", "").trim());
                rows.add(currentRow.toArray(new String[0]));
                currentRow.clear();
                start = i + 1;
                rowStart = i + 1;
            }
        }
        if (start < text.length()) {
            currentRow.add(text.substring(start).replace("\"", "").trim());
            rows.add(currentRow.toArray(new String[0]));
        }
        return rows.toArray(new String[0][]);
    }
    
    private static String[][] cleanData(String[][] data) {
        String[][] cleaned = new String[data.length][];
        for (int i = 0; i < data.length; i++) {
            cleaned[i] = new String[data[i].length];
            for (int j = 0; j < data[i].length; j++) {
                String value = data[i][j];
                if (value.isEmpty()) value = "MISSING";
                cleaned[i][j] = value;
            }
        }
        return cleaned;
    }
    
    private static void displayTable(String[][] data) {
        if (data.length == 0) return;
        
        int[] colWidths = new int[data[0].length];
        for (String[] row : data) {
            for (int j = 0; j < row.length; j++) {
                colWidths[j] = Math.max(colWidths[j], row[j].length());
            }
        }
        
        StringBuilder border = new StringBuilder("+");
        for (int w : colWidths) border.append("-".repeat(w + 2)).append("+");
        
        System.out.println(border);
        for (int i = 0; i < data.length; i++) {
            System.out.print("|");
            for (int j = 0; j < data[i].length; j++) {
                System.out.printf(" %-" + (colWidths[j] + 1) + "s|", data[i][j]);
            }
            System.out.println();
            if (i == 0) System.out.println(border);
        }
        System.out.println(border);
    }
    
    private static void generateReport(String[][] data) {
        System.out.println("\nDATA ANALYSIS REPORT:");
        System.out.println("Total records: " + (data.length - 1));
        
        if (data.length < 2) return;
        
        for (int col = 0; col < data[0].length; col++) {
            System.out.println("\nColumn '" + data[0][col] + "':");
            Set<String> unique = new HashSet<>();
            int missing = 0, numeric = 0;
            double sum = 0, min = Double.MAX_VALUE, max = Double.MIN_VALUE;
            
            for (int row = 1; row < data.length; row++) {
                String value = data[row][col];
                unique.add(value);
                if (value.equals("MISSING")) missing++;
                if (isNumeric(value)) {
                    numeric++;
                    double num = Double.parseDouble(value);
                    sum += num;
                    min = Math.min(min, num);
                    max = Math.max(max, num);
                }
            }
            
            System.out.println("  Unique values: " + unique.size());
            System.out.println("  Missing values: " + missing);
            if (numeric > 0) {
                System.out.printf("  Numeric stats: min=%.2f, max=%.2f, avg=%.2f%n", 
                    min, max, sum / numeric);
            }
        }
    }
    
    private static boolean isNumeric(String str) {
        if (str == null || str.equals("MISSING")) return false;
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}