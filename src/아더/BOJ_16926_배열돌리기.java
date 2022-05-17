package 아더;

import java.io.*;

public class BOJ_16926_배열돌리기 {

    static int N, M, R, layer;
    static int[][] adj, dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

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
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(adj[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void solve() {
        rotate(R);
    }

    private static void rotate(int rotateNum) {
        for (int i = 0; i < rotateNum; i++) {
            for (int n = 0; n < layer; n++) {
                int tmp = adj[n][n];
                int edgeCnt = 0;
                int lx = n, ly = n;

                while (edgeCnt < 4) {
                    int ny = ly + dir[edgeCnt][1];
                    int nx = lx + dir[edgeCnt][0];

                    if (ny >= n && nx >= n && ny < N - n && nx < M - n) {
                        adj[ly][lx] = adj[ny][nx];
                        ly = ny;
                        lx = nx;
                    } else {
                        edgeCnt++;
                    }
                }
                adj[n + 1][n] = tmp;
            }
        }
    }

    private static void input() throws IOException {
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader(new File("/Users/woo-jinpark/Desktop/Park/05_Test/input/input.txt")));

        String[] split = br.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);
        R = Integer.parseInt(split[2]);
        layer = Math.min(N, M) / 2;
        adj = new int[N][M];

        for (int i = 0; i < N; i++) {
            split = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                adj[i][j] = Integer.parseInt(split[j]);
            }
        }
    }
}
