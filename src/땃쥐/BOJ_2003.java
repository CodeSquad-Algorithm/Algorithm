package 땃쥐;

import java.io.IOException;

public class BOJ_2003 {

    public static void main(String[] args) throws IOException {
        int N = readInt();
        int M = readInt();

        int[] sums = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            sums[i] = sums[i - 1] + readInt();
        }

        int count = 0;
        for (int point1 = 1, point2 = 1; point1 <= N && point2 <= N; ) {
            int sum = sums[point2] - sums[point1 - 1];
            if (sum >= M) {
                point1++;
                point2 = point1;
                if (sum == M) {
                    count++;
                }
            } else {
                point2++;
            }
        }
        System.out.print(count);
    }

    private static int readInt() throws IOException {
        int value = 0;
        while (true) {
            int input = System.in.read();
            if (input == ' ' || input == '\n') {
                return value;
            } else {
                value = value * 10 + (input - 48);
            }
        }

    }
}