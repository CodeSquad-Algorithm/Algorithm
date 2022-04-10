import java.io.IOException;
import java.util.Arrays;

public class BOJ_2110 {

    public static void main(String[] args) throws IOException {
        int N = readInt(); // 집의 수
        int C = readInt(); // 공유기의 수

        int[] house = new int[N]; // 집들의 좌표들

        for (int i=0; i<N; i++) {
            house[i] = readInt();
        }
        Arrays.sort(house); // 정렬해야 거리 관계를 파악하기 쉬움

        int maxDistance = findMaxDistance(N, C, house); // 공유기간 최소거리의 최댓값
        System.out.print(maxDistance);
    }

    private static int findMaxDistance(int N, int C, int[] house) {
        int left = 1; // 탐색범위의 시작값
        int right = house[N -1]- house[0]; // 탐색범위의 끝값(초깃값 : 최소-최대 좌표의 거리)
        int mid;

        while (left <= right) {
            mid = (left+right) >> 1; // 공유기간 최소거리

            int count = countOfCanInstallRouter(N, house, mid);

            /**
             * 주어진 공유기 갯수보다 더 많이 설치할 수 있거나, 같으면
             * 더 벌려보면서 공유기간 최소거리가 충분하다는 것이다. 최댓값을 찾아낸다.
             */
            if (count >= C) {
                left = mid +1;
            } else {
                /**
                 * 주어진 공유기의 갯수보다 더 적은 값이면, 너무 최소거리가 멀다는 것을 의미함.
                 */
                right = mid-1;
            }
        }
        return right;
    }

    private static int countOfCanInstallRouter(int N, int[] house, int minDist) {
        int count = 1; // 설치할 수 있는 공유기 갯수
        int lastLocate = house[0];

        for (int i = 1; i< N; i++) {
            int locate = house[i];
            if (locate - lastLocate >= minDist) {
                count ++;
                lastLocate = locate;
            }
        }
        return count;
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
