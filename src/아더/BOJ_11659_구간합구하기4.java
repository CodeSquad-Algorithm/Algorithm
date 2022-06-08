package 아더;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_11659_구간합구하기4 {

    static int N, M, START, END, ANSWER;
    static int[] nums, sum;

    public static void main(String[] args) {
        try {
            input();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void solve() {
        ANSWER = 0;

        if (START - 2 < 0) {
            System.out.println(sum[END - 1]);
        } else {
            System.out.println(sum[END - 1] - sum[START - 2]);
        }
    }

    private static void input() throws IOException {
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader(new File("/Users/woo-jinpark/Desktop/Park/05_Test/input/input.txt")));

        String[] split = br.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);
        nums = new int[N];
        sum = new int[N];

        split = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(split[i]);
            if (i > 0) {
                sum[i] += sum[i - 1] + nums[i];
            } else {
                sum[i] += nums[i];
            }


        }

        for (int i = 0; i < M; i++) {
            split = br.readLine().split(" ");
            START = Integer.parseInt(split[0]);
            END = Integer.parseInt(split[1]);

            solve();
        }
    }
}
