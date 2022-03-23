import java.util.Scanner;

public class BOJ_14888 {

    private static int numberCount;
    private static int[] numbers;

    private static int[] operators = new int[4];

    private static int max = Integer.MIN_VALUE;
    private static int min = Integer.MAX_VALUE;

    public static void main(String[] args) {
        readInput();

        recursiveCalc(numbers[0], 1);

        System.out.println(max);
        System.out.println(min);
    }

    private static void recursiveCalc(int sum, int nextIndex) {

        if (nextIndex == numberCount) {
            max = Math.max(sum, max);
            min = Math.min(sum, min);

            return;
        }

        for (int i = 0; i < 4; i++) {
            if (operators[i] > 0) {

                operators[i]--;

                switch (i) {
                    case 0: recursiveCalc(sum + numbers[nextIndex], nextIndex + 1); break;
                    case 1: recursiveCalc(sum - numbers[nextIndex], nextIndex + 1); break;
                    case 2: recursiveCalc(sum * numbers[nextIndex], nextIndex + 1); break;
                    case 3: recursiveCalc(sum / numbers[nextIndex], nextIndex + 1); break;
                }

                operators[i]++;
            }
        }
    }

    private static void readInput() {
        Scanner sc = new Scanner(System.in);

        numberCount = sc.nextInt();
        numbers = new int[numberCount];

        for (int i = 0; i < numberCount; i++) {
            numbers[i] = sc.nextInt();
        }

        for (int i = 0; i < 4; i++) {
            operators[i] = sc.nextInt();
        }
    }
}