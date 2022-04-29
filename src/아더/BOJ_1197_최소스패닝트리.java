package 아더;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BOJ_1197_최소스패닝트리 {
    static int V, E;
    static List<Edge> edges;
    static int[] parents;

    static class Edge implements Comparable<Edge> {
        int from, to, weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
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

    private static int findParents(int x) {
        if (parents[x] != x) {
            parents[x] = findParents(parents[x]);
        }
        return parents[x];
    }

    private static void unionParents(int a, int b) {
        a = findParents(a);
        b = findParents(b);

        if (a < b) {
            parents[b] = a;
        } else {
            parents[a] = b;
        }
    }

    private static void solve() {
        int answer = 0;
        Collections.sort(edges);

        for (Edge edge : edges) {
            if (findParents(edge.from) != findParents(edge.to)) {
                unionParents(edge.from, edge.to);
                answer += edge.weight;
            }
        }

        System.out.println(answer);
    }

    private static void input() throws IOException {
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader(new File("/Users/woo-jinpark/Desktop/Park/05_Test/input/input.txt")));

        String[] split = br.readLine().split(" ");
        V = Integer.parseInt(split[0]);
        E = Integer.parseInt(split[1]);
        parents = new int[V + 1];
        edges = new ArrayList<>();

        for (int i = 1; i <= V; i++) {
            parents[i] = i;
        }

        for (int i = 1; i <= V; i++) {
            split = br.readLine().split(" ");
            edges.add(new Edge(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2])));
        }
    }
}
