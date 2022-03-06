package 땃쥐;

import java.io.IOException;

public class BOJ_11660_1 {

    public static void main(String[] args) throws IOException {
        int N = readInt();
        int M = readInt();

        int[][] numbers = new int[N][N];

        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                numbers [i][j] = readInt();
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i=0; i<M; i++) {
            int t1 = readInt()-1;
            int u1 = readInt()-1;
            int t2 = readInt()-1;
            int u2 = readInt()-1;

            int sum = 0;

            for (int j=t1; j<=t2; j++) {
                for (int k=u1; k<=u2; k++) {
                    sum += numbers[j][k];
                }
            }
            sb.append(sum).append('\n');
        }
        System.out.print(sb);
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

