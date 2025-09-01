import java.util.*;

public class TextCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Text-Based Calculator ===");
        System.out.println("Enter mathematical expressions (type 'exit' to quit):");
        
        while (true) {
            System.out.print("\nExpression: ");
            String expression = scanner.nextLine().trim();
            
            if (expression.equalsIgnoreCase("exit")) {
                break;
            }
            
            if (expression.isEmpty()) {
                continue;
            }
            
            if (!isValidExpression(expression)) {
                System.out.println("Invalid expression!");
                continue;
            }
            
            try {
                double result = evaluateExpression(expression);
                System.out.printf("Result: %.2f%n", result);
            } catch (Exception e) {
                System.out.println("Error evaluating expression: " + e.getMessage());
            }
        }
        scanner.close();
    }
    
    private static boolean isValidExpression(String expr) {
        // Check for invalid characters
        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);
            if (!isValidChar(c)) {
                return false;
            }
        }
        
        // Check operator placement and parentheses matching
        return hasValidOperators(expr) && hasMatchingParentheses(expr);
    }
    
    private static boolean isValidChar(char c) {
        return Character.isDigit(c) || c == '+' || c == '-' || c == '*' || c == '/' || 
               c == '.' || c == ' ' || c == '(' || c == ')';
    }
    
    private static boolean hasValidOperators(String expr) {
        expr = expr.replaceAll("\\s+", "");
        if (expr.isEmpty()) return false;
        
        // Check if starts or ends with operator (except minus for negative numbers)
        char first = expr.charAt(0);
        char last = expr.charAt(expr.length() - 1);
        if ("+*/".indexOf(first) >= 0 || "+-*/".indexOf(last) >= 0) {
            return false;
        }
        
        // Check consecutive operators
        for (int i = 0; i < expr.length() - 1; i++) {
            char current = expr.charAt(i);
            char next = expr.charAt(i + 1);
            if ("+-*/".indexOf(current) >= 0 && "+-*/".indexOf(next) >= 0) {
                return false;
            }
        }
        
        return true;
    }
    
    private static boolean hasMatchingParentheses(String expr) {
        int count = 0;
        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);
            if (c == '(') count++;
            if (c == ')') count--;
            if (count < 0) return false;
        }
        return count == 0;
    }
    
    private static double evaluateExpression(String expr) {
        // Remove spaces and handle parentheses first
        expr = expr.replaceAll("\\s+", "");
        expr = evaluateParentheses(expr);
        
        // Evaluate multiplication and division
        expr = evaluateOperations(expr, new char[]{'*', '/'});
        
        // Evaluate addition and subtraction
        expr = evaluateOperations(expr, new char[]{'+', '-'});
        
        return Double.parseDouble(expr);
    }
    
    private static String evaluateParentheses(String expr) {
        while (expr.contains("(")) {
            int start = expr.lastIndexOf('(');
            int end = expr.indexOf(')', start);
            
            if (end == -1) throw new RuntimeException("Mismatched parentheses");
            
            String innerExpr = expr.substring(start + 1, end);
            double result = evaluateExpression(innerExpr);
            
            // Replace the parenthetical expression with its result
            String before = expr.substring(0, start);
            String after = expr.substring(end + 1);
            expr = before + result + after;
        }
        return expr;
    }
    
    private static String evaluateOperations(String expr, char[] operators) {
        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);
            if (contains(operators, c)) {
                // Find the left operand
                int leftStart = findLeftOperandStart(expr, i - 1);
                double left = Double.parseDouble(expr.substring(leftStart, i));
                
                // Find the right operand
                int rightEnd = findRightOperandEnd(expr, i + 1);
                double right = Double.parseDouble(expr.substring(i + 1, rightEnd));
                
                // Perform operation
                double result = performOperation(c, left, right);
                
                // Replace the operation with result
                String before = expr.substring(0, leftStart);
                String after = expr.substring(rightEnd);
                expr = before + result + after;
                
                // Reset index to account for changed string length
                i = leftStart + String.valueOf(result).length() - 1;
            }
        }
        return expr;
    }
    
    private static int findLeftOperandStart(String expr, int index) {
        int start = index;
        while (start >= 0 && (Character.isDigit(expr.charAt(start)) || expr.charAt(start) == '.' || 
                             (expr.charAt(start) == '-' && (start == 0 || "+-*/".indexOf(expr.charAt(start - 1)) >= 0)))) {
            start--;
        }
        return start + 1;
    }
    
    private static int findRightOperandEnd(String expr, int index) {
        int end = index;
        while (end < expr.length() && (Character.isDigit(expr.charAt(end)) || expr.charAt(end) == '.' || 
                                     (end == index && expr.charAt(end) == '-'))) {
            end++;
        }
        return end;
    }
    
    private static double performOperation(char op, double left, double right) {
        switch (op) {
            case '+': return left + right;
            case '-': return left - right;
            case '*': return left * right;
            case '/': 
                if (right == 0) throw new RuntimeException("Division by zero");
                return left / right;
            default: throw new RuntimeException("Unknown operator: " + op);
        }
    }
    
    private static boolean contains(char[] array, char value) {
        for (char c : array) {
            if (c == value) return true;
        }
        return false;
    }
}