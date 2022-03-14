package 땃쥐;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1463 {

    private static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        br.close();
        dp = new int[N+1];
        System.out.print(dp(N));
    }

    private static int dp(int k) {
        if (k==0||k==1||dp[k] != 0) return dp[k];
        else {
            if (k%6 == 0) {
                dp[k] = Math.min(Math.min(dp(k/3), dp(k/2)), dp(k-1)) + 1;
            } else if (k%3 ==0) {
                dp[k] = Math.min(dp(k/3),dp(k-1)) +1;
            } else if (k%2 ==0) {
                dp[k] = Math.min(dp(k/2),dp(k-1)) +1;
            } else {
                dp[k] = dp(k-1) + 1;
            }
        }
        return dp[k];
    }
}
