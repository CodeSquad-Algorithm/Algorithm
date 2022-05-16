package 땃쥐;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BOJ_1068 {

    private static int N; // 노드의 갯수
    private static Node[] nodes;

    public static void main(String[] args) throws IOException {
        N = readInt();

        nodes = new Node[N];

        for (int i = 0; i < N; i++) {
            nodes[i] = new Node(i);
        }

        for (int i = 0; i < N; i++) {
            int parentNodeNumber = readInt();
            if (isNotRootNode(parentNodeNumber)) {
                nodes[parentNodeNumber].addChild(nodes[i]);
            }
        }

        int deleteNodeNumber = readInt();
        nodes[deleteNodeNumber].detach();

        int count = 0;

        for (Node node : nodes) {
            if (node.isLeafNode()) {
                count++;
            }
        }

        System.out.print(count);
    }

    private static boolean isNotRootNode(int parentNodeNumber) {
        return parentNodeNumber != -1;
    }

    private static int readInt() throws IOException {
        int value = 0;
        boolean negative = false;

        int input;
        while (true) {
            input = System.in.read();
            if (input == ' ' || input == '\n') {
                return (negative) ? -value : value;
            } else if (input == '-') {
                negative = true;
            } else {
                value = value * 10 + (input - 48);
            }
        }
    }
}

class Node {
    Node parent;
    List<Node> childs = new ArrayList<>();

    final int value;
    boolean detached;

    public Node(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void addChild(Node child) { // 양방향 참조
        childs.add(child);
        child.parent = this;
    }

    public Node getRootNode() {
        if (parent == null) {
            return this;
        }
        Node parent = this.parent;

        while(true) {
            if (parent.parent == null) {
                return parent;
            }
            parent = parent.parent;
        }
    }

    public void detach() {
        if (parent != null) {
            parent.childs.remove(this);
            this.parent = null;
        }
        detached = true;
    }

    public boolean isLeafNode() {
        return !isRootDetached() && (childs.size() == 0);
    }

    public boolean isRootDetached() {
        return getRootNode().detached;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return getValue() == node.getValue();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }

}
