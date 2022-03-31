package 땃쥐;

import java.util.LinkedList;
import java.util.Queue;

public class Programmers_81302 {

    private static final char PERSON = 'P';
    private static final char EMPTY = 'O';

    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];

        for (int numberOfPlace = 0; numberOfPlace < places.length; numberOfPlace++) { // 각 대기실마다 안전성 여부를 판단한다.
            answer[numberOfPlace] =
                    isSafePlace(places[numberOfPlace])
                            ? 1
                            : 0;
        }
        return answer;
    }

    private boolean isSafePlace(String[] place) {
        for (int row = 0; row < 5; row++) {
            for (int column = 0; column < 5; column++) { // 모든 행 모든 열을 탐색하여
                if (place[row].charAt(column) == PERSON) { // 사람을 찾았을 때
                    if (!isSafeAroundOf(row, column, place)) { // 너비탐색을 하여 위험한 사람이 있는지 판단
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean isSafeAroundOf(int row, int column, String[] place) {
        int[] rowDiffs = {-1, 1, 0, 0}; // 상, 하, 좌, 우 각각의 행 인덱스 차
        int[] columnDiffs = {0, 0, -1, 1}; // 상, 하, 좌, 우 각각의 열 인덱스 차

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{row, column}); // 시작점

        while (!queue.isEmpty()) {
            int[] space = queue.poll(); // 큐에 저장된 위치를 꺼내기

            for (int i = 0; i < 4; i++) { // 상하좌우 모든 위치에 대하여
                int nearRow = space[0] + rowDiffs[i];
                int nearColumn = space[1] + columnDiffs[i];

                if (nearColumn < 0 || nearColumn >= 5 || nearRow < 0 || nearRow >= 5 || (nearRow==row && nearColumn==column)) { // 위치값이 범위를 벗어거나 같은 좌표면 더 탐색하지 않음
                    continue;
                }

                int distance = Math.abs(nearRow - row) + Math.abs(nearColumn - column); // 맨해튼 거리

                if (distance <= 2 && place[nearRow].charAt(nearColumn) == PERSON) { // 맨해튼 거리 상으로 2 이내(상하좌우2 또는 대각선)이고 사람이 있을 경우
                    return false;
                } else if (distance < 2 && place[nearRow].charAt(nearColumn) == EMPTY) {
                    // 맨해튼 거리 상으로 2보다 작은 경우(상하좌우)에 빈 테이블이 놓여질 경우,
                    // 그 지점 기준 상하좌우 범위는 의심 범위
                    queue.offer(new int[]{nearRow, nearColumn});
                } else { // 그 외의 경우(블록이 있거나, 거리상으로 범위를 벗어난 경우)에는 더 이상 너비 탐색을 하지 않는다.
                    continue;
                }
            }
        }
        return true;
    }

}
