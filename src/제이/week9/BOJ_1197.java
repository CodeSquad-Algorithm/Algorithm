import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

public class BOJ_1197 {
    private static List<Point> startPoints = new ArrayList<>();;
    private static int[][] tomatoBox;
    private static final int EMPTY = -1;
    private static final int NOT_RIPE = 0;

    public static void main(String[] args) throws Exception {
        readInput();

        int days = 0;

        if (startPoints.size() > 0 && isAllTomatosRipe()) {
            System.out.print(days);
            return;
        }

        days = findAllTomatoRipeDays(startPoints);

        if (!isAllTomatosRipe()) {
            days = -1;
        }

        System.out.print(days);
    }

    private static void readInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int inputX = Integer.parseInt(st.nextToken());
        int inputY = Integer.parseInt(st.nextToken());

        tomatoBox = new int[inputY][inputX];

        for (int y = 0; y < inputY; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < inputX; x++) {
                tomatoBox[y][x] = Integer.parseInt(st.nextToken());
                if (tomatoBox[y][x] == 1) {
                    startPoints.add(new Point(x, y));
                }
            }
        }
    }

    private static boolean isAllTomatosRipe() {
        boolean isAllRipe = true;

        for (int[] arr : tomatoBox){
            for (int num : arr) {
                if (num == 0) {
                    isAllRipe = false;
                    break;
                }
            }
        }

        return isAllRipe;
    }

    private static int findAllTomatoRipeDays(List<Point> startPoints) {

        int maxRipeDays = 0;

        Queue<Point> queue = new LinkedList<>();

        for (int i=0; i<startPoints.size(); i++) {
            queue.add(startPoints.get(i));
        }

        int[] dirY = {1,-1,0,0};
        int[] dirX = {0,0,1,-1};

        while (!queue.isEmpty()) {
            Point current = queue.poll();
            int ripeDays = tomatoBox[current.y][current.x];
            int nextRipeDays = 0;

            for (int i=0; i<dirY.length; i++) {
                int newY = current.y + dirY[i];
                int newX = current.x + dirX[i];

                if (newY < 0 || newY >= tomatoBox.length || newX < 0 || newX >= tomatoBox[0].length) continue;
                if (tomatoBox[newY][newX] == EMPTY || tomatoBox[newY][newX] != NOT_RIPE) continue;

                nextRipeDays = ripeDays + 1;
                tomatoBox[newY][newX] = nextRipeDays;
                queue.add(new Point(newX, newY));

                if (maxRipeDays < nextRipeDays) maxRipeDays = nextRipeDays;
            }
        }

        return maxRipeDays - 1;
    }
}