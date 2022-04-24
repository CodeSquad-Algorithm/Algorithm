package 땃쥐;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_5582 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str1 = br.readLine();
        String str2 = br.readLine();
        br.close();

        int length1 = str1.length();
        int length2 = str2.length();

        // 공통 길이 2차원 배열
        int[][] dp = new int[length1 + 1][length2 + 1];

        /**
         * i행 : 1번 문자열의 i번째 문자열까지
         * j열 : 2번 문자열의 j번째 문자열까지
         * dp[i][j] : 비교했을 때 공통 길이
         */

        int max = 0;

        for (int i = 1; i <= length1; i++) {
            for (int j = 1; j <= length2; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) { // 문자가 서로 일치할 때
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        System.out.print(max);

    }

}
