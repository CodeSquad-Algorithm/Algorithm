import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_2056 {

    private static int N;
    private static int[] costs; // 작업 비용들을 저장할 배열
    private static int[] indegree; // 진입 차수(들어오는 간선의 갯수)
    private static int[] results; // 각 작업을 순차적으로 작업했을 때 완료시점에서의 누적 시간
    private static ArrayList<ArrayList<Integer>> nexts = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        N = readInt();
        costs = new int[N + 1];
        indegree = new int[N + 1];
        results = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            nexts.add(new ArrayList<>());
        }

        for (int i = 1; i <= N; i++) {
            costs[i] = readInt(); // 작업 비용
            results[i] = costs[i];
            indegree[i] = readInt(); // 진입 차수

            for (int j = 0; j < indegree[i]; j++) {
                int beforeNode = readInt(); // 현재 노드의 선행 노드
                nexts.get(beforeNode).add(i); // 선행 노드의 다음 노드 list에 추가한다.
            }
        }

        topologySort();

        int totalCost = Integer.MIN_VALUE;
        for (int i=1; i<=N; i++) {
            totalCost = Math.max(totalCost, results[i]);
        }
        System.out.print(totalCost);
    }

    private static void topologySort() {
        Queue<Integer> queue = new LinkedList<>();

        for (int i=1; i<N; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int now = queue.poll(); // 큐에서 원소(노드) 꺼내기

            // 해당 노드와 연결된 노드들의 진입차수에서 1 빼기
            for (int next : nexts.get(now)) {
                indegree[next]--;
                results[next] = Math.max(results[next], results[now] + costs[next]); // 다음 노드 작업 완료 시간을 갱신
                if (indegree[next] == 0) {
                    queue.offer(next); // 진입차수가 0인 노드는 시간 누적계산이 완료된 노드임
                }
            }
        }
    }

    private static int readInt() throws IOException {
        int value = 0;

        int input;
        while (true) {
            input = System.in.read();
            if (input == ' ' || input == '\n') {
                return value;
            } else {
                value = value * 10 + (input - 48);
            }
        }
    }
}
