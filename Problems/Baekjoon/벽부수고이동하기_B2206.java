import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 벽부수고이동하기_B2206 {

    private static int N, M;
    private static int[][] grid;
    private static int[][][] dist;
    private static int[] dy = {-1, 0, 1, 0}, dx = {0, -1, 0, 1};

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        init(br);

        bfs(0, 0);
        if (dist[N - 1][M - 1][0] == 0 && dist[N - 1][M - 1][1] == 0) {
            System.out.println(-1);
        } else if (dist[N - 1][M - 1][0] == 0) {
            System.out.println(dist[N - 1][M - 1][1]);
        } else if (dist[N - 1][M - 1][1] == 0) {
            System.out.println(dist[N - 1][M - 1][0]);
        } else {
            System.out.println(Math.min(dist[N - 1][M - 1][0], dist[N - 1][M - 1][1]));
        }
        br.close();
    }

    private static void init(BufferedReader br) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        grid = new int[N][M];
        dist = new int[N][M][2];
        for (int i = 0; i < N; i++) {
            char[] tempCharArr = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                grid[i][j] = tempCharArr[j] - '0';
            }
        }
    }

    private static void bfs(int y, int x) {
        Queue<Node> nodeQueue = new LinkedList<>();
        nodeQueue.offer(new Node(y, x, true));
        dist[y][x][0] = 1;
        int curDist = 1;
        int size = nodeQueue.size();
        while (!nodeQueue.isEmpty()) {
            Node tempNode = nodeQueue.poll();
            for (int i = 0; i < 4; i++) {
                int ny = tempNode.y + dy[i];
                int nx = tempNode.x + dx[i];
                if (checkRange(ny, nx)) {
                    if (grid[ny][nx] == 0) {
                        if (dist[ny][nx][tempNode.breakWall ? 0 : 1] == 0) {
                            dist[ny][nx][tempNode.breakWall ? 0 : 1] = curDist + 1;
                            nodeQueue.offer(new Node(ny, nx, tempNode.breakWall));
                        }
                    } else {
                        if (dist[ny][nx][1] == 0 && tempNode.breakWall) {
                            dist[ny][nx][1] = curDist + 1;
                            nodeQueue.offer(new Node(ny, nx, false));
                        }
                    }
                }
            }
            if (--size == 0) {
                size = nodeQueue.size();
                curDist++;
            }
        }
    }

    private static boolean checkRange(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < M;
    }

    private static class Node {
        int y;
        int x;
        boolean breakWall;

        public Node(int y, int x, boolean breakWall) {
            this.y = y;
            this.x = x;
            this.breakWall = breakWall;
        }
    }
}