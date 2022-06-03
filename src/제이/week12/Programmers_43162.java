import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

class Programmers_43162 {
    private static final int CONNECTED = 1;
    private static final int DISCONNECTED = 0;

    public int solution(int n, int[][] computers) {
        int answer = 0;

        List<Computer> computerList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            computerList.add(new Computer(i));
        }

        for (int i = 0; i < computers.length; i++) {
            for (int j = 0; j <= i; j++) {
                if (computers[i][j] == CONNECTED) {
                    Computer current = computerList.get(i);
                    Computer other = computerList.get(j);

                    current.connect(other);
                    other.connect(current);
                }
            }
        }

        int netWorks = 0;
        boolean[] visit = new boolean[computerList.size()];

        for (int i = 0; i < computerList.size(); i++) {
            if (!visit[i]) {
                dfs(computerList, visit, i);
                netWorks++;
            }
        }

        return netWorks;
    }

    private void dfs(List<Computer> computerList, boolean[] visit, int first) {
        Stack<Computer> stack = new Stack<>();
        stack.add(computerList.get(first));
        visit[first] = true;

        while(!stack.isEmpty()) {
            Computer com = stack.pop();

            for (Computer connCom : com.getConnectedComputers()) {
                int val = connCom.getValue();
                if (!visit[val]) {
                    visit[val] = true;
                    stack.add(connCom);
                }
            }
        }
    }
}

class Computer {
    private final int value;
    private final List<Computer> connectedComputers = new ArrayList<>();

    public Computer(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void connect(Computer node) {
        connectedComputers.add(node);
    }

    public List<Computer> getConnectedComputers() {
        return Collections.unmodifiableList(connectedComputers);
    }
}

