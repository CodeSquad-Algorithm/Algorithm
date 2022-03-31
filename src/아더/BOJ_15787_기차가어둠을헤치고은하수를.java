package 아더;

import java.io.*;
import java.util.HashSet;

public class BOJ_15787_기차가어둠을헤치고은하수를 {

    static int N, M;
    static int[] trains;

    public static void main(String[] args) {
        try {
            input();
            print();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void print() {
        HashSet<Integer> distinctTrains = new HashSet<>();

        for (int i = 1; i <= N; i++) {
            distinctTrains.add(trains[i]);
        }

        System.out.println(distinctTrains.size());
    }

    private static void input() throws IOException {
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader(new File("/Users/woo-jinpark/Desktop/Park/05_Test/input/input.txt")));

        String[] split = br.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);
        trains = new int[N + 1];

        for (int i = 0; i < M; i++) {
            split = br.readLine().split(" ");
            int command = Integer.parseInt(split[0]);
            int trainNumber = Integer.parseInt(split[1]);
            int seatNumber = 0;
            if (split.length > 2) {
                seatNumber = Integer.parseInt(split[2]);
            }

            doCommand(command, trainNumber, seatNumber);
        }
    }

    private static void doCommand(int command, int trainNumber, int seatNumber) {
        if (command == 1) {
            trains[trainNumber] = trains[trainNumber] | (1 << seatNumber);
            return ;
        }
        if (command == 2) {
            trains[trainNumber] = trains[trainNumber] & ~(1 << seatNumber);
            return ;
        }
        if (command == 3) {
            trains[trainNumber] = trains[trainNumber] << 1;
            trains[trainNumber] = trains[trainNumber] & ((1 << 21) - 1);
            return ;
        }
        trains[trainNumber] = trains[trainNumber] >> 1;
        trains[trainNumber] = trains[trainNumber] & ~1;
    }
}
