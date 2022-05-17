package 아더;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pro_86052_빛의경로사이클 {

    public static void main(String[] args) {
        Pro_86052_빛의경로사이클 pro = new Pro_86052_빛의경로사이클();
        String[] grid1 = {"SL","LR"};
        String[] grid2 = {"S"};
        String[] grid3 = {"R","R"};

        System.out.println(Arrays.toString(pro.solution(grid1)));
        System.out.println(Arrays.toString(pro.solution(grid2)));
        System.out.println(Arrays.toString(pro.solution(grid3)));
    }

    public int[] solution(String[] grid) {
        List<Integer> answer = new ArrayList<>();
        int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 서 북 동 남

        int row = grid.length;
        int col = grid[0].length();
        char[][] adj = new char[row][col];
        boolean [][][] visited = new boolean[row][col][dir.length];

        // 입력 받기
        for (int i = 0; i < row; i++) {
            String[] split = grid[i].split("");
            for (int j = 0; j < col; j++) {
                adj[i][j] = split[j].charAt(0);
            }
        }

        // 로직
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                for (int k = 0; k < dir.length; k++) {
                    if (visited[i][j][k]) continue;
                    int cycleCount = 0;
                    int tempRow = i;
                    int tempCol = j;
                    int tempDir = k;

                    // 4방향에서 각 점에 접근한다
                    while (true) {
                        // 각 점의 값에 따라 이동 및 방향 변경
                        if (adj[tempRow][tempCol] == 'S') {
                            // 방향 변경할 필요 없음
                            tempRow = (tempRow + dir[tempDir][0] % 4) % row;
                            tempCol = (tempCol + dir[tempDir][1] % 4) % col;
                        } else if (adj[tempRow][tempCol] == 'L') {
                            tempDir = (tempDir - 1 + 4) % 4;
                            tempRow = (tempRow + dir[(tempDir) % 4][0]) % row;
                            tempCol = (tempCol + dir[(tempDir) % 4][1]) % col;
                        } else if (adj[tempRow][tempCol] == 'R') {
                            tempDir = (tempDir + 1 + 4) % 4;
                            tempRow = (tempRow + dir[(tempDir) % 4][0]) % row;
                            tempCol = (tempCol + dir[(tempDir) % 4][1]) % col;
                        }

                        // temp가 범위를 벗어나면 보정
                        if (tempRow < 0) {
                            tempRow = row - 1;
                        }
                        if (tempCol < 0) {
                            tempCol = col - 1;
                        }
                        if (tempRow >= row) {
                            tempRow = 0;
                        }
                        if (tempCol >= col) {
                            tempCol = 0;
                        }

                        // 방문했던 곳이면 사이클임
                        if (visited[tempRow][tempCol][tempDir]) {
                            break;
                        }

                        // 방문 처리 및 사이클 수 증가
                        visited[tempRow][tempCol][tempDir] = true;
                        cycleCount++;
                    }
                    answer.add(cycleCount);
                }
            }
        }

        return answer.stream()
                .mapToInt(num -> num)
                .sorted()
                .toArray();
    }
}
