import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Programmers_72413 {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int answer1 = sol.solution(6, 4, 6, 2, new int[][]{{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}});
        int answer2 = sol.solution(7, 3, 4, 1, new int[][] {{5, 7, 9}, {4, 6, 4}, {3, 6, 1}, {3, 2, 3}, {2, 1, 6}});
        int answer3 = sol.solution(6, 4, 5, 6, new int[][] {{2,6,6}, {6,3,7}, {4,6,7}, {6,5,11}, {2,5,12}, {5,3,20}, {2,4,8}, {4,3,9}});

        System.out.println(answer1 == 82);
        System.out.println(answer2 == 14);
        System.out.println(answer3 == 18);
    }
}

class Solution {

    static final int INF = 200_000_001;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        List<List<Node>> nodeGraph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            nodeGraph.add(new ArrayList<>());
        }

        for (int[] fare : fares) {
            int num1 = fare[0];
            int num2 = fare[1];
            int cost = fare[2];

            nodeGraph.get(num1).add(new Node(num2, cost));
            nodeGraph.get(num2).add(new Node(num1, cost));
        }

        int[] costS = dijkstra(nodeGraph, n, s);
        int[] costA = dijkstra(nodeGraph, n, a);
        int[] costB = dijkstra(nodeGraph, n, b);

        int sumMin = INF;

        for (int i = 1; i <= n; i++) {
            int sum = costA[i] + costB[i] + costS[i];
            if (sum < sumMin) sumMin = sum;
        }

        return sumMin;
    }

    private int[] dijkstra(List<List<Node>> nodeGraph, int nodeCount, int startNum) {
        int[] minCost = new int[nodeCount + 1];
        boolean[] visit = new boolean[nodeCount + 1];

        Arrays.fill(minCost, INF);

        PriorityQueue<Node> heap = new PriorityQueue<>();
        heap.add(new Node(startNum, 0));
        minCost[startNum] = 0;

        while (!heap.isEmpty()) {
            Node node = heap.poll();
            int num = node.num;

            if (!visit[num]) {

                for (Node nextNode : nodeGraph.get(num)) {
                    int nextNum = nextNode.num;
                    int sum = minCost[num] + nextNode.cost;
                    if (!visit[nextNum] && sum < minCost[nextNum]) {
                        minCost[nextNum] = sum;
                        heap.add(new Node(nextNum, sum));
                    }
                }
            }
        }

        return minCost;
    }

    class Node implements Comparable<Node> {
        int num;
        int cost;

        public Node(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.num - o.num;
        }
    }
}