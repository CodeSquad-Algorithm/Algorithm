package 땃쥐;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11724_1 {

    private static boolean[][] connects; // 연결관계
    private static boolean[] visited; // 방문 여부
    private static int N; // 정점의 갯수
    private static int M; // 간선의 갯수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        connects = new boolean[N+1][N+1];

        int a, b;

        // 연결 체결
        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            connects[a][b] = connects[b][a] = true;
        }
        br.close();

        visited = new boolean[N+1];

        int countOfDiffConnects = 0;

        // 완전 탐색
        for (int i=1; i<=N; i++) {
            if (!visited[i]) {
                dfs(i); // dfs 한 사이클을 전부 돌면, 연결된 것들끼리 전부 순회함
                countOfDiffConnects ++;
            }
        }
        System.out.println(countOfDiffConnects);
    }

    // 간선으로 연결되어있는 모든 노드를 순회하여 visited에 기록함.
    private static void dfs(int index) {
        if (visited[index]) {
            return;
        } else {
            visited[index] = true;
            for (int i=1; i<=N; i++) {
                if(connects[index][i]) {
                    dfs(i);
                }
            }
        } // 간선으로 연결된 모든 노드들의 방문이 끝나면 메서드 종료.
    }

}
