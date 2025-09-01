import java.util.*;

public class EmailAnalyzer {
    private static final List<EmailData> emailDataList = new ArrayList<>();
    private static int validCount = 0;
    private static int invalidCount = 0;
    private static final Map<String, Integer> domainCount = new HashMap<>();
    private static int totalUsernameLength = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Email Address Analyzer ===");
        System.out.println("Enter email addresses (one per line). Type 'done' to finish:");
        
        while (true) {
            String email = scanner.nextLine().trim();
            
            if (email.equalsIgnoreCase("done")) {
                break;
            }
            
            if (!email.isEmpty()) {
                processEmail(email);
            }
        }
        
        scanner.close();
        displayResults();
        displayStatistics();
    }

    private static void processEmail(String email) {
        boolean isValid = validateEmail(email);
        EmailData data = extractEmailComponents(email, isValid);
        emailDataList.add(data);
        
        if (isValid) {
            validCount++;
            totalUsernameLength += data.username.length();
            domainCount.put(data.domain, domainCount.getOrDefault(data.domain, 0) + 1);
        } else {
            invalidCount++;
        }
    }

    private static boolean validateEmail(String email) {
        // Check for exactly one '@' symbol
        int atIndex = email.indexOf('@');
        if (atIndex == -1 || atIndex != email.lastIndexOf('@')) {
            return false;
        }

        // Check username is not empty
        if (atIndex == 0) {
            return false;
        }

        // Check for at least one '.' after '@' and domain is not empty
        int dotIndex = email.indexOf('.', atIndex + 1);
        if (dotIndex == -1 || dotIndex == email.length() - 1) {
            return false;
        }

        // Check domain part is not empty
        if (atIndex == email.length() - 1) {
            return false;
        }

        return true;
    }

    private static EmailData extractEmailComponents(String email, boolean isValid) {
        EmailData data = new EmailData();
        data.email = email;
        data.isValid = isValid;

        if (isValid) {
            int atIndex = email.indexOf('@');
            int lastDotIndex = email.lastIndexOf('.');
            
            // Extract username
            data.username = email.substring(0, atIndex);
            
            // Extract full domain
            data.domain = email.substring(atIndex + 1);
            
            // Extract domain name and extension
            if (lastDotIndex > atIndex) {
                data.domainName = email.substring(atIndex + 1, lastDotIndex);
                data.extension = email.substring(lastDotIndex + 1);
            } else {
                data.domainName = data.domain;
                data.extension = "";
            }
        } else {
            data.username = "N/A";
            data.domain = "N/A";
            data.domainName = "N/A";
            data.extension = "N/A";
        }

        return data;
    }

    private static void displayResults() {
        System.out.println("\n" + "=".repeat(100));
        System.out.println("EMAIL ANALYSIS RESULTS");
        System.out.println("=".repeat(100));
        System.out.printf("%-30s %-20s %-20s %-15s %-10s %-10s%n", 
            "Email", "Username", "Domain", "Domain Name", "Extension", "Valid");
        System.out.println("-".repeat(100));

        for (EmailData data : emailDataList) {
            System.out.printf("%-30s %-20s %-20s %-15s %-10s %-10s%n",
                truncate(data.email, 28),
                truncate(data.username, 18),
                truncate(data.domain, 18),
                truncate(data.domainName, 13),
                truncate(data.extension, 8),
                data.isValid ? "✓" : "✗");
        }
        System.out.println("-".repeat(100));
    }

    private static void displayStatistics() {
        System.out.println("\nSTATISTICS:");
        System.out.println("===========");
        System.out.println("Total emails processed: " + (validCount + invalidCount));
        System.out.println("Valid emails: " + validCount);
        System.out.println("Invalid emails: " + invalidCount);
        System.out.printf("Validation rate: %.1f%%%n", 
            (validCount * 100.0) / (validCount + invalidCount));

        if (validCount > 0) {
            System.out.printf("Average username length: %.2f characters%n", 
                (double) totalUsernameLength / validCount);
            
            String mostCommonDomain = findMostCommonDomain();
            System.out.println("Most common domain: " + mostCommonDomain + 
                " (" + domainCount.get(mostCommonDomain) + " occurrences)");
            
            System.out.println("\nDomain distribution:");
            for (Map.Entry<String, Integer> entry : domainCount.entrySet()) {
                System.out.printf("  %-20s: %d (%.1f%%)%n", 
                    entry.getKey(), 
                    entry.getValue(),
                    (entry.getValue() * 100.0) / validCount);
            }
        }
    }

    private static String findMostCommonDomain() {
        return domainCount.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("N/A");
    }

    private static String truncate(String text, int maxLength) {
        if (text.length() <= maxLength) {
            return text;
        }
        return text.substring(0, maxLength - 3) + "...";
    }

    static class EmailData {
        String email;
        String username;
        String domain;
        String domainName;
        String extension;
        boolean isValid;
    }
}