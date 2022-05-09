package 땃쥐;

import java.io.IOException;

public class BOJ_1932 {

    public static void main(String[] args) throws IOException {
        int n = readInt();
        int[][] maxNumbers = initMaxNumbers(n);

        calculateEachMaxNumbers(n, maxNumbers);
        int bottomMax = getBottomMax(n, maxNumbers);
        System.out.print(bottomMax);
    }

    private static int getBottomMax(int n, int[][] maxNumbers) {
        int bottomMax = 0;

        for (int k = 1; k<= n; k++) {
            bottomMax = Math.max(bottomMax, maxNumbers[n][k]);
        }
        return bottomMax;
    }

    private static void calculateEachMaxNumbers(int n, int[][] maxNumbers) throws IOException {
        for (int i = 1; i<= n; i++) {
            for (int j=1; j<=i; j++) {
                maxNumbers[i][j] = Math.max(maxNumbers[i-1][j-1], maxNumbers[i-1][j]) + readInt();
            }
        }
    }

    private static int[][] initMaxNumbers(int n) {
        int[][] maxNumbers = new int[n +1][];

        for (int i = 0; i< n +1; i++) {
            maxNumbers[i] = new int[i+2];
        }
        return maxNumbers;
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
