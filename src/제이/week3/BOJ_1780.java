import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.awt.Point;

public class BOJ_1780 {

    private static int paperSize;
    private static int[][] paper;

    private static int minusOneCount;
    private static int zeroCount;
    private static int oneCount;

    public static void main(String[] args) throws IOException {
        readInput();

        int firstValue = paper[0][0];

        if (isAllSame(paperSize, new Point(0, 0), firstValue)) {
            addCountOfNumber(firstValue);
        } else {
            recursive(new Point(0,0), paperSize);
        }
        System.out.println(minusOneCount);
        System.out.println(zeroCount);
        System.out.println(oneCount);
    }

    private static void recursive(Point startPoint, int size) {
        int splitedSize = size / 3;

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                int nx = startPoint.x + (x * splitedSize);
                int ny = startPoint.y + (y * splitedSize);

                int firstValue = paper[ny][nx];

                if (splitedSize == 1) {
                    addCountOfNumber(paper[ny][nx]);
                    continue;
                }

                boolean allSame = isAllSame(splitedSize, new Point(nx, ny), firstValue);

                if (allSame) {
                    addCountOfNumber(firstValue);
                } else {
                    recursive(new Point(nx, ny), splitedSize);
                }
            }
        }
    }

    private static boolean isAllSame(int splitedSize, Point startPoint, int firstValue) {
        boolean allSame = true;

        int nx = startPoint.x;
        int ny = startPoint.y;

        for (int y = ny; y < ny + splitedSize; y++) {
            for (int x = nx; x < nx + splitedSize; x++) {
                int curValue = paper[y][x];
                allSame = (firstValue == curValue);

                if (!allSame) {
                    y = ny + splitedSize;
                    break;
                }
            }
        }

        return allSame;
    }

    private static void readInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        paperSize = Integer.parseInt(reader.readLine());
        paper = new int[paperSize][paperSize];

        for (int i = 0; i < paperSize; i++) {
            String[] row = reader.readLine().split(" ");
            for (int j = 0; j < row.length; j++) {
                paper[i][j] = Integer.parseInt(row[j]);
            }
        }
    }

    private static void addCountOfNumber(int num) {
        if (num == -1) minusOneCount++;
        else if (num == 0) zeroCount++;
        else if (num == 1) oneCount++;
    }
}