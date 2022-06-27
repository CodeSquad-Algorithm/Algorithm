package 아더;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class INF_뮤직비디오 {

    public static void main(String[] args) throws IOException {
        INF_뮤직비디오 inf_뮤직비디오 = new INF_뮤직비디오();
        System.out.println(inf_뮤직비디오.solution());
    }

    private int solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        int N = Integer.parseInt(split[0]);
        int M = Integer.parseInt(split[1]);

        int[] dvds = new int[N];

        split = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            dvds[i] = Integer.parseInt(split[i]);
        }

        return solve(dvds, M);
    }
    // TODO
    // 결정 문제 : 용량 X로 했을 때 M개의 DVD에 모든 노래를 다 담을 수 있는가?

    private int solve(int[] dvds, int m) {
        int L = 1, R = 10_000, target = m, answer = 0;

        while (L <= R) {
            int mid = (L + R) / 2;
            if (determination(dvds, target, mid)) {
                L = mid + 1;
                answer = mid;
            } else {
                R = mid - 1;
            }
        }

        return answer + 1;
    }

    private boolean determination(int[] dvds, int target, int mid) {
        int dvdCount = 1, sum = 0;

        for (int dvd : dvds) {
            if (sum + dvd > mid) {
                dvdCount++;
                sum = dvd;
            } else {
                sum += dvd;
            }
        }

        return dvdCount > target;
    }
}
