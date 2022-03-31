package 땃쥐;

import java.io.IOException;

public class BOJ_2579 {

    private static int[] stair;
    private static Integer[] maxScores;

    public static void main(String[] args) throws IOException {
        int N = readInt();

        stair = new int[N + 1];
        maxScores = new Integer[N + 1];

        for (int i = 1; i <= N; i++) {
            stair[i] = readInt(); // 계단 초기화
        }

        maxScores[0] = stair[0];
        maxScores[1] = stair[1];

        if (N >= 2) {
            maxScores[2] = stair[1] + stair[2];
        }
        int maxScore = calMaxScore(N);
        System.out.print(maxScore);
    }

    private static int calMaxScore(int k) {
        if (maxScores[k] == null) {
            maxScores[k] = Math.max(calMaxScore(k - 3) + stair[k - 1], calMaxScore(k - 2)) + stair[k];
        }
        return maxScores[k];
    }

    private static int readInt() throws IOException {
        int value = 0;
        while (true) {
            // 입력 문자의 ASCII코드 값.
            // 가령 '0'이 들어왔으면 숫자 0이 아니라 '0'의 ASCII 코드값인 48이다.
            int input = System.in.read();
            if (input == '\n') { // 개행문자면 연산을 끊음
                return value; // 그냥 반환
            } else {
                value = value * 10 + (input - 48); // 기존 값을 10배하고 입력된 추가값을 파싱하여 더함
            }
        }
    }
}
