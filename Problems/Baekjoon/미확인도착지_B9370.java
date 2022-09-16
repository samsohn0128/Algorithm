import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class 미확인도착지_B9370 {

    private static int numberOfTestCases;
    private static int numberOfVertices, numberOfEdges, numberOfDestinations;
    private static int start, smellVertex1, smellVertex2;
    private static List<Integer> destinations;
    private static int[] distances;
    private static List<Edge>[] edges;

    public static void main(String[] args) throws Exception {
        BufferedReader br = setBufferedReader();
        numberOfTestCases = Integer.parseInt(br.readLine());
        String answer = solution(br);
        System.out.println(answer);
        br.close();
    }

    private static BufferedReader setBufferedReader() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        return new BufferedReader(new InputStreamReader(System.in));
    }

    private static void init(BufferedReader br) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        numberOfVertices = Integer.parseInt(st.nextToken());
        numberOfEdges = Integer.parseInt(st.nextToken());
        numberOfDestinations = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        smellVertex1 = Integer.parseInt(st.nextToken());
        smellVertex2 = Integer.parseInt(st.nextToken());

        distances = new int[numberOfVertices + 1];
        edges = new List[numberOfVertices + 1];
        for (int i = 1; i <= numberOfVertices; i++) {
            edges[i] = new ArrayList<>();
            distances[i] = -1;
        }
        for (int i = 0; i < numberOfEdges; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            if (isSmellVertices(a, b)) {
                edges[a].add(new Edge(a, b, 2 * weight - 1));
                edges[b].add(new Edge(b, a, 2 * weight - 1));
            } else {
                edges[a].add(new Edge(a, b, 2 * weight));
                edges[b].add(new Edge(b, a, 2 * weight));
            }
        }

        destinations = new ArrayList<>();
        for (int i = 0; i < numberOfDestinations; i++) {
            destinations.add(Integer.parseInt(br.readLine()));
        }
    }

    private static String solution(BufferedReader br) throws Exception {
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < numberOfTestCases; t++) {
            init(br);
            dijkstra();
            destinations.stream().sorted().filter(destination -> distances[destination] % 2 == 1).forEach(destination -> sb.append(destination + " "));
            sb.append("\n");
        }
        return sb.toString();
    }

    private static void dijkstra() {
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(edge -> distances[edge.from] + edge.weight));
        distances[start] = 0;
        edges[start].forEach(edge -> pq.offer(edge));

        while (!pq.isEmpty()) {
            Edge curEdge = pq.poll();
            if (distances[curEdge.to] == -1) {
                distances[curEdge.to] = distances[curEdge.from] + curEdge.weight;
                edges[curEdge.to].forEach(nextEdge -> pq.offer(nextEdge));
            }
        }
    }

    private static boolean isSmellVertices(int v1, int v2) {
        return (v1 == smellVertex1 && v2 == smellVertex2) || (v1 == smellVertex2 && v2 == smellVertex1);
    }


    private static class Edge {
        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }
}