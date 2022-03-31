import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Math.max;

public class BOJ_2579 {

    private static int size;
    private static int[] scores;
    private static int[] maxScores;

    public static void main(String[] args) throws IOException {
        readInput();

        maxScores[0] = scores[0];

        if (scores.length >= 2){
            maxScores[1] = scores[0] + scores[1];

            if (scores.length >= 3) {
                maxScores[2] = max(scores[0] + scores[2], scores[1] + scores[2]);

                for (int i = 3; i < size; i++) {
                    int calc1 = maxScores[i-2] + scores[i];
                    int calc2 = maxScores[i-3] + scores[i-1] + scores[i];
                    maxScores[i] = Math.max(calc1, calc2);
                }
            }
        }

        System.out.println(maxScores[size - 1]);
    }

    private static void readInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        size = Integer.parseInt(br.readLine());

        scores = new int[size];
        maxScores = new int[size];

        for (int i = 0; i < size; i++) {
            scores[i] = Integer.parseInt(br.readLine());
        }
    }
}