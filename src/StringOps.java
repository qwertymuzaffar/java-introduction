public class StringOps {
    public static void main(String s[]) {
        String s1 = "Hello World";
        System.out.println(s1);
        String s2 = new String("Hello World");
        System.out.println(s2);
        String s3 = "Hello World";

        System.out.println("s1 and s2 comparison "+ (s1 == s2));

        System.out.println("s2 and s3 comparison "+ (s2 == s3));

        System.out.println("s1 and s3 comparison "+ (s1 == s3));
    }
}
