package 아더;

import java.io.*;

public class BOJ_1463_1로만들기 {

    static int N;
    static int DP[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        DP = new int[N + 1];

        solve();
    }

    private static void solve() {
        // 초기값 셋팅
        DP[1] = 0;

        // DP배열을 처음부터 최댓값까지 채워간다
        for (int i = 2; i <= N; i++) {
            DP[i] = DP[i - 1] + 1; // 이전 값에서 -1 연산을 한 번 더함

            if (i % 2 == 0) DP[i] = Math.min(DP[i], DP[i / 2] + 1);
            if (i % 3 == 0) DP[i] = Math.min(DP[i], DP[i / 3] + 1);
        }

        // 정답 출력
        System.out.println(DP[N]);
    }
}
