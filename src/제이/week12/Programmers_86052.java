import java.util.Arrays;

public class Programmers_86052 {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] res1 = sol.solution(new String[]{"SL", "LR"});
        int[] res2 = sol.solution(new String[]{"S"});
        int[] res3 = sol.solution(new String[]{"R","R"});

        System.out.println(Arrays.toString(res1)); // {16}
        System.out.println(Arrays.toString(res2)); // {1,1,1,1}
        System.out.println(Arrays.toString(res3)); // {4,4}
    }
}

class Solution {
    public int[] solution(String[] grid) {
        int[] answer = {};

        int[] nx = {0,0,-1,1};
        int[] ny = {-1,1,0,0};

        int yLen = grid.length;
        int xLen = grid[0].length();

        Direction[] directions = { Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT };

        for (int y = 0; y < yLen; y++) {
            for (int x = 0; x < xLen; x++) {
                Node[][] nodes = initializeNodes(grid);
                for (Direction direction : directions) {
                    int length = findCycle(nodes, x, y, direction);
                }
            }
        }

        return answer;
    }

    private int findCycle(Node[][] nodes, int startX, int startY, Direction direction) {
        int maxX = nodes[0].length - 1;
        int maxY = nodes.length - 1;
        int cycleLength = 0;

        Direction nextDirection = direction;
        int x = startX;
        int y = startY;

        boolean isVisited = false;
        Node currentNode = nodes[y][x];
        currentNode.setVisited(direction, InOut.OUT);
        nextDirection = currentNode.getNextDirection(direction);

        while (isVisited) {
            x += nextDirection.getX();
            y += nextDirection.getY();
            if (x < 0) x = maxX;
            if (y < 0) y = maxY;
            if (maxX <= x) x = 0;
            if (maxY <= y) y = 0;

            currentNode = nodes[y][x];
            isVisited = currentNode.isVisited(nextDirection, InOut.IN);

            if (isVisited) break;

            cycleLength++;
            currentNode.setVisited(nextDirection, InOut.IN);
        }

        return cycleLength;
    }

    private Node[][] initializeNodes(String[] grid) {
        Node[][] nodes = new Node[grid.length][grid[0].length()];

        for (int y = 0; y < grid.length; y++) {
            char[] row = grid[y].toCharArray();
            for (int x = 0; x < row.length; x++) {
                nodes[y][x] = new Node(Sign.valueOf(row[x]));
            }
        }

        return nodes;
    }
}

enum Direction {
    UP(0, -1, 0),
    DOWN(0, 1, 1),
    LEFT(-1, 0, 2),
    RIGHT(1, 0, 3);

    private int x;
    private int y;
    private int index;

    private Direction left;
    private Direction right;

    static {
        UP.left = LEFT;
        UP.right = RIGHT;

        DOWN.left = RIGHT;
        DOWN.right = LEFT;

        LEFT.left = DOWN;
        LEFT.right = UP;

        RIGHT.left = UP;
        RIGHT.right = DOWN;
    }

    Direction(int x, int y, int index) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getIndex() {
        return index;
    }

    public Direction getLeft() {
        return left;
    }

    public Direction getRight() {
        return right;
    }
}

enum Sign {
    S,L,R;

    public static Sign valueOf(char chr) {
        switch (chr) {
            case 'S': return S;
            case 'L': return L;
            case 'R': return R;
            default: throw new IllegalArgumentException();
        }
    }
}

enum InOut {
    IN(0),
    OUT(1);

    private int index;

    InOut(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}

class Node {

    private static final int directions = 4;
    private static final int inOutCount = 2;

    private final Sign sign;
    private final boolean[][] visit;

    public Node(Sign sign) {
        this.sign = sign;
        visit = new boolean[directions][inOutCount];
    }

    public boolean isVisited(Direction direction, InOut inOut) {
        return visit[direction.getIndex()][inOut.getIndex()];
    }

    public void setVisited(Direction direction, InOut inOut) {
        visit[direction.getIndex()][inOut.getIndex()] = true;
    }

    public Direction getNextDirection(Direction direction) {
        switch (sign) {
            case S: return direction;
            case L: return direction.getLeft();
            case R: return direction.getRight();
            default: throw new IllegalStateException();
        }
    }
}