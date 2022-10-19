import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class 최소비용구하기2_B11779 {
    private static BufferedReader br;
    private static int N, M;
    private static int start, end;
    private static int[][] adjacent;
    private static int[] previousCity;
    private static int[] minPricesFromStart;
    private static PriorityQueue<Bus> pq = new PriorityQueue<>(Comparator.comparingInt(b -> b.price));


    public static void main(String[] args) throws Exception {
        init();
        String answer = solution();
        System.out.println(answer);
    }

    private static void init() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        previousCity = new int[N + 1];
        minPricesFromStart = new int[N + 1];
        for (int i = 0; i < minPricesFromStart.length; i++) {
            minPricesFromStart[i] = Integer.MAX_VALUE;
        }
        adjacent = new int[N + 1][N + 1];
        for (int i = 0; i < N + 1; i++) {
            for (int j = 0; j < N + 1; j++) {
                adjacent[i][j] = Integer.MAX_VALUE;
            }
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            adjacent[start][end] = Math.min(adjacent[start][end], weight);
        }
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
    }

    private static String solution() {
        StringBuilder sb = new StringBuilder();
        setMinPriceAndRoute();
        List<Integer> route = new ArrayList<>();
        getRoute(end, route);

        sb.append(minPricesFromStart[end]);
        sb.append("\n");
        sb.append(route.size());
        sb.append("\n");
        for (int i = 0; i < route.size(); i++) {
            sb.append(route.get(i));
            sb.append(" ");
        }
        return sb.toString();
    }

    private static void setMinPriceAndRoute() {
        for (int i = 0; i < N + 1; i++) {
            if (adjacent[start][i] != Integer.MAX_VALUE) {
                int nextCity = i;
                int price = adjacent[start][i];
                minPricesFromStart[nextCity] = price;
                previousCity[nextCity] = start;
                pq.offer(new Bus(nextCity, price));
            }
        }
        minPricesFromStart[start] = 0;
        previousCity[start] = -1;

        while (!pq.isEmpty()) {
            int curCity = pq.poll().end;
            for (int i = 0; i < N + 1; i++) {
                if (adjacent[curCity][i] != Integer.MAX_VALUE) {
                    int nextCity = i;
                    int price = adjacent[curCity][i];
                    if (minPricesFromStart[nextCity] > minPricesFromStart[curCity] + price) {
                        minPricesFromStart[nextCity] = minPricesFromStart[curCity] + price;
                        previousCity[nextCity] = curCity;
                        pq.offer(new Bus(nextCity, minPricesFromStart[nextCity]));
                    } else if (minPricesFromStart[nextCity] == minPricesFromStart[curCity] + price) {
                        previousCity[nextCity] = Math.min(previousCity[nextCity], curCity);
                    }
                }
            }
        }
    }

    private static void getRoute(int city, List<Integer> route) {
        Stack<Integer> stack = new Stack<>();
        while (true) {
            stack.push(city);
            city = previousCity[city];
            if (city == -1) break;
        }
        while (!stack.isEmpty()) {
            route.add(stack.pop());
        }
    }

    private static class Bus {
        private int end;
        private int price;

        public Bus(int end, int price) {
            this.end = end;
            this.price = price;
        }
    }
}