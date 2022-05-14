package 아더;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BOJ_1068_트리 {
    static int N, DEL;
    static List<Integer>[] adj;

    public static void main(String[] args) {
        try {
            input();
            delNodeAndChilderen(DEL);
            delNodeParents();
            solve();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void delNodeParents() {
        // 자식으로 남아있는 것 지우기
        for (List<Integer> a : adj) {
            if (a == null) continue;

            if (a.contains(DEL)) {
                a.remove(Integer.valueOf(DEL));
            }
        }
    }

    static void delNodeAndChilderen(int delNode) {
        // 해당 노드가 지워지면 자식노드들도 전부 삭제된다
        for (int node : adj[delNode]) {
            delNodeAndChilderen(node);
        }
        adj[delNode] = null;
    }

    static void solve() {
        // 단말 노드의 갯수를 세야한다.
        int leafNodeCnt = 0;
        for (int i = 0; i < N; i++) {
            if (adj[i] == null) continue;
            if (adj[i].size() == 0) leafNodeCnt++;
        }
        System.out.println(leafNodeCnt);
    }

    static void input() throws IOException {
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader(new File("/Users/woo-jinpark/Desktop/Park/05_Test/input/input.txt")));

        N = Integer.parseInt(br.readLine());
        adj = new ArrayList[N];

        for (int i = 0; i < N; i++) {
            adj[i] = new ArrayList<>();
        }

        String[] split = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            int iNum = Integer.parseInt(split[i]);
            // 루트노드 확인
            if (iNum == -1) continue;
            // 부모의 값에 index 넣기
            adj[iNum].add(i);
        }
        DEL = Integer.parseInt(br.readLine());
    }
}
