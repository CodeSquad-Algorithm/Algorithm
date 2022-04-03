package 땃쥐;

import java.io.IOException;

public class BOJ_11657 {

    private static int N; // 도시의 갯수
    private static int M; // 노선의 갯수
    private static int[][] edges; // 간선들
    private static long[] dist;
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        N = readInt();
        M = readInt();

        edges = new int[M][3];

        for (int i=0; i<M; i++) {
            int s = readInt();
            int e = readInt();
            int cost = readInt();
            edges[i] = new int[]{s, e, cost};
        }

        dist = new long[N+1];
        for (int i=0; i<N+1; i++) { // 무한으로 초기화
            dist[i] = INF;
        }

        StringBuilder sb = new StringBuilder();

        // 벨먼 포드 알고리즘을 굴렸을 때 음의 사이클이 없을 경우 true
        if (bellmanFord()) {
            for (int i=2; i<=N; i++) {
                if (dist[i] == INF) {
                    sb.append(-1).append('\n');
                } else {
                    sb.append(dist[i]).append('\n');
                }
            }
        } else { // 음의 사이클이 존재하면 false
            sb.append(-1);
        }
        System.out.print(sb);
    }

    private static boolean bellmanFord() { // 음수 싸이클이 존재하면 false
        dist[1] = 0; // 시작점 1번 노드는 비용이 0

        for (int i=0; i<N-1; i++) { // 정점의 갯수 -1 만큼 반복
            for (int[] edge : edges) {
                int s = edge[0];
                int e = edge[1];
                int cost = edge[2];

                if ((dist[s] != INF) && (dist[e] > dist[s] + cost)) {
                    dist[e] = dist[s] + cost;
                }
            }
        }

        for (int[] edge: edges) { // 어딘가 갱신이 발생하면 음수 싸이클이 존재
            int s = edge[0];
            int e = edge[1];
            int cost = edge[2];

            if ((dist[s] != INF) && (dist[e] > dist[s] + cost)) {
                return false;
            }
        }
        return true;
    }

    private static int readInt() throws IOException {
        int value = 0;
        boolean negative = false;

        int input;
        while (true) {
            input = System.in.read();
            if (input == ' ' || input == '\n') {
                return (negative) ? -value : value;
            } else if (input == '-') {
                negative = true;
            } else {
                value = value * 10 + (input - 48);
            }
        }
    }

}
