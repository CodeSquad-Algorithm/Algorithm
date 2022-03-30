package 땃쥐;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BOJ_1916_1 {

    private static int N; // 노드의 갯수
    private static List<List<int[]>> connects; // 연결 노드 및 소요 비용
    private static int[] minCosts; // 최소 비용들
    private static boolean[] visited; // 방문 여부

    public static void main(String[] args) throws IOException {
        N = readInt();

        minCosts = new int[N+1];
        for (int i=0; i<N+1; i++) {
            minCosts[i] = Integer.MAX_VALUE; // 비용들을 일단 최대로 설정한다.
        }
        visited = new boolean[N+1];

        connects = new ArrayList<>();
        for (int i=0; i<N+1; i++) {
            connects.add(new ArrayList<>());
        }

        int M = readInt(); // 간선의 갯수

        for (int i=0; i<M; i++) {
            int s = readInt();
            int e = readInt();
            int cost = readInt();
            connects.get(s).add(new int[]{e, cost}); // 연결관계를 추가한다.
        }

        int startIndex = readInt(); // 시작점 입력
        int endIndex = readInt(); // 종료점 입력

        dijkstra(startIndex); // 다익스트라 알고리즘을 통해, 출발점 기준 각 노드들에 대한 최소 비용을 갱신한다.
        System.out.print(minCosts[endIndex]);
    }

    private static void dijkstra(int startIndex) {
        minCosts[startIndex] = 0; //  시작점의  비용은 0

        for (int i=0; i<N; i++) { // 노드의 갯수만큼 반복한다. (N번)
            int minCost = Integer.MAX_VALUE;
            int nodeIndex = 0;

            nodeIndex = findUnvisitedMinCostNode(minCost, nodeIndex); // 방문하지 않은 노드 중에서 비용이 최소인 지점을 찾는다.
            updateMinCost(nodeIndex); // 해당 노드를 방문하고 노드 기준으로 연결된 노드들에 대한 비용을 확인하여 최소비용을 갱신한다.
        }
    }

    // 방문하지 않은 노드 중에서, 비용이 최소인 지점을 찾는다.
    private static int findUnvisitedMinCostNode(int minCost, int nodeIndex) {
        for (int j=1; j<=N; j++) {
            if (!visited[j] && (minCosts[j] < minCost)) {
                minCost = minCosts[j];
                nodeIndex = j;
            }
        }
        return nodeIndex;
    }

    // 방문되지 않은 노드 중에서, 비용이 최소인 지점을 찾으면 그 지점 기준으로 인접한 노드에 대한 최소 소요비용을 갱신
    private static void updateMinCost(int nodeIndex) {
        visited[nodeIndex] = true; // 방문처리

        // 해당 노드 기준으로 연결된 모든 노드들에 대한 연결관계 및 비용을 확인하고 최소비용을 갱신한다.
        for (int[] connect : connects.get(nodeIndex)) {
            int e = connect[0];
            int cost = connect[1];
            minCosts[e] = Math.min(minCosts[e], minCosts[nodeIndex] + cost);
        }
    }

    private static int readInt() throws IOException {
        int value = 0;
        while (true) {
            int input = System.in.read();
            if (input == ' ' || input == '\n') {
                return value;
            } else {
                value = value * 10 + (input - 48);
            }
        }
    }
}
