package 아더;

import java.util.Arrays;

public class Pro_81302_거리두기확인하기 {

    public static void main(String[] args) {
        Pro_81302_거리두기확인하기 sol = new Pro_81302_거리두기확인하기();
        String[][] places = {
                {"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
                {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
                {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},
                {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
                {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}};

        int[] solution = sol.solution(places);
        System.out.println(Arrays.toString(solution));
    }

    class People {
        int row;
        int col;
        boolean isVisited;

        public People(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    // 각 대기실은 5 X 5
    // 거리두기를 위해 맨해튼거리 2이하로 앉지말아라
        // 맨해튼거리 : |r1 - r2| + |c1 - c2|
    // 자리 사이가 파티션으로 가려져 있으면 허용
    // 응시자 : P, 빈 테이블 : O, 파티션 : X
    // 거리지키고 있으면 1, 아니면 0을 배열에 넣는다
    public int[] solution(String[][] places) {
        int[] answer = new int[5];


        for (int i = 0; i < 5; i++) {
            answer[i] = calc(i, places);
        }

        return answer;
    }

    private int calc(int i, String[][] places) {
        // 사람들을 대상으로 맨해튼거리 내에 사람 있는지 확인
        // 있으면 파티션으로 가려져있는지 확인
        // 아니라면 0을 할당한다
        // 모든 점 확인 했는데 이상없으면 1 할당
        for (int j = 0; j < 5; j++) {
            for (int k = 0; k < 5; k++) {
                // 사람일 때
                if(places[i][j].charAt(k) == 'P') {
                    if (isNearPeopleManhattanDistance(j, k, places[i])) {
                        return 0;
                    }
                }
            }
        }
        return 1;
    }

    private boolean isNearPeopleManhattanDistance(int r1, int c1, String[] places) {
        int[][] dir = {{0, -1}, {-1, 0}, {1, 0}, {0, 1}, {0, -2}, {-1, -1}, {1, -1}, {-2, 0},
                {2, 0}, {-1, 1}, {1, 1}, {0, 2}};

        // 맨해튼거리 <= 2 라면 false 리턴
        for (int i = 0; i < 12; i++) {
            int r2 = r1 + dir[i][0];
            int c2 = c1 + dir[i][1];

            // 범위 벗어나면 리턴
            if (r2 < 0 || c2 < 0 || r2 >= 5 || c2 >= 5) continue;

            int xDiff = Math.abs(r1 - r2);
            int yDiff = Math.abs(c1 - c2);

            // 상하좌우 1의 거리
            if ((xDiff == 1 && yDiff != 1) || (xDiff != 1 && yDiff == 1)) {
                if (places[r2].charAt(c2) == 'P') {
                    return false;
                }
            }

            // 상하좌우 2의 거리
            if (xDiff == 2 || yDiff == 2) {
                if (places[r2].charAt(c2) == 'P') {
                    if (r2 > r1) {
                        if (places[r2 - 1].charAt(c2) != 'X') return false;
                    } else if (r1 > r2) {
                        if (places[r2 + 1].charAt(c2) != 'X') return false;
                    } else if (c2 > c1) {
                        if (places[r2].charAt(c2 - 1) != 'X') return false;
                    } else {
                        if (places[r2].charAt(c2 + 1) != 'X') return false;
                    }
                }
            }

            // 대각선
            if (xDiff == 1 && yDiff == 1) {
                if ((r2 > r1) && (c2 > c1)) {
                    if (places[r2].charAt(c2 - 1) != 'X' || places[r2 + 1].charAt(c2) != 'X') return false;
                } else if ((r2 > r1) && (c2 < c1)) {
                    if (places[r2].charAt(c2 + 1) != 'X' || places[r2 + 1].charAt(c2) != 'X') return false;
                } else if ((r2 < r1) && (c2 > c1)) {
                    if (places[r2].charAt(c2 + 1) != 'X' || places[r2 - 1].charAt(c2) != 'X') return false;
                } else {
                    if (places[r2].charAt(c2 - 1) != 'X' || places[r2 - 1].charAt(c2) != 'X') return false;
                }
            }
        }
        return true;
    }
}
