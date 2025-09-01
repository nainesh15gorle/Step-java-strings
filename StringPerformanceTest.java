import java.util.*;

public class StringPerformanceTest {

    // String Concatenation using '+'
    public static long testStringConcat(int iterations) {
        long start = System.currentTimeMillis();
        String str = "";
        for (int i = 0; i < iterations; i++) {
            str += "x"; // new object created each time
        }
        long end = System.currentTimeMillis();
        System.out.printf("%-15s %-20d %-20d %-20s\n", "String (+)", (end - start), str.length(), "Low (Immutable)");
        return (end - start);
    }

    // StringBuilder
    public static long testStringBuilder(int iterations) {
        long start = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < iterations; i++) {
            sb.append("x");
        }
        long end = System.currentTimeMillis();
        System.out.printf("%-15s %-20d %-20d %-20s\n", "StringBuilder", (end - start), sb.length(), "High (Non-synchronized)");
        return (end - start);
    }

    // StringBuffer
    public static long testStringBuffer(int iterations) {
        long start = System.currentTimeMillis();
        StringBuffer sbuf = new StringBuffer();
        for (int i = 0; i < iterations; i++) {
            sbuf.append("x");
        }
        long end = System.currentTimeMillis();
        System.out.printf("%-15s %-20d %-20d %-20s\n", "StringBuffer", (end - start), sbuf.length(), "High (Synchronized)");
        return (end - start);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of iterations (e.g. 1000, 10000, 100000): ");
        int iterations = sc.nextInt();

        System.out.println("\n================ Performance Analysis ================");
        System.out.printf("%-15s %-20s %-20s %-20s\n", "Method", "Time (ms)", "Final Length", "Memory Efficiency");
        System.out.println("---------------------------------------------------------------");

        // Run tests
        testStringConcat(iterations);
        testStringBuilder(iterations);
        testStringBuffer(iterations);

        sc.close();
    }
}
