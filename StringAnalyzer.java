public class StringAnalyzer {
    public static void main(String[] args) {
        // Comparison
        String a = "Java", b = "java", c = "Java";
        System.out.println("==: " + (a == c));           // true (string pool)
        System.out.println("equals: " + a.equals(b));     // false
        System.out.println("equalsIgnoreCase: " + a.equalsIgnoreCase(b)); // true

        // Performance
        long start = System.nanoTime();
        String slow = a + b + c;  // Slower (+ creates 3 objects)
        System.out.println("+ time: " + (System.nanoTime() - start) + "ns");

        start = System.nanoTime();
        String fast = new StringBuilder().append(a).append(b).append(c).toString();  // Faster
        System.out.println("Builder time: " + (System.nanoTime() - start) + "ns");
    }
}