package 제이.week1;

import java.util.Scanner;
import java.awt.Point;

public class BOJ_11660{

    int size;
    int rangeCount;

    int[][] matrix;
    long[][] matrixNestedVSum;
    Point[][] ranges;
    long[] rangeSum;


    private void solve() {
        readInput();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < ranges.length; i++) {
            Point[] range = ranges[i];
            Point start = range[0];
            Point end = range[1];
            if (pointEquals(start, end)) {
                sb.append(matrix[start.x][start.y])
                        .append("\n");
                continue;
            }
            sb.append(getRangeSumOnMatrix(start, end))
                    .append("\n");
        }

        System.out.println(sb);
    }

    private boolean pointEquals(Point a, Point b) {
        return a != null && b != null &&
                a.x == b.x && a.y == b.y;
    }

    private long getRangeSumOnMatrix(Point start, Point end) {
        int prevX = (start.x > 0) ? start.x - 1 : -1;
        long sum = 0;
        for (int i = start.y; i <= end.y; i++) {
            long prevSum = (prevX >= 0) ? matrixNestedVSum[prevX][i] : 0;
            sum += matrixNestedVSum[end.x][i] - prevSum;
        }

        return sum;
    }

    private void readInput() {
        Scanner sc = new Scanner(System.in);

        size = sc.nextInt();
        rangeCount = sc.nextInt();

        matrix = new int[size][size];
        matrixNestedVSum = new long[size][size];
        ranges = new Point[rangeCount][2];
        rangeSum = new long[rangeCount];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int num = sc.nextInt();
                matrix[i][j] = num;
                if (i == 0) matrixNestedVSum[i][j] = num;
                else matrixNestedVSum[i][j] = matrixNestedVSum[i-1][j] + num;
            }
        }

        for (int i = 0; i < rangeCount; i++) {
            for (int j = 0; j < 2; j++) {
                int x = sc.nextInt() - 1;
                int y = sc.nextInt() - 1;
                ranges[i][j] = new Point(x, y);
            }
        }
    }

    public static void main(String[] args) {
        BOJ_11660 q = new BOJ_11660();
        q.solve();
    }
}
