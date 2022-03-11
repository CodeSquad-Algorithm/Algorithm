import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2003{

    private int n;
    private int m;
    private int[] numbers;

    private void solve() throws IOException {
        readInput();

        int count = 0;

        for (int p1 = 0; p1 < numbers.length; p1++) {
            int sum = 0;
            int num1 = numbers[p1];

            // 첫 포인터의 숫자가 합보다 크면 더 탐색할 필요 없음
            if (num1 > m) continue;

            // 첫 포인터의 숫자가 합과 같으면 count 하고, 더 탐색할 필요 없음
            if (num1 == m) {
                count++;
                continue;
            }

            sum += num1;

            for (int p2 = p1 + 1; p2 < numbers.length; p2++) {
                int num2 = numbers[p2];
                sum += num2;
                if (sum == m) {
                    count++;
                    break;
                }

                // 만약 끝까지 모두 더했는데도 합보다 작으면, 이 숫자들로는 m 만큼의 합을 만들 수 없으므로 바로 종료
                if (p2 == numbers.length-1 && sum < m) {
                    System.out.println(count);
                    return;
                }
            }
        }

        System.out.println(count);
    }

    private void readInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        n = Integer.parseInt(split[0]);
        m = Integer.parseInt(split[1]);

        numbers = new int[n];

        split = br.readLine().split(" ");

        for (int i = 0; i < split.length; i++) {
            numbers[i] = Integer.parseInt(split[i]);
        }
    }

    public static void main(String[] args) throws IOException {
        BOJ_2003 q = new BOJ_2003();
        q.solve();
    }
}