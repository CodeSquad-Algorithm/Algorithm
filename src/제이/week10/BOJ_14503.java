import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14503 {

    private static final int MAX_TURN_COUNT = 4;

    private static int N; // 세로
    private static int M; // 가로

    private static RobotVacuum robotVacuum;

    private static int[][] area;

    public static void main(String[] args) throws IOException {
        readInput();
        solve();
        System.out.println(robotVacuum.getCleaned());
    }

    private static void solve() {
        robotVacuum.cleanArea(area);
        robotVacuum.turnLeft();

        int turnLeftCount = 1;

        while (true) {
            Point nextPoint = Point.add(robotVacuum.getPoint(), robotVacuum.getDirection().getDirectionPoint());

            if (getAreaValue(area, nextPoint) == AreaStatus.EMPTY.getValue()) {

                robotVacuum.moveForward();
                robotVacuum.cleanArea(area);

                turnLeftCount = 0;
            }

            if (turnLeftCount == MAX_TURN_COUNT) {
                if (getAreaValue(area, nextPoint) == AreaStatus.WALL.getValue()) break;

                robotVacuum.moveBackward();
                turnLeftCount = 0;
            }

            robotVacuum.turnLeft();
            turnLeftCount++;
        }
    }

    private static int getAreaValue(int[][] area, Point point) {
        return area[point.y][point.x];
    }

    private static void readInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());
        Direction direction = Direction.valueOf(Integer.parseInt(st.nextToken()));

        robotVacuum = new RobotVacuum(new Point(col, row), direction);

        area = new int[N][M];

        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < M; x++) {
                int value = Integer.parseInt(st.nextToken());
                area[y][x] = value;
            }
        }
    }
}

class RobotVacuum {
    private Point point;
    private Direction direction;
    private int cleaned;

    public RobotVacuum(Point point, Direction direction) {
        this.point = point;
        this.direction = direction;
    }

    public Point getPoint() {
        return point;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getCleaned() {
        return cleaned;
    }

    public void cleanArea(int[][] area) {
        area[this.point.y][this.point.x] = AreaStatus.CLEANED.getValue();
        cleaned++;
    }

    public void turnLeft() {
        this.direction = this.direction.getNext();
    }

    public void moveForward() {
        this.point = Point.add(this.point, direction.getDirectionPoint());
    }

    public void moveBackward() {
        turnLeft();
        turnLeft();
        moveForward();
        turnLeft();
        turnLeft();
    }
}

enum AreaStatus {
    EMPTY(0),
    WALL(1),
    CLEANED(2);

    private final int value;

    AreaStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

enum Direction {
    UP(0, new Point(0, -1)),
    RIGHT(1, new Point(1, 0)),
    DOWN(2, new Point(0, 1)),
    LEFT(3, new Point(-1, 0));

    static {
        UP.next = LEFT;
        RIGHT.next = UP;
        DOWN.next = RIGHT;
        LEFT.next = DOWN;
    }

    private Direction next;
    private final int value;
    private final Point directionPoint;

    Direction(int value, Point directionPoint) {
        this.value = value;
        this.directionPoint = directionPoint;
    }

    public Direction getNext() {
        return next;
    }

    public int getValue() {
        return value;
    }

    public Point getDirectionPoint() {
        return directionPoint;
    }

    public static Direction valueOf(int value) {
        switch (value) {
            case 0: return UP;
            case 1: return RIGHT;
            case 2: return DOWN;
            case 3: return LEFT;
            default: throw new IllegalArgumentException();
        }
    }
}

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static Point add(Point p1, Point p2) {
        return new Point(p1.x + p2.x, p1.y + p2.y);
    }
}