package 땃쥐;

import java.io.IOException;

public class BOJ_2512_2 {

    private static int N;
    private static int M;
    private static int[] budgets;
    private static int left;
    private static int mid;
    private static int right;

    public static void main(String[] args) throws IOException {
        N = readInt();
        budgets = new int[N]; // 예산들

        right = Integer.MIN_VALUE;
        for(int i=0; i<N; i++) {
            budgets[i] = readInt();
            right = Math.max(right, budgets[i]);
        }
        M = readInt(); // 예산 총액
        System.out.print(findMaxBudget());
    }

    private static int findMaxBudget() {
        while(left<=right) {
            mid = (left+right)/2;
            int sum = 0;
            for (int budget : budgets) {
                if (budget>mid) {
                    sum += mid;
                } else {
                    sum += budget;
                }
            }
            if (sum == M) {
                return mid;
            } else if (sum < M) {
                left = mid+1;
            } else {
                right = mid-1;
            }
        }
        return right;
    }

    private static int readInt() throws IOException {
        boolean isNegative = false; // 음수니?
        int value = 0;
        while (true) {
            // 입력 문자의 ASCII코드 값.
            // 가령 '0'이 들어왔으면 숫자 0이 아니라 '0'의 ASCII 코드값인 48이다.
            int input = System.in.read();
            if (input == ' ' || input == '\n') { // 개행문자거나 공백이면 연산을 끊음
                return (isNegative)
                        ? -1 * value // 음수면 - 붙여서 반환
                        : value; // 양수면 그냥 반환
            } else if (input == '-') { // 문자를 읽었는데 -가 붙어있으면 이 값은 음수임
                isNegative = true;
            } else {
                value = value * 10 + (input - 48); // 기존 값을 10배하고 입력된 추가값을 파싱하여 더함
            }
        }
    }

}
