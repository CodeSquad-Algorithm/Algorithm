package 땃쥐;

import java.io.IOException;
import java.util.Arrays;

public class BOJ_1197 {
    /**
     * 1. 신장트리 : 하나의 그래프가 있을 때 모든 노드를 포함하면서 사이클이 존재하지 않는 부분 그래프
     * 2. 간선의 사이클
     *   - 루트 노드가 서로 다르다 : union 연산 수행
     *   - 루트 노드가 서로 같다 : 사이클이 발생한 것
     */
    private static int V; // 정점의 갯수
    private static int E; // 간선의 갯수
    private static int[] parents; // 최상위 부모노드
    private static Edge[] edges;

    public static void main(String[] args) throws IOException {
        initNodes();
        initEdges();
        int costSum = kruskal();
        System.out.print(costSum);
    }

    private static void initNodes() throws IOException {
        V = readInt();
        parents = new int[V+1];
        for (int i=1; i<=V; i++) {
            parents[i] = i; // 초기 부모는 자기 자신으로 설정
        }
    }

    private static int kruskal() {
        int costSum = 0;
        int count = 0;

        // 각 노드를 순회
        for (Edge edge : edges) {
            // 사이클 발생하지 않을 경우
            if (union(edge.start, edge.end)) {
                costSum += edge.cost;
                if (++count == V-1) break; // 연결이 완료됐는가?
            }
        }
        return costSum;
    }

    private static void initEdges() throws IOException {
        E = readInt();
        edges = new Edge[E];
        int start, end, cost;
        for (int i = 0; i < E; i++) {
            start = readInt();
            end = readInt();
            cost = readInt();
            edges[i] = new Edge(start, end, cost);
        }
        Arrays.sort(edges); // 간선의 가중치 기준 오름차순 정렬
    }

    private static int findParent(int a) {
        return (a == parents[a])
                ? a
                : (parents[a] = findParent(parents[a]));
    }

    // 두 원소노드가 속한 집합 합치기.
    private static boolean union(int a, int b) {
        int aRoot = findParent(a); // 최상위 조상노드를 찾아냄
        int bRoot = findParent(b);

        if (aRoot == bRoot) { // 최상위 노드가 같으면 이미 연결된 것. 사이클이 발생한 것.
            return false;
        } else if (aRoot < bRoot) {
            parents[bRoot] = aRoot;
            return true;
        } else {
            parents[aRoot] = bRoot;
            return true;
        }
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

class Edge implements Comparable<Edge> {
    int start;
    int end;
    int cost;

    public Edge(int start, int end, int cost) {
        this.start = start;
        this.end = end;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge ob) { // 간선의 가중치 기준 오름차순
        return this.cost - ob.cost;
    }
}
