package 땃쥐;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class BOJ_1916_2 {

    private static int N; // 노드의 갯수
    private static List<List<int[]>> connects; // 연결된 노드 및 소요 비용
    private static int[] minCosts; // 최소 비용들

    public static void main(String[] args) throws IOException {
        N = readInt();

        minCosts = new int[N+1];
        for (int i=0; i<N+1; i++) {
            minCosts[i] = Integer.MAX_VALUE; // 비용들을 일단 최대로 설정한다.
        }

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

        enhancedDijkstra(startIndex, endIndex); // 다익스트라 알고리즘을 통해, 출발점 기준 각 노드들에 대한 최소 비용을 갱신한다.
        System.out.print(minCosts[endIndex]);
    }

    private static void enhancedDijkstra(int startIndex, int endIndex) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(node -> node[1]));

        minCosts[startIndex] = 0; //  시작점의 비용은 0
        queue.offer(new int[]{startIndex, minCosts[startIndex]}); // 시작점 기준으로 탐색 시작

        while(!queue.isEmpty()) {
            // 방문하지 않은 제일 소요비용이 적은 노드를 꺼낸다.

            int[] currentNode = queue.poll();
            int currentIndex = currentNode[0]; // 노드의 인덱스
            int currentMinCost = currentNode[1]; // 노드의 소요 비용

            // 해당 노드에 연결된 노드들에 대하여 최소 연결 비용을 갱신한다.
            updateMinCost(queue, currentIndex, currentMinCost);
        }
    }

    private static void updateMinCost(PriorityQueue<int[]> queue, int currentIndex, int currentMinCost) {
        // 최소비용이 현재 연결관계의 비용보다 저렴하면,
        // 이미 꺼내져서 한 번 더 적게 갱신된 바 있으므로 불필요한 작업이다. 스킵.
        if(minCosts[currentIndex] < currentMinCost) {
            return;
        }

        // 해당 노드의 모든 주변 노드를 탐색하여 최소비용을 갱신시킨다.
        for (int[] connect : connects.get(currentIndex)) {
            int indexOfConnectedNode = connect[0];
            int minCostOfConnectedNode = connect[1];

            // 연결 비용이 더 저렴하면 최소비용을 갱신시킨다.
            // 그리고 그 노드들을 기준으로도 최소 연결 비용을 갱신하기 위해 우선순위 Queue에 넣는다.
            if (minCosts[indexOfConnectedNode] > currentMinCost + minCostOfConnectedNode) {
                minCosts[indexOfConnectedNode] = currentMinCost + minCostOfConnectedNode;
                queue.offer(new int[]{indexOfConnectedNode, minCosts[indexOfConnectedNode]});
            }
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
