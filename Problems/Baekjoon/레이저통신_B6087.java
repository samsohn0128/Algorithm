import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 레이저통신_B6087 {
    private static int W, H;
    private static char[][] map;
    private static int[][] minNumberOfMirrors;
    private static Node start, end;
    private static final int[] dy = {-1, 1, 0, 0};
    private static final int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        init();
        String answer = solution();
        System.out.println(answer);
    }

    private static void init() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new char[H][W];
        minNumberOfMirrors = new int[H][W];
        for (int i = 0; i < H; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < W; j++) {
                minNumberOfMirrors[i][j] = -1;
                if (map[i][j] == 'C') {
                    if (start == null) {
                        start = new Node(i, j);
                    } else {
                        end = new Node(i, j);
                    }
                }
            }
        }

        br.close();
    }

    private static String solution() {
        StringBuilder sb = new StringBuilder();

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.numberOfMirrors));

        pq.offer(start);
        minNumberOfMirrors[start.y][start.x] = 0;
        while (!pq.isEmpty()) {
            Node temp = pq.poll();
            for (int i = 0; i < 4; i++) {
                int ny = temp.y + dy[i];
                int nx = temp.x + dx[i];
                if (isInMap(ny, nx)) {
                    if (map[ny][nx] == '.' || map[ny][nx] == 'C') {
                        int numberOfMirrors = calculateNextNumberOfMirrors(temp, i);
                        if (minNumberOfMirrors[ny][nx] == -1) {
                            minNumberOfMirrors[ny][nx] = numberOfMirrors;
                            pq.offer(new Node(ny, nx, i, numberOfMirrors));
                        } else {
                            if (minNumberOfMirrors[ny][nx] >= numberOfMirrors) {
                                minNumberOfMirrors[ny][nx] = numberOfMirrors;
                                pq.offer(new Node(ny, nx, i, numberOfMirrors));
                            }
                        }

                    }
                }
            }
        }
        sb.append(minNumberOfMirrors[end.y][end.x]);
        return sb.toString();
    }

    private static int calculateNextNumberOfMirrors(Node node, int direction) {
        int numberOfMirrors = node.numberOfMirrors;
        if (node.direction != -1 && node.direction != direction) {
            numberOfMirrors++;
        }
        return numberOfMirrors;
    }

    private static boolean isInMap(int y, int x) {
        return 0 <= y && y < H && 0 <= x && x < W;
    }

    private static class Node {
        private int y;
        private int x;
        private int direction = -1;
        private int numberOfMirrors;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }

        public Node(int y, int x, int direction, int numberOfMirrors) {
            this.y = y;
            this.x = x;
            this.direction = direction;
            this.numberOfMirrors = numberOfMirrors;
        }
    }
}