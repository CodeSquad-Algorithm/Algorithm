package 아더;

import java.io.*;
import java.util.*;

public class BOJ_2056_작업 {
    static int N;
    static int[] indeg, D, SUM;
    static List<Integer>[] adj;

    public static void main(String[] args) {
        try {
            input();
            solve();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void solve() {
        Deque<Integer> que = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (indeg[i] == 0) {
                que.add(i);
                SUM[i] = D[i];
            }
        }

        while (!que.isEmpty()) {
            int x = que.poll();
            for (int y : adj[x]) {
                indeg[y]--;
                if (indeg[y] == 0) {
                    que.add(y);
                }
                SUM[y] = Math.max(SUM[y], SUM[x] + D[y]);
            }
        }

        System.out.println(Arrays.stream(SUM).max().getAsInt());
    }

    private static void input() throws IOException {
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader(new File("C:\\Users\\k2j38\\OneDrive\\Desktop\\Park\\99.ETC\\input.txt")));

        N = Integer.parseInt(br.readLine());
        indeg = new int[N + 1];
        adj = new List[N + 1];
        D = new int[N + 1];
        SUM = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            String[] split = br.readLine().split(" ");
            D[i] = Integer.parseInt(split[0]);

            for (int j = 2; j < split.length; j++) {
                adj[i].add(Integer.parseInt(split[j]));
                indeg[Integer.parseInt(split[j])]++;
            }
        }
    }
}
