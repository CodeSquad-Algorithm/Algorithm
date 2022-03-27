import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_16918 {

    private static final char CHR_EMPTY = '.';
    private static final char CHR_BOMB = 'O';
    private static final int BOMB_INIT_TIME = 3;
    private static final int EMPTY = 0;

    private static int row;
    private static int col;
    private static int timeLimit;
    private static int[][] map;

    private static int[] x_move = {0,0,-1,1};
    private static int[] y_move = {-1,1,0,0};

    public static void main(String[] args) throws IOException {
        readInput();

        int time = 1;
        processBomb(map);

        boolean isInstallTurn = true;

        while(time < timeLimit) {
            time++;

            if (isInstallTurn) {
                installOrProcessBomb(map);
            } else {
                processBomb(map);
            }

            isInstallTurn = !isInstallTurn;
        }

        StringBuilder sb = new StringBuilder();
        for (int[] r : map) {
            for (int c : r) {
                if (c == 0) sb.append(CHR_EMPTY);
                else sb.append(CHR_BOMB);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void readInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstRow = br.readLine().split(" ");
        row = Integer.parseInt(firstRow[0]);
        col = Integer.parseInt(firstRow[1]);
        timeLimit = Integer.parseInt(firstRow[2]);
        map = new int[row][col];

        for (int y = 0; y < row; y++) {
            String line = br.readLine();
            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == CHR_BOMB)
                    map[y][x] = BOMB_INIT_TIME;
            }
        }
    }

    private static void installOrProcessBomb(int[][] map) {
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[0].length; x++) {
                if (map[y][x] == EMPTY) {
                    map[y][x] = BOMB_INIT_TIME;
                } else {
                    map[y][x]--;
                }
            }
        }
    }

    private static void processBomb(int[][] map) {
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[0].length; x++) {
                if (map[y][x] == EMPTY) continue;

                map[y][x]--;

                if (map[y][x] == EMPTY) {
                    fireBomb(map, x, y);
                }
            }
        }
    }

    private static void fireBomb(int[][] map, int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + x_move[i];
            int ny = y + y_move[i];

            if (ny < 0 || ny >= map.length || nx < 0 || nx >= map[0].length) continue;
            if (map[ny][nx] == 1) continue;

            map[ny][nx] = EMPTY;
        }
    }
}
