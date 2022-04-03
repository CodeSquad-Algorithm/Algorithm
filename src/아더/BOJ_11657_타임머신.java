package 아더;

import java.io.*;
import java.util.*;

public class BOJ_11657_타임머신 {

    static int N, M;
    static boolean isCycle;
    static long[] dist;
    static List<Edge>[] edges;

    static class Edge {
        int to, weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        try {
            input();
            solve();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void solve() {
        Arrays.fill(dist, Integer.MAX_VALUE);

        if (bellmanFord(1)) {
            System.out.println(-1);
        } else {
            for (int i = 2; i <= N; i++) {
                if (dist[i] == Integer.MAX_VALUE) {
                    System.out.println(-1);
                } else {
                    System.out.println(dist[i]);
                }
            }
        }
    }

    private static boolean bellmanFord(int start) {
        dist[start] = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 1; j <= N; j++) {
                for (Edge edge : edges[j]) {
                    if (dist[j] == Integer.MAX_VALUE) continue;
                    if (dist[edge.to] > dist[j] + edge.weight) {
                        dist[edge.to] = dist[j] + edge.weight;
                        if (i == N - 1) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    private static void input() throws IOException {
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader(new File("C:\\Users\\k2j38\\OneDrive\\Desktop\\Park\\99.ETC\\input.txt")));

        String[] split = br.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);
        isCycle = false;

        dist = new long[N + 1];
        edges = new List[N + 1];

        for (int i = 1; i <= N; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            split = br.readLine().split(" ");
            edges[Integer.parseInt(split[0])].add(new Edge(Integer.parseInt(split[1]), Integer.parseInt(split[2])));
        }
    }
}
