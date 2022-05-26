package 땃쥐;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Programmers86052 {

    private static final int COUNT_OF_DIRECTION = 4; // 동, 남, 서, 북
    private static final int[] DIRECTION_X = {1, 0, -1, 0}; // 동 남 서 북
    private static final int[] DIRECTION_Y = {0, 1, 0, -1}; // 동 남 서 북

    private static int WIDTH;
    private static int HEIGHT;

    private static boolean[][][] isVisited; // 각 칸별로 상하 좌우 방향으로 빛이 사출된 이력

    public int[] solution(String[] grid) {

        List<Integer> answer = new ArrayList<>();
        HEIGHT = grid.length;
        WIDTH = grid[0].length();

        isVisited = new boolean[HEIGHT][WIDTH][COUNT_OF_DIRECTION];

        for (int row = 0; row < HEIGHT; row++) {
            for (int column = 0; column < WIDTH; column++) {
                for (int d = 0; d < COUNT_OF_DIRECTION; d++) {
                    if (!isVisited[row][column][d]) {
                        answer.add(light(grid, row, column, d));
                    }
                }
            }
        }
        return answer.stream()
                .sorted()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    private int light(String[] grid, int row, int column, int d) {
        int count = 0;

        while (true) {
            if (isVisited[row][column][d]) { // 해당 방향으로 빛이 사출된 이력이 있는 경우 계산하지 않는다.
                break;
            }
            count++; // 거리 1 증가
            isVisited[row][column][d] = true; // 방문 처리
            if (grid[row].charAt(column) == 'L') { // 화살표를 반시계로 회전해야함. d를 -1만큼 움직여야함.
                d = (d == 0) ? 3 : d - 1; // 반시계방향 (좌회전)
            } else if (grid[row].charAt(column) == 'R') { // 화살표를 시계방향으로 회전해야함. d를 +1만큼 움직여야함.
                d = (d == 3) ? 0 : d + 1; // 시계방향 (우회전)
            }

            row = (row + DIRECTION_Y[d] + HEIGHT) % HEIGHT; // 방향을 더했을 때 음이 될 수 있어서 최대 높이/너비 값을 한번 더하고 나머지 처리 해야함
            column = (column + DIRECTION_X[d] + WIDTH) % WIDTH;
        }
        return count;
    }

}
