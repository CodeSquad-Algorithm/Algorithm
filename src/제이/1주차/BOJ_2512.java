import java.util.Scanner;

public class BOJ_2512{
    int n;
    int minBudget;
    int maxBudget;
    int budgetSum;
    int[] budgets;
    long totalBudget;

    private void solve() {
        readInput();

        int min = minBudget;
        int max = maxBudget;
        while (min <= max) {
            int mid = (min + max) / 2;

            int sum = 0;
            for (int budget : budgets) {
                if (budget <= mid) sum += budget;
                else sum += mid;
            }

            if (sum > totalBudget) max = mid - 1;
            else min = mid + 1;
        }

        System.out.println(max);
    }

    private void readInput() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        budgets = new int[n];
        minBudget = budgets[0];
        for (int i = 0; i < budgets.length; i++) {
            int b = sc.nextInt();
            budgets[i] = b;

            if (b < minBudget) {
                minBudget = b;
            } else if (b > maxBudget) {
                maxBudget = b;
            }

            budgetSum += b;
        }

        totalBudget = sc.nextLong();
    }

    public static void main(String[] args) {
        BOJ_2512 q = new BOJ_2512();
        q.solve();
    }
}
