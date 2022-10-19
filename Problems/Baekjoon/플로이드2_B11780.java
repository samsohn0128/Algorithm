import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class 플로이드2_B11780 {
    private static BufferedReader br;

    private static final int MAX = 1000000000;
    private static int N, M;
    private static int[][] buses;
    private static int[][] prices;
    private static int[][] previousCity;

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
        buses = new int[N + 1][N + 1];
        prices = new int[N + 1][N + 1];
        previousCity = new int[N + 1][N + 1];
        for (int i = 0; i < N + 1; i++) {
            for (int j = 0; j < N + 1; j++) {
                buses[i][j] = MAX;
                prices[i][j] = MAX;
                previousCity[i][j] = -1;
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            buses[start][end] = Math.min(buses[start][end], price);
        }
    }

    private static String solution() {
        StringBuilder sb = new StringBuilder();
        getMinPricesAndRoutes(sb);

        return sb.toString();
    }

    private static void getMinPricesAndRoutes(StringBuilder sb) {
        for (int i = 1; i <= N; i++) {
            getMinPricesFromStart(i);
        }
        appendMinPrices(sb);
        appendRoutes(sb);
    }

    private static void getMinPricesFromStart(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.accumulatePrice));

        prices[start][start] = 0;
        pq.offer(new Node(start, 0));
        while (!pq.isEmpty()) {
            Node temp = pq.poll();
            for (int i = 0; i < buses[temp.curCity].length; i++) {
                if (buses[temp.curCity][i] != MAX) {
                    if (prices[start][i] > temp.accumulatePrice + buses[temp.curCity][i]) {
                        prices[start][i] = temp.accumulatePrice + buses[temp.curCity][i];
                        previousCity[start][i] = temp.curCity;
                        pq.offer(new Node(i, prices[start][i]));
                    } else if (prices[start][i] == temp.accumulatePrice + buses[temp.curCity][i]) {
                        previousCity[start][i] = Math.min(previousCity[start][i], temp.curCity);
                    }
                }
            }
        }
    }

    private static void appendMinPrices(StringBuilder sb) {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (prices[i][j] != MAX) {
                    sb.append(prices[i][j]);
                } else {
                    sb.append(0);
                }
                sb.append(" ");
            }
            sb.append("\n");
        }
    }

    private static void appendRoutes(StringBuilder sb) {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (prices[i][j] != 0 && prices[i][j] != MAX) {
                    Stack<Integer> stack = new Stack<>();
                    int destination = j;
                    while (destination != -1) {
                        stack.push(destination);
                        destination = previousCity[i][destination];
                    }
                    sb.append(stack.size());
                    sb.append(" ");
                    while (!stack.isEmpty()) {
                        sb.append(stack.pop());
                        sb.append(" ");
                    }
                } else {
                    sb.append(0);
                }
                sb.append("\n");
            }
        }

    }

    private static class Node {

        private int curCity;
        private int accumulatePrice;

        public Node(int curCity, int accumulatePrice) {
            this.curCity = curCity;
            this.accumulatePrice = accumulatePrice;
        }
    }
}