import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

class City implements Comparable<City> {
    int number;
    int cost;

    public City(int number, int cost) {
        this.number = number;
        this.cost = cost;
    }

    @Override
    public int compareTo(City o) {
        return this.cost - o.cost;
    }
}

public class BOJ_1916 {

    static int cityCount;
    static int busCount;
    static List<List<City>> cityGraph = new ArrayList<>();
    static int startCity;
    static int endCity;

    public static void main(String[] args) throws Exception {
        readInput();

        int min = dijkstra(startCity, endCity);
        System.out.println(min);
    }

    private static int dijkstra(int startCity, int endCity) {
        PriorityQueue<City> heap = new PriorityQueue<>();
        int[] minCost = new int[cityCount + 1];
        boolean[] visit = new boolean[cityCount + 1];

        Arrays.fill(minCost, Integer.MAX_VALUE);

        heap.add(new City(startCity, 0));
        minCost[startCity] = 0;

        while (!heap.isEmpty()) {
            City city = heap.poll();
            int number = city.number;

            if (!visit[number]) {
                visit[number] = true;

                for (City nextCity : cityGraph.get(number)) {
                    int nextNumber = nextCity.number;
                    int sum = minCost[number] + nextCity.cost;
                    if (!visit[nextNumber] && sum < minCost[nextNumber]) {
                        minCost[nextNumber] = sum;
                        heap.add(new City(nextNumber, sum));
                    }
                }
            }
        }

        return minCost[endCity];
    }

    private static void readInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        cityCount = parseInt(br.readLine());

        for (int i = 0; i <= cityCount; i++) {
            cityGraph.add(new ArrayList<>());
        }

        busCount = parseInt(br.readLine());

        StringTokenizer st;

        for (int i = 0; i < busCount; i++) {
            st = new StringTokenizer(br.readLine());
            int start = parseInt(st.nextToken());
            int end = parseInt(st.nextToken());
            int cost = parseInt(st.nextToken());

            cityGraph.get(start).add(new City(end, cost));
        }

        st = new StringTokenizer(br.readLine());
        startCity = parseInt(st.nextToken());
        endCity = parseInt(st.nextToken());

        br.close();
    }

}