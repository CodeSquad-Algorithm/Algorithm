package 땃쥐;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.IntBinaryOperator;

public class BOJ_14888_1 {

    private static int max = Integer.MIN_VALUE;
    private static int min = Integer.MAX_VALUE;

    private static int[] numbers;
    private static List<IntBinaryOperator> functions = new ArrayList<>();

    private static boolean visited[];
    private static IntBinaryOperator plus = (x, y) -> x + y;
    private static IntBinaryOperator minus = (x, y) -> x - y;
    private static IntBinaryOperator multiply = (x, y) -> x * y;
    private static IntBinaryOperator divide = (x, y) -> x / y;

    private static int N;

    public static void main(String[] args) throws IOException {
        N = readInt();
        numbers = new int[N];
        visited = new boolean[N - 1];

        for (int i = 0; i < N; i++) {
            numbers[i] = readInt();
        }

        int numberOfPlus = readInt();
        int numberOfMinus = readInt();
        int numberOfMultiply = readInt();
        int numberOfDivide = readInt();

        for (int i = 0; i < numberOfPlus; i++) {
            functions.add(plus);
        }
        for (int i = 0; i < numberOfMinus; i++) {
            functions.add(minus);
        }
        for (int i = 0; i < numberOfMultiply; i++) {
            functions.add(multiply);
        }
        for (int i = 0; i < numberOfDivide; i++) {
            functions.add(divide);
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
        for (int i = 0; i < N - 1; i++) { // 0,1,2,3,...,N-2
            if (!visited[i]) {
                visited[i] = true;
                bt(functions.get(i).applyAsInt(currentValue, numbers[depth + 1]), depth + 1);
                visited[i] = false;
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
