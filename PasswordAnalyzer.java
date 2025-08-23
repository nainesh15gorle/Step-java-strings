import java.util.*;

public class PasswordAnalyzer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter passwords to analyze (comma separated): ");
        String[] passwords = scanner.nextLine().split(",");
        
        System.out.print("Enter desired length for generated password: ");
        int length = scanner.nextInt();
        
        System.out.println("\nPASSWORD ANALYSIS RESULTS:");
        System.out.println("=".repeat(90));
        System.out.printf("%-20s %-8s %-4s %-4s %-4s %-4s %-8s %-10s%n", 
            "Password", "Length", "Up", "Low", "Dig", "Spec", "Score", "Strength");
        System.out.println("-".repeat(90));

        
        for (String pwd : passwords) {
            pwd = pwd.trim();
            if (pwd.isEmpty()) continue;
            
            int upper = countUppercase(pwd);
            int lower = countLowercase(pwd);
            int digits = countDigits(pwd);
            int special = countSpecial(pwd);
            int score = calculateScore(pwd, upper, lower, digits, special);
            String strength = getStrengthLevel(score);
            
            System.out.printf("%-20s %-8d %-4d %-4d %-4d %-4d %-8d %-10s%n", 
                pwd, pwd.length(), upper, lower, digits, special, score, strength);
        }
        
        String generatedPwd = generatePassword(length);
        System.out.println("\nGenerated Strong Password: " + generatedPwd);
    }
    
    private static int countUppercase(String pwd) {
        int count = 0;
        for (char c : pwd.toCharArray()) {
            if (c >= 65 && c <= 90) count++;
        }
        return count;
    }
    
    private static int countLowercase(String pwd) {
        int count = 0;
        for (char c : pwd.toCharArray()) {
            if (c >= 97 && c <= 122) count++;
        }
        return count;
    }
    
    private static int countDigits(String pwd) {
        int count = 0;
        for (char c : pwd.toCharArray()) {
            if (c >= 48 && c <= 57) count++;
        }
        return count;
    }
    
    private static int countSpecial(String pwd) {
        int count = 0;
        for (char c : pwd.toCharArray()) {
            if ((c >= 33 && c <= 47) || (c >= 58 && c <= 64) || 
                (c >= 91 && c <= 96) || (c >= 123 && c <= 126)) {
                count++;
            }
        }
        return count;
    }
    
    private static int calculateScore(String pwd, int upper, int lower, int digits, int special) {
        int score = 0;
        
        // Length points
        score += Math.max(0, pwd.length() - 8) * 2;
        
        // Character variety points
        if (upper > 0) score += 10;
        if (lower > 0) score += 10;
        if (digits > 0) score += 10;
        if (special > 0) score += 10;
        
        // Deduct for common patterns
        String[] patterns = {"123", "abc", "qwerty", "password", "admin", "letmein"};
        for (String pattern : patterns) {
            if (pwd.toLowerCase().contains(pattern)) {
                score -= 15;
            }
        }
        
        return Math.max(0, score);
    }
    
    private static String getStrengthLevel(int score) {
        if (score <= 20) return "Weak";
        if (score <= 50) return "Medium";
        return "Strong";
    }
    
    private static String generatePassword(int length) {
        if (length < 8) length = 12;
        
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String special = "!@#$%^&*()_+-=[]{}|;:,.<>?";
        
        Random random = new Random();
        StringBuilder password = new StringBuilder();
        
        // Ensure at least one of each type
        password.append(upper.charAt(random.nextInt(upper.length())));
        password.append(lower.charAt(random.nextInt(lower.length())));
        password.append(numbers.charAt(random.nextInt(numbers.length())));
        password.append(special.charAt(random.nextInt(special.length())));
        
        // Fill remaining positions
        String allChars = upper + lower + numbers + special;
        for (int i = 4; i < length; i++) {
            password.append(allChars.charAt(random.nextInt(allChars.length())));
        }
        
        // Shuffle the password
        return shuffleString(password.toString());
    }
    
    private static String shuffleString(String input) {
        List<Character> chars = new ArrayList<>();
        for (char c : input.toCharArray()) {
            chars.add(c);
        }
        Collections.shuffle(chars);
        
        StringBuilder result = new StringBuilder();
        for (char c : chars) {
            result.append(c);
        }
        return result.toString();
    }
}