package 땃쥐;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_7576 {

    private static final int[] DIRECTION_X = {0, 0, -1, 1}; // 상,하, 좌,우
    private static final int[] DIRECTION_Y = {-1, 1, 0, 0};

    private static int M; // 가로
    private static int N; // 세로
    private static int[][] storage; // 창고

    public static void main(String[] args) throws IOException {
        initStorage();
        bfs();
        System.out.print(answer());
    }

    private static void initStorage() throws IOException {
        M = readInt(); // 가로
        N = readInt(); // 세로

        storage = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                storage[i][j] = readInt();
            }
        }
    }

    private static void bfs() {
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (storage[i][j] == 1) { // 익은 토마토이면 큐에 좌표를 삽입
                    queue.offer(new int[]{j, i});
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] ripeTomato = queue.poll();
            int x = ripeTomato[0];
            int y = ripeTomato[1];

            for (int i = 0; i < 4; i++) {
                int nearX = x + DIRECTION_X[i];
                int nearY = y + DIRECTION_Y[i];

                if (nearX < 0 || nearY < 0 || nearX >= M || nearY >= N) { // 인덱스 범위 밖을 벗어나면 무시
                    continue;
                }

                if (storage[nearY][nearX] == -1) { // 빈 공간일 경우 무시
                    continue;
                }
                if (storage[nearY][nearX] == 0) { // 안 익은 토마토일 경우 숙성 처리. 값을 늘려서 숙성일을 누적시킨다.
                    storage[nearY][nearX] = storage[y][x] + 1;
                    queue.offer(new int[]{nearX, nearY});
                }
            }
        }
    }

    private static int answer() {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (storage[i][j] > max) {
                    max = storage[i][j]; // 최댓값 갱신
                }
                if (storage[i][j] == 0) {
                    return -1;
                }
            }
        }
       return max-1; // 모두 익었을 경우. 최댓값-1이 모두 숙성하는데 소요되는 날짜
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
