package 아더;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1541_잃어버린괄호 {

    static int ANSWER;
    static String INPUT;

    public static void main(String[] args) {
        try {
            input();
            solve();
            print();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void print() {
        System.out.println(ANSWER);
    }

    private static void solve() {
        StringBuilder sb = new StringBuilder();
        boolean isMinus = false;
        for (int i = 0; i < INPUT.length(); i++) {
            if (INPUT.charAt(i) != '+' && INPUT.charAt(i) != '-') {
                sb.append(INPUT.charAt(i));
            }
            if (INPUT.charAt(i) == '+' || INPUT.charAt(i) == '-' || i == INPUT.length() - 1) {
                if (isMinus) {
                    ANSWER -= Integer.parseInt(sb.toString());
                } else {
                    ANSWER += Integer.parseInt(sb.toString());
                }
                sb = new StringBuilder();
            }

            if (INPUT.charAt(i) == '-') {
                isMinus = true;
            }
        }
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ANSWER = 0;

        INPUT = br.readLine();
    }
}
