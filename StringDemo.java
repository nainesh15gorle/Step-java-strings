public class StringDemo {
    public static void main(String[] args) {
                String s1 = "Hello";                       String s2 = new String("World");          char[] chars = {'J', 'a', 'v', 'a'};
        String s3 = new String(chars);   
      
        System.out.println("s1: " + s1);
        System.out.println("s2: " + s2);
        System.out.println("s3: " + s3);

              System.out.println("Length of s1: " + s1.length());

                String s4 = s1 + " " + s2;
        System.out.println("Concatenated s1 and s2: " + s4);

             System.out.println("Substring of s4 (0 to 5): " + s4.substring(0, 5));

               System.out.println("s3 in upper case: " + s3.toUpperCase());

               System.out.println("s4 in lower case: " + s4.toLowerCase());

                String s5 = s4.replace('o', '0');
        System.out.println("s4 with 'o' replaced by '0': " + s5);

               System.out.println("Does s4 contain 'World'? " + s4.contains("World"));
    }
}


