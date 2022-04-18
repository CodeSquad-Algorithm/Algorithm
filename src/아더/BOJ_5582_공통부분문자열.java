package 아더;

import java.io.*;
import java.util.Arrays;

public class BOJ_5582_공통부분문자열 {
    static String S1, S2;
    static int[][] LCS;

    public static void main(String[] args) {
        try {
            input();
            solve();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void solve() {
        for (int i = 0; i < S1.length(); i++) {
            for (int j = 0; j < S2.length(); j++) {
                if (i == 0 || j == 0) {
                    LCS[i][j] = 0;
                } else if (S1.charAt(i - 1) == S2.charAt(j - 1)) {
                    LCS[i][j] = LCS[i - 1][j - 1] + 1;
                }
            }
        }

        int max = Arrays.stream(LCS)
                .flatMapToInt(Arrays::stream)
                .max()
                .getAsInt();

        System.out.println(max);
    }

    private static void input() throws IOException {
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader(new File("/Users/woo-jinpark/Desktop/Park/05_Test/input/input.txt")));

        S1 = br.readLine();
        S2 = br.readLine();
        LCS = new int[S1.length() + 1][S2.length() + 1];
    }
}
