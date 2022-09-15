import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 운동_B1956 {

    private static int numberOfVertices;
    private static int numberOfEdges;
    private static int[][] adjacent;
    private static long[][] distances;

    private static final int MAX_DISTANCE = Integer.MAX_VALUE;


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
        distances = new long[numberOfVertices + 1][numberOfVertices + 1];

        for (int i = 0; i < numberOfEdges; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            adjacent[start][end] = weight;
        }
    }

    private static int solution() {
        setDistances();
        floydwarshall();

        int answer = getAnswer();
        return answer;
    }

    private static void setDistances() {
        for (int i = 1; i <= numberOfVertices; i++) {
            for (int j = 1; j <= numberOfVertices; j++) {
                if (adjacent[i][j] != 0) distances[i][j] = adjacent[i][j];
                else distances[i][j] = MAX_DISTANCE;
            }
        }
    }

    private static void floydwarshall() {
        for (int k = 1; k <= numberOfVertices; k++) {
            for (int i = 1; i <= numberOfVertices; i++) {
                for (int j = 1; j <= numberOfVertices; j++) {
                    distances[i][j] = Math.min(distances[i][j], distances[i][k] + distances[k][j]);
                }
            }
        }
    }

    private static int getAnswer() {
        long answer = Integer.MAX_VALUE;
        for (int i = 1; i <= numberOfVertices; i++) {
            answer = Math.min(answer, distances[i][i]);
        }
        return answer == Integer.MAX_VALUE ? -1 : (int) answer;
    }
}