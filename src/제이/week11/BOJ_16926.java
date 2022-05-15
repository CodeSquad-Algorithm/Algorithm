import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class BOJ_16926 {

    private static int N; // 세로
    private static int M; // 가로
    private static int R; // 회전수

    private static int[][] arr;

    public static void main(String[] args) throws IOException {
        readInput();
        solve();
    }

    private static void solve() {
        double midN = N / 2.0;
        double midM = M / 2.0;

        int[][] temp = new int[N][M];
        char[][] chrs = new char[N][M];

        for (int r = 0; r < R; r++) {

            for (int y = 0; y < arr.length; y++) {
                for (int x = 0; x < arr[0].length; x++) {
                    if (isDown(midM, y, x)) {
                        temp[y + 1][x] = arr[y][x];
//                    chrs[y][x] = 'v';
                    } else if (isUp(midM, y, x)) {
                        temp[y - 1][x] = arr[y][x];
//                    chrs[y][x] = '^';
                    } else if (isRight(midN, y, x)) {
                        temp[y][x + 1] = arr[y][x];
//                    chrs[y][x] = '>';
                    } else if (isLeft(midN, y, x)) {
                        temp[y][x - 1] = arr[y][x];
//                    chrs[y][x] = '<';
                    } else {
                        temp[y][x] = arr[y][x];
//                    chrs[y][x] = 'O';
                    }
                }
            }

            if (r == R - 1) break;

            for (int i = 0; i < arr.length; i++) {
                arr[i] = Arrays.copyOf(temp[i], temp[i].length);
            }
        }

        StringBuilder sb = new StringBuilder();

//        for (char[] chr : chrs) {
//            for (char c : chr) {
//                System.out.print(c + " ");
//            }
//            System.out.println();
//        }

        for (int[] row : temp) {
            String line = Arrays.stream(row)
                    .mapToObj(String::valueOf)
                    .reduce((nested, element) -> nested + " " + element)
                    .get();
            sb.append(line);
            sb.append("\n");
        }

        System.out.println(sb);


    }

    private static boolean isDown(double midM, int y, int x) {

        return x < (int) midM && x <= y && y < N - (x + 1);
    }

    private static boolean isUp(double midM, int y, int x) {
        return x >= midM && y >= (M - x) && y <= N - (M - x);
    }

    private static boolean isLeft(double midN, int y, int x) {
        return y < midN && x > y && x < (M - y);
    }

    private static boolean isRight(double midN, int y, int x) {
        return y >= midN && x >= N - (y + 1) && x < M - (N - y);
    }

    private static void readInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());
        R = parseInt(st.nextToken());

        arr = new int[N][M];

        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());

            for (int x = 0; x < M; x++) {
                arr[y][x] = parseInt(st.nextToken());
            }
        }
    }
}
/*
5 5 2
1 2 3 4 5
6 7 8 9 10
11 12 13 14 15
16 17 18 19 20
21 22 23 24 25

3 2 1
1 2
6 3
5 4

5 3 1
1 2 3
4 5 6
7 8 9
10 11 12
13 14 15

 */


/*
- down
    x < midX && x <= y && y < N - (x + 1)
- up
    x > midX && x >= y && y >= (M - x)
- left
    y < midN && x > y && x < (M - y)
- right
    y > midN && x < y && x >= M - (y + 1)
 */