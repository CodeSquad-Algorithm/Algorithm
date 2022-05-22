package 땃쥐;

public class Programmers_43162 {

    public int solution(int n, int[][] computers) {
        int answer = 0;
        int[] parents = initParents(n);
        unionAll(n, computers, parents);
        answer = countRoots(n, answer, parents);
        return answer;
    }

    private int countRoots(int n, int answer, int[] parents) {
        for (int i = 0; i< n; i++) {
            if (parents[i] == i) {
               answer++;
            }
        }
        return answer;
    }

    private void unionAll(int n, int[][] computers, int[] parents) {
        for (int i = 0; i< n; i++) {//0,1,2,...n-1 컴퓨터에 대하여
            for (int j = 0; j< n; j++) { // 0,1,2,...j-1과 연결상태를 확인
                if (i!=j && computers[i][j] == 1) { // 연결되어있는가?
                    union(parents, i, j);
                }
            }
        }
    }

    private int[] initParents(int n) {
        int[] parents = new int[n];
        for (int i = 0; i< n; i++) {
            parents[i] = i; // 초기상태의 자기 자신의 최상의 부모는 자기 자신
        }
        return parents;
    }

    private void union(int[] parents, int a, int b) {
        int aRoot = findParent(parents, a);
        int bRoot = findParent(parents, b);

        if (aRoot == bRoot) {
            return;
        } else if (aRoot < bRoot) {
            parents[bRoot] = aRoot;
        } else {
            parents[aRoot] = bRoot;
        }
    }

    private static int findParent(int[] parents, int a) {
        return (a == parents[a])
                ? a
                : (parents[a] = findParent(parents, parents[a]));
    }

}
