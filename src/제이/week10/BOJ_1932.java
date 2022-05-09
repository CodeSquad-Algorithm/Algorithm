import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1932 {

    private static int n;
    private static Integer[][] tree;
    private static Integer[][] sum;

    public static void main(String[] args) throws IOException {
        readInput();
        System.out.println(calcSumRecursive(0, 0));
    }

    private static int calcSumRecursive(int h, int i) {

        if (h == n - 1) return sum[h][i];

        if (sum[h][i] == null) {
            sum[h][i] = Math.max(
                    calcSumRecursive(h + 1, i) + tree[h][i],
                    calcSumRecursive(h + 1, i + 1) + tree[h][i]
            );
        }

        return sum[h][i];
    }

    private static void readInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        tree = new Integer[n][n];
        sum = new Integer[n][n];

        for (int h = 0; h < n; h++) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; st.hasMoreTokens(); i++) {
                tree[h][i] = Integer.parseInt(st.nextToken());
                if (h == n - 1) {
                    sum[h][i] = tree[h][i];
                }
            }
        }
    }
}
