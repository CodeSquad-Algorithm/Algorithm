package 아더;

public class Pro_43162_네트워크 {
    static boolean[] visited;

    public static void main(String[] args) {
        Pro_43162_네트워크 pro = new Pro_43162_네트워크();

        int n1 = 3;
        int n2 = 3;
        int n3 = 3;
        int n4 = 3;
        int[][] computers1 = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        int[][] computers2 = {{1, 1, 0}, {1, 1, 1}, {0, 1, 1}};
        int[][] computers3 = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        int[][] computers4 = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};

        System.out.println(pro.solution(n1, computers1));
        System.out.println(pro.solution(n2, computers2));
        System.out.println(pro.solution(n3, computers3));
        System.out.println(pro.solution(n4, computers4));
    }

    public int solution(int n, int[][] computers) {
        int answer = 0;
        visited = new boolean[n];

        for (int i = 0; i < computers.length; i++) {
            if (!visited[i]) {
                dfs(i, computers);
                answer++;
            }
        }

        return answer;
    }

    private void dfs(int computer, int[][] computers) {
        visited[computer] = true;

        for (int i = 0; i < computers.length; i++) {
            if (!visited[i] && computers[computer][i] == 1) {
                dfs(i, computers);
            }
        }
    }
}
