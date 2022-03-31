import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Math.min;

public class BOJ_1463 {

    private static Integer[] register;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        register = new Integer[n+1];

        register[0] = 0;
        register[1] = 0;

        System.out.println(counting(n));
    }

    private static int counting(int num) {

        if (register[num] == null) {
            if (num % 6 == 0) {
                register[num] = min(counting(num - 1), min(counting(num / 3), counting(num / 2))) + 1;
            } else if (num % 3 == 0) {
                register[num] = min(counting(num / 3), counting(num - 1)) + 1;
            } else if (num % 2 == 0) {
                register[num] = min(counting(num / 2), counting(num - 1)) + 1;
            } else {
                register[num] = counting(num - 1) + 1;
            }
        }

        return register[num];
    }

}
