import java.util.*;

public class FileOrganizer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter file names (comma-separated):");
        String[] files = scanner.nextLine().split(",");
        
        List<FileInfo> fileList = new ArrayList<>();
        for (String file : files) {
            fileList.add(analyzeFile(file.trim()));
        }
        
        categorizeFiles(fileList);
        generateReport(fileList);
    }
    
    private static FileInfo analyzeFile(String filename) {
        FileInfo info = new FileInfo();
        info.originalName = filename;
        
        int lastDot = filename.lastIndexOf('.');
        if (lastDot > 0 && lastDot < filename.length() - 1) {
            info.name = filename.substring(0, lastDot);
            info.extension = filename.substring(lastDot + 1).toLowerCase();
        } else {
            info.name = filename;
            info.extension = "unknown";
        }
        
        info.isValid = isValidFilename(filename);
        return info;
    }
    
    private static boolean isValidFilename(String name) {
        if (name.isEmpty() || name.length() > 255) return false;
        String invalidChars = "<>:\"/\\|?*";
        for (char c : invalidChars.toCharArray()) {
            if (name.indexOf(c) >= 0) return false;
        }
        return true;
    }
    
    private static void categorizeFiles(List<FileInfo> files) {
        Map<String, Integer> counts = new HashMap<>();
        for (FileInfo file : files) {
            file.category = getCategory(file.extension);
            file.suggestedName = generateName(file, counts);
        }
    }
    
    private static String getCategory(String ext) {
        switch (ext) {
            case "txt": case "doc": case "docx": case "pdf": return "Documents";
            case "jpg": case "png": case "gif": case "bmp": return "Images";
            case "java": case "py": case "js": case "html": return "Code";
            case "mp3": case "wav": case "flac": return "Audio";
            case "mp4": case "avi": case "mov": return "Video";
            default: return "Other";
        }
    }
    
    private static String generateName(FileInfo file, Map<String, Integer> counts) {
        if (!file.isValid) return "INVALID_NAME";
        
        String date = new java.text.SimpleDateFormat("yyyyMMdd").format(new Date());
        String baseName = file.category.toLowerCase() + "_" + date;
        
        int count = counts.getOrDefault(baseName, 0) + 1;
        counts.put(baseName, count);
        
        return baseName + "_" + count + "." + file.extension;
    }
    
    private static void generateReport(List<FileInfo> files) {
        System.out.println("\nFILE ORGANIZATION REPORT");
        System.out.println("=".repeat(60));
        
        Map<String, Integer> categoryCount = new HashMap<>();
        int invalidCount = 0;
        
        for (FileInfo file : files) {
            categoryCount.put(file.category, categoryCount.getOrDefault(file.category, 0) + 1);
            if (!file.isValid) invalidCount++;
        }
        
        System.out.printf("%-12s %-20s %-20s%n", "Category", "Count", "Suggested Name");
        System.out.println("-".repeat(60));
        for (FileInfo file : files) {
            System.out.printf("%-12s %-20s %-20s%n", 
                file.category, file.originalName, file.suggestedName);
        }
        
        System.out.println("\nCATEGORY SUMMARY:");
        for (Map.Entry<String, Integer> entry : categoryCount.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " files");
        }
        
        System.out.println("\nInvalid filenames: " + invalidCount);
        System.out.println("Total files processed: " + files.size());
        
        System.out.println("\nRENAME COMMANDS:");
        for (FileInfo file : files) {
            if (file.isValid) {
                System.out.println("mv \"" + file.originalName + "\" \"" + file.suggestedName + "\"");
            }
        }
    }
    
    static class FileInfo {
        String originalName;
        String name;
        String extension;
        String category;
        String suggestedName;
        boolean isValid;
    }
}