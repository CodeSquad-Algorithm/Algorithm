package 땃쥐;

public class Programmers_42898 {

    public int solution(int m, int n, int[][] puddles) {

        int[][] routeCounter = new int[n+1][m+1]; // 각 인덱스는 그 지점에 올 수 있는 가짓수로 취급

        for (int[] puddle : puddles) { // 웅덩이는 일단 -1로 체킹
            routeCounter[puddle[1]][puddle[0]] = -1;
        }

        routeCounter[1][0] = 1; // 1,1의 왼쪽값을 1로 가정

        for (int i=1; i<=n; i++) {
            for (int j=1; j<=m; j++) {
                if (routeCounter[i][j] == -1) {
                    routeCounter[i][j] = 0; // 웅덩이가 있는 곳에 올 수 있는 경우의 수는 0이다.
                } else {
                    routeCounter[i][j] = (routeCounter[i][j-1] + routeCounter[i-1][j])%1_000_000_007;
                    // 위의 좌표에 도달할 수 있는 경우의 수 + 왼쪽의 좌표에 도달할 수 있는 경우의 수
                }
            }
        }

        int answer = routeCounter[n][m];
        return answer;
    }

}
