import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class 등산_B16681 {
    private static BufferedReader br;
    private static int N, M;
    private static long D, E;
    private static long[] heights;
    private static List<Edge>[] edges;
    private static long[][] distances;

    public static void main(String[] args) throws Exception {
        init();
        String answer = solution();
        System.out.println(answer);
    }

    private static void init() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Long.parseLong(st.nextToken());
        E = Long.parseLong(st.nextToken());

        heights = new long[N + 1];
        edges = new List[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            heights[i] = Long.parseLong(st.nextToken());
            edges[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edges[from].add(new Edge(to, weight));
            edges[to].add(new Edge(from, weight));
        }
        for (int i = 1; i <= N; i++) {
            edges[i].sort(Comparator.comparingLong(edge -> edge.weight));
        }
        br.close();
    }

    private static String solution() {
        StringBuilder sb = new StringBuilder();

        distances = new long[2][N + 1];
        Arrays.fill(distances[0], -1L);
        Arrays.fill(distances[1], -1L);

        boolean isFromHome = true;
        setDistances(isFromHome);
        setDistances(!isFromHome);

        long maxValue = calculateMaxValue();
        if (maxValue == Long.MIN_VALUE) {
            sb.append("Impossible");
        } else {
            sb.append(maxValue);
        }

        return sb.toString();
    }

    private static void setDistances(boolean isFromHome) {
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingLong(edge -> edge.weight));
        int distanceIndex = isFromHome ? 0 : 1;
        int startNumber = isFromHome ? 1 : N;
        for (int i = 0; i < edges[startNumber].size(); i++) {
            if (heights[startNumber] < heights[edges[startNumber].get(i).position]) {
                pq.offer(edges[startNumber].get(i));
            }
        }

        while (!pq.isEmpty()) {
            Edge temp = pq.poll();
            int curPosition = temp.position;
            long curWeight = temp.weight;
            if (distances[distanceIndex][curPosition] != -1) {
                continue;
            }
            distances[distanceIndex][curPosition] = curWeight;
            for (int i = 0; i < edges[curPosition].size(); i++) {
                int nextPosition = edges[curPosition].get(i).position;
                long nextWeight = edges[curPosition].get(i).weight;

                if (heights[nextPosition] > heights[curPosition] && distances[distanceIndex][nextPosition] == -1) {
                    pq.offer(new Edge(nextPosition, curWeight + nextWeight));
                }
            }
        }
    }

    private static long calculateMaxValue() {
        long maxValue = Long.MIN_VALUE;
        for (int i = 1; i <= N; i++) {
            if (distances[0][i] != -1 && distances[1][i] != -1) {
                long stamina = (distances[0][i] + distances[1][i]) * D;
                long value = heights[i] * E;
                maxValue = Math.max(maxValue, value - stamina);
            }
        }
        return maxValue;
    }

    private static class Edge {
        private int position;
        private long weight;

        public Edge(int position, long weight) {
            this.position = position;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "{" + position +
                    ", " + weight + "}";
        }
    }
}