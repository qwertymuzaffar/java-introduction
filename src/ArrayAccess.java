public class ArrayAccess {
    public static void main(String s[]) {
        int years[] = {2020,2021,2022,2023,2024,2025};
        int count_years = years.length;
        System.out.println("the length of the array is " + count_years);
        for (int year : years) {
            System.out.println(year);
        }
    }
}
