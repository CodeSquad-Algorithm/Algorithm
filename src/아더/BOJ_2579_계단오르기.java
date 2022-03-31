package 아더;

import java.io.*;

public class BOJ_2579_계단오르기 {
    static int N;
    static int[] stairs;
    static int[][] DP;

    public static void main(String[] args) {
        try {
            input();
            solve();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void solve() {
        // 초기값 설정
        DP[1][0] = stairs[1]; // 0은 i-1번째 계단 안밟고 i의 최댓값
        DP[1][1] = stairs[1]; // 1은 i-1번째 계단 밟고 i의 최댓값
        DP[2][0] = stairs[1];
        DP[2][1] = stairs[1] + stairs[2];

        // 점화식 생성
        for (int i = 3; i <= N; i++) {
            DP[i][0] = Math.max(DP[i - 2][0] + stairs[i], DP[i - 2][1] + stairs[i]);
            DP[i][1] = DP[i - 1][0] + stairs[i];
        }

        // 결과 출력
        System.out.println(Math.max(DP[N][0], DP[N][1]));
    }

    private static void input() throws IOException {
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader(new File("C:\\Users\\k2j38\\OneDrive\\Desktop\\Park\\99.ETC\\input.txt")));

        N = Integer.parseInt(br.readLine());

        stairs = new int[N + 1];
        DP = new int[N + 1][2];

        for (int i = 0; i < N; i++) {
            stairs[i + 1] = Integer.parseInt(br.readLine());
        }
    }
}
