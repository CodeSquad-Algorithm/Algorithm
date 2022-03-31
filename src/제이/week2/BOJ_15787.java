import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class BOJ_15787{

    private int[] trains;

    private void solve() {
        readInput();

        int count = countPassedTrain();

        System.out.println(count);
    }

    private void readInput() {
        Scanner sc = new Scanner(System.in);

        int trainCount = sc.nextInt();
        int commandCount = sc.nextInt();

        trains = new int[trainCount+1];

        for (int i = 0; i < commandCount; i++) {
            int command = sc.nextInt();
            int trainIndex = sc.nextInt();
            if (command <= 2) {
                int seatIndex = sc.nextInt();
                processCommand(command, trainIndex, seatIndex);
            } else if( command <= 4) {
                processCommand(command, trainIndex);
            }
        }
    }

    private void processCommand(int command, int trainIndex, int seatIndex) {
        if (command == 1) {
            trains[trainIndex] = trains[trainIndex] | (1 << seatIndex);
            return;
        }

        if (command == 2) {
            trains[trainIndex] = trains[trainIndex] & ~(1 << seatIndex);
        }
    }

    private void processCommand(int command, int trainIndex) {
        if (command == 3) {
            trains[trainIndex] = trains[trainIndex] << 1;
            trains[trainIndex] = trains[trainIndex] & ((1 << 21) - 1);
            return;
        }

        if (command == 4) {
            trains[trainIndex] = trains[trainIndex] >> 1;
            trains[trainIndex] = trains[trainIndex] & ~1;
        }
    }

    private int countPassedTrain() {
        Set<Integer> passedTrain = new HashSet<>();

        for (int i = 1; i < trains.length; i++) {
            passedTrain.add(trains[i]);
        }

        return passedTrain.size();
    }

    public static void main(String[] args) {
        BOJ_15787 q = new BOJ_15787();
        q.solve();
    }
}
