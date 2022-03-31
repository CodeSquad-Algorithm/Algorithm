class Solution {

    private static final int WATER = 0;
    private static final int MOD_NUM = 1_000_000_007;

    public int solution(int m, int n, int[][] puddles) {
        Integer[][] map = new Integer[n][m];

        for (int[] puddle : puddles) {
            map[puddle[1]-1][puddle[0]-1] = WATER;
        }

        return getWaysCount(map, m, n);
    }

    private int getWaysCount(Integer[][] map, int m, int n) {
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < m; x++) {
                if (map[y][x] != null && map[y][x] == WATER) continue;

                if (y == 0 && x == 0) {
                    map[y][x] = 1;
                    continue;
                }

                int value = 0;
                if (y > 0 && map[y-1][x] != null) value += map[y-1][x];
                if (x > 0 && map[y][x-1] != null) value += map[y][x-1];

                map[y][x] = value % MOD_NUM;
            }
        }

        return (map[n-1][m-1] == null) ? 0 : map[n-1][m-1];
    }
}