package 제이;

public class BOJ_11724 {
    private void solve() {
        Scanner sc = new Scanner(System.in);
        int pointCount = sc.nextInt();
        int lineCount = sc.nextInt();
        sc.nextLine();

        int[][] map = new int[pointCount + 1][pointCount + 1];
        boolean[] connected = new boolean[pointCount + 1];

        for (int i = 0; i < lineCount; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            sc.nextLine();

            map[from][to] = 1;
            map[to][from] = 1;
        }

        for (int i = 1; i <= pointCount; i++) {
            for (int j = 1; j <= pointCount; j++) {
                Deque<Integer> queue = new ArrayDeque<>();
                queue.add(i);

                bfs(queue, map, connected, pointCount);
            }
        }

        int notConnectedCount = 0;

        for (boolean b : connected) {
            if (b == false) notConnectedCount++;
        }

        notConnectedCount -= 1; // last point
        System.out.println(notConnectedCount);
    }

    public void bfs(Deque<Integer> queue, int[][] map, boolean[] connected, int pointCount) {
        while(queue.size() > 0) {
            int from = queue.pop();

            for(int to = 1; to <= pointCount; ++to) {
                if (1 == map[from][to]) {
                    map[from][to] = 0;
                    map[to][from] = 0;

                    if(!queue.contains(to)) {
                        queue.add(to);
                        connected[to] = true;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        BOJ_11724 main = new BOJ_11724();
        main.solve();
    }
}
