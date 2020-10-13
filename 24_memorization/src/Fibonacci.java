import java.util.HashMap;
import java.util.Map;

public class Fibonacci {
    private Map<Integer, Integer> map;

    Fibonacci() {
        map = new HashMap<>();
    }

    public int calculate(int n) {
        if (n==0 || n==1) {
            return n;
        }
        if (this.map.containsKey(n)) {
            return map.get(n);
        }

        int result = calculate(n-1) + calculate(n-2);
        this.map.put(n, result);
        return result;
    }


    public static void main(String[] args) {
        Fibonacci f = new Fibonacci();
        System.out.println(f.calculate(5));
        System.out.println(f.calculate(6));
        System.out.println(f.map);
    }
}