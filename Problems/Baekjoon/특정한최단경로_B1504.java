import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 특정한최단경로_B1504 {

    private static int numberOfVertices;
    private static int numberOfEdges;
    private static int[][] adjacent;

    private static int[] mustGoThroughVertices = new int[2];

    private static final int OVERFLOW = 500000000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = setBufferedReader();
        init(br);
        int answer = solution();
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
        adjacent = new int[numberOfVertices + 1][numberOfVertices + 1];
        for (int i = 0; i < numberOfEdges; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            adjacent[v1][v2] = weight;
            adjacent[v2][v1] = weight;
        }
        st = new StringTokenizer(br.readLine());
        mustGoThroughVertices[0] = Integer.parseInt(st.nextToken());
        mustGoThroughVertices[1] = Integer.parseInt(st.nextToken());
    }

    private static int solution() {
        int distStartAndV1 = getShortestDistance(1, mustGoThroughVertices[0]);
        int distStartAndV2 = getShortestDistance(1, mustGoThroughVertices[1]);

        int distV1AndV2 = getShortestDistance(mustGoThroughVertices[0], mustGoThroughVertices[1]);

        int distV1AndEnd = getShortestDistance(mustGoThroughVertices[0], numberOfVertices);
        int distV2AndEnd = getShortestDistance(mustGoThroughVertices[1], numberOfVertices);
        int minDistance = Math.min(distStartAndV1 + distV1AndV2 + distV2AndEnd,
                distStartAndV2 + distV1AndV2 + distV1AndEnd);
        return minDistance >= OVERFLOW ? -1 : minDistance;
    }

    private static int getShortestDistance(int start, int end) {
        PriorityQueue<Integer[]> pq = new PriorityQueue<>(Comparator.comparingInt(i -> i[1]));
        boolean[] visited = new boolean[numberOfVertices + 1];
        pq.offer(new Integer[]{start, 0});
        while (!pq.isEmpty()) {
            Integer[] temp = pq.poll();
            int curVertex = temp[0];
            int curDistance = temp[1];
            if (curVertex == end) {
                return curDistance;
            }
            visited[curVertex] = true;
            for (int i = 0; i < adjacent[curVertex].length; i++) {
                if (adjacent[curVertex][i] != 0 && !visited[i]) {
                    pq.offer(new Integer[]{i, curDistance + adjacent[curVertex][i]});
                }
            }
        }
        return OVERFLOW;
    }
}