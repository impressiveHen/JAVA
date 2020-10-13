import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        int[] a = new int[]{-1, 3, -99, 18, 4};
        Arrays.sort(a);
        Arrays.stream(a).forEach(i -> System.out.print(i + " "));
    }
}
