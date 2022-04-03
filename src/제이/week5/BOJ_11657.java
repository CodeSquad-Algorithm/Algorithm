import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class Main {
    static final long INF = Long.MAX_VALUE;
    static final int MAX = 5_000_000;

    static int cityCount;
    static int busCount;
    static List<List<City>> graph = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        readInput();
        

        long[] minCost = bellmanFord(1);

        if (minCost == null || cityCount == 1) {
            System.out.println(-1);
        } else {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            for (int i = 2; i <= cityCount; i++) {
                long cost = minCost[i];
                bw.write(cost + "\n");
            }

            bw.flush();
            bw.close();
        }
    }

    private static long[] bellmanFord(int first) {
        long[] minCost = new long[cityCount + 1];
        Arrays.fill(minCost, INF);

        minCost[first] = 0;

        for (int i = 1; i < cityCount; i++) {
            for (int start = first; start <= cityCount; start++) {
                for (City city : graph.get(start)) {
                    if (minCost[start] == INF) break;

                    long sum = minCost[start] + city.cost;
                    if (sum < minCost[city.num]) {
                        minCost[city.num] = sum;
                    }
                }
            }
        }

        for (int start = first; start <= cityCount; start++) {
            for (City city : graph.get(start)) {
                if (minCost[start] > MAX) {
                    minCost[start] = -1;
                    break;
                }

                long sum = minCost[start] + city.cost;
                if (sum < minCost[city.num]) {
                    return null;
                }
            }
        }

        return minCost;
    }

    private static void readInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        cityCount = parseInt(st.nextToken());
        busCount = parseInt(st.nextToken());

        for (int i = 0; i <= cityCount; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < busCount; i++) {
            st = new StringTokenizer(br.readLine());
            int start = parseInt(st.nextToken());
            int end = parseInt(st.nextToken());
            int cost = parseInt(st.nextToken());

            graph.get(start).add(new City(end, cost));
        }

        br.close();
    }

    static class City implements Comparable<City> {
        int num;
        int cost;

        public City(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }

        @Override
        public int compareTo(City o) {
            return this.cost - o.cost;
        }
    }
}