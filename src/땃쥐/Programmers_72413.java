package 땃쥐;

public class Programmers_72413 {

    private int[][] minCosts;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        minCosts = initMinCosts(n, fares);
        floyd(n);
        return getAnswer(n, s, a, b);
    }

    private int[][] initMinCosts(int n, int[][] fares) {
        int[][] minCosts = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) { // 기본 초기화
            for (int j = 1; j <= n; j++) {
                minCosts[i][j] = (i == j) ? 0 : Integer.MAX_VALUE; // 자기 자신으로 가는 비용은 0, 나머지는 무한
            }
        }

        for (int[] fare : fares) { // fare를 읽고 양방향 비용 초기화
            minCosts[fare[0]][fare[1]] = minCosts[fare[1]][fare[0]] = fare[2];
        }
        return minCosts;
    }

    private void floyd(int n) {
        for (int i = 1; i <= n; i++) { // i번 노드를 지나는 모든 경우에 대해서
            for (int j = 1; j <= n; j++) {
                if (j == i) {
                    continue;
                }
                for (int k = 1; k <= n; k++) {
                    // i가 아닌 서로 다른 두 출발점 j, 도착점 k를 선택해야함.
                    if (k == i || k == j) {
                        continue;
                    }
                    // j에서 i 또는 i에서 k로 가는 경로가 존재하지 않는 경우는 최소비용 갱신이 더 이상 되지 않는다.
                    if (minCosts[j][i] == Integer.MAX_VALUE || minCosts[i][k] == Integer.MAX_VALUE) {
                        continue;
                    }
                    // j, k 사이에 노드 i가 있을 경우 경유비용과 직접 이동비용 양쪽을 비용하여 최소비용을 갱신한다.
                    minCosts[j][k] = Math.min(minCosts[j][k], minCosts[j][i] + minCosts[i][k]);
                }
            }
        }
    }

    private int getAnswer(int n, int s, int a, int b) {
        int answer = Integer.MAX_VALUE;

        for (int i = 1; i <= n; i++) {
            int currentCost = minCosts[s][i] + minCosts[i][a] + minCosts[i][b];
            answer = Math.min(answer, currentCost);
        }
        return answer;
    }

}
