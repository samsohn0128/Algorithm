import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 등산_B1486 {
    private static BufferedReader br;
    private static int N, M, T, D;
    private static int[][] heights;
    private static int[][][][] distances;
    private static final int[] dy = {-1, 1, 0, 0};
    private static final int[] dx = {0, 0, -1, 1};

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
        T = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        heights = new int[N][M];
        distances = new int[N][M][N][M];
        for (int i = 0; i < N; i++) {
            char[] temp = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if ('A' <= temp[j] && temp[j] <= 'Z') {
                    heights[i][j] = temp[j] - 'A';
                } else if ('a' <= temp[j] && temp[j] <= 'z') {
                    heights[i][j] = temp[j] - 'a' + 26;
                }
                for (int k = 0; k < N; k++) {
                    for (int l = 0; l < M; l++) {
                        distances[i][j][k][l] = -1;
                    }
                }
            }
        }

        br.close();
    }

    private static String solution() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                setDistances(i, j);
            }
        }

        int maxHeight = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (distances[0][0][i][j] != -1 && distances[i][j][0][0] != -1 && distances[0][0][i][j] + distances[i][j][0][0] <= D) {
                    maxHeight = Math.max(maxHeight, heights[i][j]);
                }
            }
        }
        sb.append(maxHeight);
        return sb.toString();
    }

    private static void setDistances(int startY, int startX) {
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(edge -> edge.weight));
        distances[startY][startX][startY][startX] = 0;
        for (int i = 0; i < 4; i++) {
            int ny = startY + dy[i];
            int nx = startX + dx[i];
            if (0 <= ny && ny < N && 0 <= nx && nx < M) {
                int curHeight = heights[startY][startX];
                int nextHeight = heights[ny][nx];
                if (Math.abs(curHeight - nextHeight) <= T) {
                    pq.offer(new Edge(ny, nx, calculateWeight(curHeight, nextHeight)));
                }
            }
        }

        while (!pq.isEmpty()) {
            Edge temp = pq.poll();
            if (distances[startY][startX][temp.endY][temp.endX] != -1) {
                continue;
            }
            distances[startY][startX][temp.endY][temp.endX] = temp.weight;
            for (int i = 0; i < 4; i++) {
                int ny = temp.endY + dy[i];
                int nx = temp.endX + dx[i];
                if (0 <= ny && ny < N && 0 <= nx && nx < M) {
                    int curHeight = heights[temp.endY][temp.endX];
                    int nextHeight = heights[ny][nx];
                    if (Math.abs(curHeight - nextHeight) <= T && distances[startY][startX][ny][nx] == -1) {
                        pq.offer(new Edge(ny, nx, temp.weight + calculateWeight(curHeight, nextHeight)));
                    }
                }
            }
        }
    }

    private static int calculateWeight(int curHeight, int nextHeight) {
        if (curHeight < nextHeight) {
            return (nextHeight - curHeight) * (nextHeight - curHeight);
        } else {
            return 1;
        }
    }

    private static class Edge {
        private int endY;
        private int endX;
        private int weight;

        public Edge(int endY, int endX, int weight) {
            this.endY = endY;
            this.endX = endX;
            this.weight = weight;
        }
    }
}