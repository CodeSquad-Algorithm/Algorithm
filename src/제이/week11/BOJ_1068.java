import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1068 {

    private static final int ROOT_INDEX = -1;

    private static int removeTargetIndex;
    private static final List<Node> nodes = new ArrayList<>();
    private static List<Node> rootNodes = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        readInput();
        solve();
    }

    private static void solve() {
        nodes.get(removeTargetIndex).disable();

        int leafCount = findLeafCountDfs();

        System.out.println(leafCount);
    }

    private static int findLeafCountDfs() {
        int leafCount = 0;

        Stack<Node> nodeStack = new Stack<>();

        for (Node rootNode : rootNodes) {
            if (rootNode.isActive()) {
                nodeStack.add(rootNode);
            }
        }

        while (!nodeStack.isEmpty()) {
            Node node = nodeStack.pop();

            if (node.isActive() && !node.hasChild()) {
                leafCount++;
                continue;
            }

            for (Node child : node) {
                if (child.isActive()) {
                    nodeStack.add(child);
                }
            }
        }

        return leafCount;
    }

    private static void readInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int nodeCount = Integer.parseInt(br.readLine());

        for (int i = 0; i < nodeCount; i++) {
            nodes.add(new Node(i));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < nodeCount; i++) {
            Node currentNode = nodes.get(i);

            int parentIndex = Integer.parseInt(st.nextToken());
            if (parentIndex == ROOT_INDEX) {
                rootNodes.add(currentNode);
                continue;
            }

            nodes.get(parentIndex).addChildNode(currentNode);
        }

        removeTargetIndex = Integer.parseInt(br.readLine());
    }
}

class Node implements Iterable<Node> {
    private final Set<Node> childs;
    private final int index;
    private boolean isActive = true;

    public Node(int index) {
        childs = new HashSet<>();
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public boolean isActive() {
        return isActive;
    }

    public void addChildNode(Node node) {
        childs.add(node);
    }

    public boolean hasChild() {
        if (childs.isEmpty()) {
            return false;
        }

        for (Node child : childs) {
            if (child.isActive()) {
                return true;
            }
        }

        return false;
    }

    public void disable() {
        this.isActive = false;
        childs.clear();
    }

    @Override
    public Iterator<Node> iterator() {
        return childs.iterator();
    }
}

// 삽질1 : 2번째 줄에서 노드가 순서대로 나올 것이라고 생각했음