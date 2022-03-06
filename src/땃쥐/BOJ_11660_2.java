package 땃쥐;

import java.io.IOException;

public class BOJ_11660_2 {

    public static void main(String[] args) throws IOException {
        int N = readInt();
        int M = readInt();

        int[][] dp = new int[N+1][N+1];

        // 누적합
        for (int i=1; i<=N; i++) {
            for (int j=1; j<=N; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + readInt();
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i=0; i<M; i++) {
            int x1 = readInt();
            int y1 = readInt();
            int x2 = readInt();
            int y2 = readInt();

            int sum = dp[x2][y2] - dp[x1-1][y2] - dp[x2][y1-1] + dp[x1-1][y1-1];
            sb.append(sum).append('\n');
        }
        System.out.println(sb);
    }

    private static int readInt() throws IOException {
        int value = 0;
        while (true) {
            // 입력 문자의 ASCII코드 값.
            // 가령 '0'이 들어왔으면 숫자 0이 아니라 '0'의 ASCII 코드값인 48이다.
            int input = System.in.read();
            if (input == ' ' || input == '\n') { // 개행문자거나 공백이면 연산을 끊음
                return value; // 양수면 그냥 반환
            } else {
                value = value * 10 + (input - 48); // 기존 값을 10배하고 입력된 추가값을 파싱하여 더함
            }
        }
    }
}
