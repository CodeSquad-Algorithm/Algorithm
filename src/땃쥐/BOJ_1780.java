package 땃쥐;

import java.io.IOException;

public class BOJ_1780 {

    private static int[] counter = new int[3]; // -1, 0, 1짜리로만 채워진 종이 갯수
    private static int[][] numbers; // 숫자들

    public static void main(String[] args) throws IOException {
        int N = readInt(); // 한 줄당 숫자 갯수
        numbers = new int[N][N]; // 숫자들
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                numbers[i][j] = readInt(); // 숫자들 입력
            }
        }
        devideCounting(0, 0, N); // 0행 0열부터 N칸에 대해 카운팅
        StringBuilder sb = new StringBuilder();
        sb.append(counter[0]).append('\n').append(counter[1]).append('\n').append(counter[2]);
        System.out.println(sb);
    }

    public static void devideCounting(int startColumn, int startRow, int length) {

        int startNumber = -2; // startNumber

        int sliceLength = length / 3; // 3등분한 길이

        for (int i = startRow; i < startRow + length; i++) {
            for (int j = startColumn; j < startColumn + length; j++) {
                if (i == startRow && j == startColumn) { // 0행 0열 값을 startNumber로 지정
                    startNumber = numbers[startRow][startColumn];
                    continue;
                }
                if (numbers[i][j] != startNumber) { // startNumber와 다른 것이 하나라도 발견됟면
                    for (int k = startRow; k < startRow + length; k += sliceLength) {
                        for (int l = startColumn; l < startColumn + length; l += sliceLength) {
                            devideCounting(l, k, sliceLength); // 9등분하여 각각에 대해서 카운팅
                        }
                    }
                    return; // 카운팅이 완료되면 반환
                }
            }
        }

        switch (startNumber) { // 반복 다 돌려보니 startNumber와 모두 같은 경우 카운팅
            case -1:
                counter[0]++;
                return;
            case 0:
                counter[1]++;
                return;
            case 1:
                counter[2]++;
                return;
        }
    }

    // 숫자 입력
    private static int readInt() throws IOException {
        int value = 0;
        boolean negative = false;
        while (true) {
            int input = System.in.read();
            if (input == ' ' || input == '\n') {
                return (negative) ? -value : value;
            } else if (input == '-') {
                negative = true;
            } else {
                value = value * 10 + (input - 48);
            }
        }

    }
}
