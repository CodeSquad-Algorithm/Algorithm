package 아더;

import java.io.*;

public class BOJ_16918_봄버맨 {
    static int R, C, N;
    static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static Area[][] maps;

    static class Area {
        int row, col, time;
        boolean isBomb;

        public Area(int row, int col, boolean isBomb) {
            this.row = row;
            this.col = col;
            this.time = 0;
            this.isBomb = isBomb;
        }

        public void increaseTime() {
            this.time++;
        }

        public boolean isExplosion() {
            return this.time >= 3;
        }

        public boolean isReadyExplosion() {
            return this.time == 2;
        }
    }

    public static void main(String[] args) {
        try {
            input();
            solve();
            print();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void print() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (maps[i][j].isBomb) {
                    sb.append("O");
                } else {
                    sb.append(".");
                }
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static void solve() {
        // N만큼의 시간초가 흐름
        for (int k = 1; k <= N; k++) {
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    // 1초 흐름
                    maps[i][j].increaseTime();

                    // 1초 지남
                    if (k == 1) {
                        continue;
                    }

                    // 모든 칸 폭탄 설치
                    if (k % 2 == 0) {
                        if (!maps[i][j].isBomb) {
                            maps[i][j] = new Area(i, j, true);
                        }
                        continue;
                    }

                    // 3초전 폭탄 폭발
                    if (maps[i][j].isBomb && maps[i][j].isExplosion()) {
                        maps[i][j] = new Area(i, j, false);
                        for (int l = 0; l < 4; l++) {
                            int ni = i + dir[l][0];
                            int nj = j + dir[l][1];

                            if (ni < 0 || nj < 0 || ni >= R || nj >= C) continue;
                            if (maps[ni][nj].isBomb && maps[ni][nj].isReadyExplosion()) continue;
                            maps[ni][nj] = new Area(ni, nj, false);
                        }
                    }
                }
            }
        }
    }

    private static void input() throws IOException {
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader(new File("C:\\Users\\k2j38\\OneDrive\\Desktop\\Park\\99.ETC\\input.txt")));

        String[] split = br.readLine().split(" ");
        R = Integer.parseInt(split[0]);
        C = Integer.parseInt(split[1]);
        N = Integer.parseInt(split[2]);
        maps = new Area[R][C];

        for (int i = 0; i < R; i++) {
            split = br.readLine().split("");
            for (int j = 0; j < C; j++) {
                if (split[j].equals(".")) {
                    maps[i][j] = new Area(i, j, false);
                } else {
                    maps[i][j] = new Area(i, j, true);
                }
            }
        }
    }
}
