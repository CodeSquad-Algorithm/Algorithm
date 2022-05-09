package 땃쥐;

import java.io.IOException;

public class BOJ_14503 {

    public static void main(String[] args) throws IOException{
        Robot robot = initRobot();
        int result = robot.run();
        System.out.print(result);
    }

    private static Robot initRobot() throws IOException {
        int N = readInt();
        int M = readInt();

        int[][] map = new int[N][M];
        boolean[][] cleaned = new boolean[N][M];

        int y = readInt();
        int x = readInt();
        int d = readInt();

        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                map[i][j] = readInt();
            }
        }
        return new Robot(x,y,d, map, cleaned);
    }

    private static int readInt() throws IOException {
        int value = 0;
        while (true) {
            int input = System.in.read();
            if (input == ' ' || input == '\n') {
                return value;
            } else {
                value = value * 10 + (input - 48);
            }
        }
    }

}

class Robot {
    
    private static final int[][] leftDiff = {{-1, 0}, {0,-1}, {1,0}, {0,1}}; // 북, 동, 남, 서 각 방향 기준 왼쪽방향
    private static final int[][] foreDiff = {{0,-1}, {1,0}, {0,1}, {-1,0}}; // 북, 동, 남, 서 각 방향 기준 앞방향
    private static final int[][] backDiff = {{0, 1}, {-1,0}, {0,-1}, {1,0}}; // 북, 동, 남, 서 각 방향 기준 뒷방향

    private final int[][] map;
    private final boolean[][] cleaned;

    private int x; // 현재 x좌표
    private int y; // 현재 y좌표
    private int d; // 현재 방향

    Robot(int x, int y, int d, int[][] map, boolean[][] cleaned) {
        this.x = x;
        this.y = y;
        this.d = d;
        this.map = map;
        this.cleaned = cleaned;
    }

    private void turnLeft() {
        switch(d) {
            case 0 : d = 3; break;
            case 1 : d = 0; break;
            case 2 : d = 1; break;
            case 3 : d = 2; break;
        }
    }

    private void forward() {
        x = x + foreDiff[d][0];
        y = y + foreDiff[d][1];
    }

    private void backward() {
        x = x + backDiff[d][0];
        y = y + backDiff[d][1];
    }

    public int run() {

        int count = 0;
        int stopCount = 4;

        while (true) {
            if (stopCount == 4 && !cleaned[y][x]) { // 초기위치 또는 이동 직후 위치가 청소되어 있지 않으면
                cleaned[y][x] = true; // 청소한다.
                count ++;
            }

            if (stopCount > 0) {
                int leftX = x + leftDiff[d][0];
                int leftY = y + leftDiff[d][1];

                // 왼쪽을 확인
                if (!cleaned[leftY][leftX] && map[leftY][leftX] == 0) { // 왼쪽이 청소되어 있지 않고 빈 공간이다.
                    turnLeft();
                    forward();
                    stopCount = 4;
                } else { // 그렇지 아니하다. 회전
                    turnLeft();
                    stopCount --;
                }
            } else { // 4번 모두 회전했을 때
                int backX = x + backDiff[d][0];
                int backY = y + backDiff[d][1]; // 뒤를 확인한다.

                if (map[backY][backX] == 1) { // 뒤가 벽이다. -> 종료조건
                    break;
                } else { // 뒤가 빈공간이다. 뒤로 후진한다.
                    backward();
                    stopCount = 4;
                }
            }
        }
        return count;
    }
}
