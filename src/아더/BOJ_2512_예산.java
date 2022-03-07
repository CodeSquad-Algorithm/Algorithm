package 아더;

import java.io.*;
import java.util.Arrays;

public class BOJ_2512_예산 {
    static int N, M;
    static int[] budgets;

    public static void main(String[] args) {

        try {
            input();
            solve();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void solve() {
        int L = 0, R = 1_000_000_000, answer = 0;

        if (Arrays.stream(budgets).sum() <= M) {
            answer = Arrays.stream(budgets).max().getAsInt();
            System.out.println(answer);
            return ;
        }

        Arrays.sort(budgets);
        binarySearch(L, R, answer);
    }

    private static void binarySearch(int L, int R, int answer) {
        while(L <= R) {
            int mid = (L + R) / 2;
            if (determination(mid)) {
                answer = mid;
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }

        System.out.println(answer);
    }

    private static boolean determination(int value) {
        long sum = 0;

        for (int i = 0; i < N; i++) {
            if (budgets[i] >= value) {
                sum += value;
            } else {
                sum += budgets[i];
            }
        }

        return sum <= M;
    }

    private static void input() throws IOException {
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader(new File("/Users/woo-jinpark/Desktop/Park/05_Test/input/input.txt")));

        N = Integer.parseInt(br.readLine());
        budgets = new int[N];

        String[] split = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            budgets[i] = Integer.parseInt(split[i]);
        }

        M = Integer.parseInt(br.readLine());
    }
}
