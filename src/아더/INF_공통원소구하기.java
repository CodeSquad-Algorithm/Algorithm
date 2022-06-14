package 아더;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class INF_공통원소구하기 {

    static int N, M;
    static int[] nums1, nums2;

    public static void main(String[] args) {
        try {
            input();
            solve();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void solve() {
        ArrayList<Integer> answers = new ArrayList<>();

        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int iIndex = 0, jIndex = 0;
        while (iIndex < N && jIndex < M) {
            if (nums1[iIndex] == nums2[jIndex]) {
                answers.add(nums1[iIndex]);
                iIndex++;
                jIndex++;
            } else if (nums1[iIndex] > nums2[jIndex]) {
                jIndex++;
            } else {
                iIndex++;
            }
        }

        for (Integer answer : answers) {
            System.out.println("answer = " + answer);
        }

    }

    private static void input() throws IOException {
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader(new File("/Users/woo-jinpark/Desktop/Park/05_Test/input/input.txt")));

        N = Integer.parseInt(br.readLine());
        nums1 = new int[N];
        String[] split = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            nums1[i] = Integer.parseInt(split[i]);
        }

        M = Integer.parseInt(br.readLine());
        nums2 = new int[M];
        split = br.readLine().split(" ");
        for (int i = 0; i < M; i++) {
            nums2[i] = Integer.parseInt(split[i]);
        }
    }
}
