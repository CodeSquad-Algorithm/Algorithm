package 아더;

public class Pro_72413_합승택시요금 {

    public static void main(String[] args) {
        Pro_72413_합승택시요금 pro = new Pro_72413_합승택시요금();

        int n = 6, s = 4, a = 6, b = 2;
        int[][] fares = {{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}};
        System.out.println(pro.solution(n, s, a, b, fares)); // 82

        n = 7; s = 3; a = 4; b = 1;
        int[][] fares2 = {{5, 7, 9}, {4, 6, 4}, {3, 6, 1}, {3, 2, 3}, {2, 1, 6}};
        System.out.println(pro.solution(n, s, a, b, fares2)); // 14

        n = 6; s = 4; a = 5; b = 6;
        int[][] fares3 = {{2, 6, 6}, {6, 3, 7}, {4, 6, 7}, {6, 5, 11}, {2, 5, 12}, {5, 3, 20}, {2, 4, 8}, {4, 3, 9}};
        System.out.println(pro.solution(n, s, a, b, fares3)); // 18
    }

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;
        int[][] dist = new int[n + 1][n + 1];

        // 거리 무한대로 초기화
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) continue;
                dist[i][j] = 100_001 * n;
            }
        }

        // 거리 입력
        for (int i = 0; i < fares.length; i++) {
            dist[fares[i][0]][fares[i][1]] = fares[i][2];
            dist[fares[i][1]][fares[i][0]] = fares[i][2];
        }

        // 플로이드 워셜 알고리즘
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        // 택시요금 계산
        for (int i = 1; i <= n; i++) {
            answer = Math.min(answer, (dist[s][i] + dist[i][a] + dist[i][b]));
        }

       return answer;
    }
}
