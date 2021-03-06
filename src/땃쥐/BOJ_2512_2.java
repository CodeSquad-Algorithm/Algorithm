package 땃쥐;

import java.io.IOException;

public class BOJ_2512_2 {

    private static int N; // 탐색 대상 수(지방 수)
    private static int M; // 총 예산액
    private static int[] budgets; // 각 지방별 예산들
    private static int left; // 탐색범위의 시작값
    private static int mid; // 탐색범위의 중간값
    private static int right; // 탐색범위의 끝값

    public static void main(String[] args) throws IOException {
        N = readInt();
        budgets = new int[N]; // 예산들

        right = Integer.MIN_VALUE;
        for(int i=0; i<N; i++) {
            budgets[i] = readInt();
            right = Math.max(right, budgets[i]);
        }
        M = readInt();
        System.out.print(findMaxBudget());
    }

    private static int findMaxBudget() {
        while(left<=right) { // 시작값이 끝값보다 같거나 작은 동안 루프
            mid = (left+right)/2; // 중간값 기준
            int sum = 0;
            for (int budget : budgets) {
                if (budget>mid) {
                    sum += mid;
                } else {
                    sum += budget;
                }
            }
            if (sum == M) { // 중간값 자체가 조건을 완벽히 부합하는 목표값인 경우
                return mid;
            } else if (sum < M) { // 조건을 만족하는 경우 mid보다 더 큰 값에서 찾기 위해 left를 뒤로 조정한다.
                left = mid+1;
            } else { // 조건을 만족하지 않은 경우 mid보다 더 작은 범위 내에서 찾기위해 right를 앞으로 조정한다.
                right = mid-1;
            }
        }
        return right;
    }

    private static int readInt() throws IOException {
        int value = 0;
        while (true) {
            // 입력 문자의 ASCII코드 값.
            // 가령 '0'이 들어왔으면 숫자 0이 아니라 '0'의 ASCII 코드값인 48이다.
            int input = System.in.read();
            if (input == ' ' || input == '\n') { // 개행문자거나 공백이면 연산을 끊음
                return value; // 양수면 그냥 반환
            } else {
                value = value * 10 + (input - 48); // 기존 값을 10배하고 입력된 추가값을 파싱하여 더함
            }
        }
    }
}
