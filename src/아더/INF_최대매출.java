package 아더;

public class INF_최대매출 {

    public static void main(String[] args) {
        INF_최대매출 inf_최대매출 = new INF_최대매출();

        int N = 10;
        int K = 3;
        int[] salesRecords = {12, 15, 11, 20, 25, 10, 20, 19, 13, 15};

        System.out.println(inf_최대매출.solution(N, K, salesRecords));
    }

    private int solution(int n, int k, int[] salesRecords) {
        int[] sum = new int[n];
        int maxSalesRecord = Integer.MIN_VALUE;
        sum[0] = salesRecords[0];

        for (int i = 1; i < n; i++) {
            sum[i] = (sum[i - 1] + salesRecords[i]);
        }

        for (int i = 0; i < n - k; i++) {
            maxSalesRecord = Math.max(sum[i + k] - sum[i], maxSalesRecord);
        }

        return maxSalesRecord;
    }
}
