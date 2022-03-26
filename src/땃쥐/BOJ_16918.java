package 땃쥐;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16918 {

    private static int row;
    private static int column;
    private static int time;
    private static int[][] bombCounter;

    private static int[] rowDiff = {-1, 1, 0, 0};
    private static int[] columnDiff = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        row = Integer.parseInt(st.nextToken()); // row
        column = Integer.parseInt(st.nextToken()); // column
        time = Integer.parseInt(st.nextToken()); // 시간

        bombCounter = new int[row][column];

        for (int i = 0; i < row; i++) {
            String line = br.readLine();
            for (int j = 0; j < column; j++) {
                bombCounter[i][j] = (line.charAt(j) == 'O') // 1초일 때 상태 초기화
                        ? 2
                        : 0;
            }
        }
        for (int t = 1; t < time; t++) {
            process(t);
        }
        br.close();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (bombCounter[i][j] > 0) {
                    sb.append('O');
                } else {
                    sb.append('.');
                }
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }

    private static void process(int currentTime) { // 1초가 진행됨.
        if (currentTime % 2 != 0) {
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    if (bombCounter[i][j] == 0) {
                        bombCounter[i][j] = 3;
                    } else {
                        bombCounter[i][j]--;
                    }
                }
            }
        } else {
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    if (bombCounter[i][j] == 1) {
                        bomb(i, j);
                    } else if (bombCounter[i][j] == 0) {
                        continue;
                    } else {
                        bombCounter[i][j]--;
                    }
                }
            }
        }
    }

    private static void bomb(int currentRow, int currentColumn) {
        bombCounter[currentRow][currentColumn] = 0;

        for (int i = 0; i < 4; i++) {
            int nearRow = currentRow + rowDiff[i];
            int nearColumn = currentColumn + columnDiff[i];
            if (nearRow < 0 || nearRow >= row || nearColumn < 0 || nearColumn >= column || bombCounter[nearRow][nearColumn] == 1) {
                continue;
            }
            bombCounter[nearRow][nearColumn] = 0;
        }
    }
}
