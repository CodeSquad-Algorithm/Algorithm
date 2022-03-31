package 아더;

public class Pro_등굣길 {

    public static void main(String[] args) {
        Pro_등굣길 pro = new Pro_등굣길();

        int m = 4, n = 3;
        int[][] puddles = {{2,2}};
        System.out.println(pro.solution(m, n, puddles)); // 정답 4
    }

    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        int divideNum = 1_000_000_007;

        int[][] map = new int[n + 1][m + 1];
        for (int[] ints : puddles) {
            map[ints[1]][ints[0]] = -1;
        }

        // 초기값 설정
        map[1][1] = 1;
        if (map[1][2] != -1) {
            map[1][2] = 1;
        }
        if (map[2][1] != -1) {
            map[2][1] = 1;
        }

        // 점화식 생성
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (i == 1 && j == 1) {
                    continue;
                }
                if (map[i][j] != -1) {
                    int corValue = 0;
                    if (map[i - 1][j] == -1) {
                        corValue++;
                    }
                    if (map[i][j - 1] == -1) {
                        corValue++;
                    }
                    map[i][j] = (map[i - 1][j] + map[i][j - 1] + corValue) % divideNum;
                }
            }
        }

        // 결과 출력
        answer = map[n][m];
        return answer;
    }
}
