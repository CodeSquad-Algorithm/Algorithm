package 아더;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class INF_미로의최단거리통로 {

    private static final int ROW_SIZE = 7;
    private static final int COL_SIZE = 7;

    public static void main(String[] args) throws IOException {
        INF_미로의최단거리통로 inf_미로의최단거리통로 = new INF_미로의최단거리통로();

        System.out.println(inf_미로의최단거리통로.solution());
    }

    private int solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] adj = new int[ROW_SIZE][COL_SIZE];

        for (int i = 0; i < ROW_SIZE; i++) {
            String[] split = br.readLine().split(" ");
            for (int j = 0; j < COL_SIZE; j++) {
                adj[i][j] = Integer.parseInt(split[j]);
            }
        }

        return bfs(adj);
    }

    private int bfs(int[][] adj) {
        Queue<Integer> que = new LinkedList<>();
        int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        boolean[][] visited = new boolean[ROW_SIZE][COL_SIZE];

        que.add(0);
        que.add(0);

        while(!que.isEmpty()) {
            int x = que.poll();
            int y = que.poll();

            for (int i = 0; i < 4; i++) {
                int nx = x + dir[i][0];
                int ny = y + dir[i][1];

                if (nx < 0 || ny < 0 || nx >= ROW_SIZE || ny >= COL_SIZE) continue;
                if (adj[nx][ny] == 1) continue;
                if (visited[nx][ny]) continue;

                visited[nx][ny] = true;
                que.add(nx);
                que.add(ny);
                adj[nx][ny] = adj[x][y] + 1;
            }
        }

        return adj[ROW_SIZE - 1][COL_SIZE - 1];
    }
}
