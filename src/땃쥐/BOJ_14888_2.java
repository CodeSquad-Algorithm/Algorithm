package 땃쥐;

import java.io.IOException;

public class BOJ_14888_2 {


    private static int max = Integer.MIN_VALUE;
    private static int min = Integer.MAX_VALUE;

    private static int[] numbers;
    private static int[] operatorCounter = new int[4];
    private static int N;

    public static void main(String[] args) throws IOException {
        N = readInt();
        numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = readInt();
        }
        for (int i=0; i<4; i++) {
            operatorCounter[i] = readInt();
        }
        bt(numbers[0], 0);
        StringBuilder sb = new StringBuilder();
        sb.append(max).append('\n').append(min);
        System.out.print(sb);
    }

    public static void bt(int currentValue, int depth) {
        if (depth == N - 1) {
            max = Math.max(max, currentValue);
            min = Math.min(min, currentValue);
            return;
        }
        for (int i = 0; i < 4; i++) {
            if (operatorCounter[i] >0) {
                operatorCounter[i] --;

                if (i==0) {
                    bt(currentValue + numbers[depth + 1], depth + 1);
                } else if (i==1) {
                    bt(currentValue - numbers[depth + 1], depth + 1);
                } else if (i==2) {
                    bt(currentValue * numbers[depth + 1], depth + 1);
                } else {
                    bt(currentValue / numbers[depth + 1], depth + 1);
                }

                operatorCounter[i] ++;
            }
        }
    }

    private static int readInt() throws IOException {
        int value = 0;
        boolean negative = false;

        int input;
        while (true) {
            input = System.in.read();
            if (input == ' ' || input == '\n') {
                return (negative) ? -value : value;
            } else if (input == '-') {
                negative = true;
            } else {
                value = value * 10 + (input - 48);
            }
        }
    }
}
