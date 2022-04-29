package 아더;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_7576_토마토 {

    static int N, M, ANSWER;
    static int[][] boxes, dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};


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
        System.out.println(ANSWER);
    }

    private static void solve() {
        // 모두 익었는지 판단
        if (isAllRipe()) {
            ANSWER = 0;
            return ;
        }

        // 큐 생성
        Queue<Integer> que = new LinkedList<>();

        // 토마토 위치 전부 담기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (boxes[i][j] == 1) {
                    que.add(i);
                    que.add(j);
                }
            }
        }

        // 큐가 빌 때까지 반복한다
        while (!que.isEmpty()) {
            int x = que.poll();
            int y = que.poll();

            // 4방향 모두 확인
            for (int k = 0; k < 4; k++) {
                int nx = x + dir[k][0];
                int ny = y + dir[k][1];

                // 범위 벗어나는지 체크
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if (boxes[nx][ny] != 0) continue;

                boxes[nx][ny] = boxes[x][y] + 1;
                que.add(nx);
                que.add(ny);
            }
        }

        // 안익은 토마토가 있는지 검사
        if (!isAllRipe()) {
            ANSWER = -1;
        } else {
            ANSWER = getMaxRipeDay() - 1;
        }
    }

    private static int getMaxRipeDay() {
        int maxDay = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                maxDay = Math.max(maxDay, boxes[i][j]);
            }
        }

        return maxDay;
    }

    private static boolean isAllRipe() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (boxes[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void input() throws IOException {
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader(new File("/Users/woo-jinpark/Desktop/Park/05_Test/input/input.txt")));

        String[] split = br.readLine().split(" ");
        M = Integer.parseInt(split[0]);
        N = Integer.parseInt(split[1]);
        ANSWER = 0;

        boxes = new int[N][M];

        for (int i = 0; i < N; i++) {
            split = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                boxes[i][j] = Integer.parseInt(split[j]);
            }
        }
    }
}
