import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class PuyoPuyo_B11559 {
    private static BufferedReader br;
    private static final int FIELDS_HEIGHT = 12;
    private static final int FIELDS_WIDTH = 6;
    private static char[][] fields = new char[FIELDS_HEIGHT][FIELDS_WIDTH];
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
        for (int i = 0; i < FIELDS_HEIGHT; i++) {
            fields[i] = br.readLine().toCharArray();
        }
        br.close();
    }

    private static String solution() {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        while (true) {
            boolean isBombed = bomb();
            if (!isBombed) {
                break;
            }
            drop();
            count++;
        }
        sb.append(count);
        return sb.toString();
    }

    private static void print() {
        for (int i = 0; i < FIELDS_HEIGHT; i++) {
            for (int j = 0; j < FIELDS_WIDTH; j++) {
                System.out.print(fields[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    private static boolean bomb() {
        boolean isBombed = false;
        for (int i = 0; i < FIELDS_HEIGHT; i++) {
            for (int j = 0; j < FIELDS_WIDTH; j++) {
                if (fields[i][j] != '.') {
                    char color = fields[i][j];
                    Queue<Node> bombQueue = bfs(color, i, j);

                    if (bombQueue != null) {
                        isBombed = true;
                        while (!bombQueue.isEmpty()) {
                            Node temp = bombQueue.poll();
                            fields[temp.y][temp.x] = '.';
                        }
                    }
                }
            }
        }
        return isBombed;
    }

    private static void drop() {
        Queue<Character> queue = new LinkedList<>();
        for (int j = 0; j < FIELDS_WIDTH; j++) {
            for (int i = FIELDS_HEIGHT - 1; i >= 0; i--) {
                if (fields[i][j] != '.') {
                    queue.offer(fields[i][j]);
                }
            }
            for (int i = FIELDS_HEIGHT - 1; i >= 0; i--) {
                if (queue.isEmpty()) {
                    fields[i][j] = '.';
                } else {
                    fields[i][j] = queue.poll();
                }
            }
        }
    }

    private static Queue<Node> bfs(char color, int startY, int startX) {
        Queue<Node> queue = new LinkedList<>();
        Queue<Node> bombQueue = new LinkedList<>();
        boolean[][] visited = new boolean[FIELDS_HEIGHT][FIELDS_WIDTH];
        Node startNode = new Node(startY, startX);
        queue.offer(startNode);
        bombQueue.offer(startNode);
        visited[startY][startX] = true;
        while (!queue.isEmpty()) {
            Node temp = queue.poll();
            for (int i = 0; i < 4; i++) {
                int ny = temp.y + dy[i];
                int nx = temp.x + dx[i];
                if (isInFields(ny, nx) && !visited[ny][nx] && fields[ny][nx] == color) {
                    Node newNode = new Node(ny, nx);
                    queue.offer(newNode);
                    bombQueue.offer(newNode);
                    visited[ny][nx] = true;
                }
            }
        }
        return bombQueue.size() < 4 ? null : bombQueue;
    }

    private static boolean isInFields(int y, int x) {
        return 0 <= y && y < FIELDS_HEIGHT && 0 <= x && x < FIELDS_WIDTH;
    }

    private static class Node {
        private int y;
        private int x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}