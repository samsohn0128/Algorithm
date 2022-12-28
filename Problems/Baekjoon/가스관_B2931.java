import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 가스관_B2931 {
    private static BufferedReader br;
    private static int R, C;
    private static char[][] grid;
    private static int mY, mX, zY, zX;
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
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        grid = new char[R][C];
        for (int i = 0; i < R; i++) {
            grid[i] = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                if (grid[i][j] == 'M') {
                    mY = i;
                    mX = j;
                } else if (grid[i][j] == 'Z') {
                    zY = i;
                    zX = j;
                }
            }
        }

        br.close();
    }

    private static String solution() {
        StringBuilder sb = new StringBuilder();
        Node hackedNode = findHackedNode();
        sb.append(hackedNode.y + 1).append(" ").append(hackedNode.x + 1).append(" ");

        int[] isConnected = calculateConnected(hackedNode);

        if (Arrays.stream(isConnected).filter(i -> i == 1).count() > 1) {
            sb.append('+');
        } else {
            if (isConnected[0] == -1) {
                if (isConnected[1] == 1) {
                    sb.append('|');
                } else if (isConnected[2] == 1) {
                    sb.append('3');
                } else {
                    sb.append('2');
                }
            } else if (isConnected[1] == -1) {
                if (isConnected[0] == 1) {
                    sb.append('|');
                } else if (isConnected[2] == 1) {
                    sb.append('4');
                } else {
                    sb.append('1');
                }
            } else if (isConnected[2] == -1) {
                if (isConnected[0] == 1) {
                    sb.append('3');
                } else if (isConnected[1] == 1) {
                    sb.append('4');
                } else {
                    sb.append('-');
                }
            } else if (isConnected[3] == -1) {
                if (isConnected[0] == 1) {
                    sb.append('2');
                } else if (isConnected[1] == 1) {
                    sb.append('1');
                } else {
                    sb.append('-');
                }
            }
        }

        return sb.toString();
    }

    private static Node findHackedNode() {
        Queue<Node> queue = new LinkedList<>();
        for (int i = 0; i < 4; i++) {
            int ny = mY + dy[i];
            int nx = mX + dx[i];
            if (isInGrid(ny, nx)) {
                if (i == 0) {
                    if (grid[ny][nx] == '|' || grid[ny][nx] == '+' || grid[ny][nx] == '1' || grid[ny][nx] == '4') {
                        queue.offer(new Node(ny, nx, mY, mX));
                    }
                } else if (i == 1) {
                    if (grid[ny][nx] == '|' || grid[ny][nx] == '+' || grid[ny][nx] == '2' || grid[ny][nx] == '3') {
                        queue.offer(new Node(ny, nx, mY, mX));
                    }
                } else if (i == 2) {
                    if (grid[ny][nx] == '-' || grid[ny][nx] == '+' || grid[ny][nx] == '1' || grid[ny][nx] == '2') {
                        queue.offer(new Node(ny, nx, mY, mX));
                    }
                } else {
                    if (grid[ny][nx] == '-' || grid[ny][nx] == '+' || grid[ny][nx] == '3' || grid[ny][nx] == '4') {
                        queue.offer(new Node(ny, nx, mY, mX));
                    }
                }
            }
        }
        while (!queue.isEmpty()) {
            Node temp = queue.poll();
            if (grid[temp.y][temp.x] == '.') {
                return temp;
            }
            if (grid[temp.y][temp.x] == '-') {
                if (temp.x == temp.prevX - 1) {
                    queue.offer(new Node(temp.y, temp.x - 1, temp.y, temp.x));
                } else {
                    queue.offer(new Node(temp.y, temp.x + 1, temp.y, temp.x));
                }
            } else if (grid[temp.y][temp.x] == '|') {
                if (temp.y == temp.prevY + 1) {
                    queue.offer(new Node(temp.y + 1, temp.x, temp.y, temp.x));
                } else {
                    queue.offer(new Node(temp.y - 1, temp.x, temp.y, temp.x));
                }
            } else if (grid[temp.y][temp.x] == '+') {
                if (temp.y == temp.prevY - 1) {
                    queue.offer(new Node(temp.y - 1, temp.x, temp.y, temp.x));
                } else if (temp.y == temp.prevY + 1) {
                    queue.offer(new Node(temp.y + 1, temp.x, temp.y, temp.x));
                } else if (temp.x == temp.prevX - 1) {
                    queue.offer(new Node(temp.y, temp.x - 1, temp.y, temp.x));
                } else {
                    queue.offer(new Node(temp.y, temp.x + 1, temp.y, temp.x));
                }
            } else if (grid[temp.y][temp.x] == '1') {
                if (temp.y == temp.prevY - 1) {
                    queue.offer(new Node(temp.y, temp.x + 1, temp.y, temp.x));
                } else {
                    queue.offer(new Node(temp.y + 1, temp.x, temp.y, temp.x));
                }
            } else if (grid[temp.y][temp.x] == '2') {
                if (temp.y == temp.prevY + 1) {
                    queue.offer(new Node(temp.y, temp.x + 1, temp.y, temp.x));
                } else {
                    queue.offer(new Node(temp.y - 1, temp.x, temp.y, temp.x));
                }
            } else if (grid[temp.y][temp.x] == '3') {
                if (temp.y == temp.prevY + 1) {
                    queue.offer(new Node(temp.y, temp.x - 1, temp.y, temp.x));
                } else {
                    queue.offer(new Node(temp.y - 1, temp.x, temp.y, temp.x));
                }
            } else if (grid[temp.y][temp.x] == '4') {
                if (temp.y == temp.prevY - 1) {
                    queue.offer(new Node(temp.y, temp.x - 1, temp.y, temp.x));
                } else {
                    queue.offer(new Node(temp.y + 1, temp.x, temp.y, temp.x));
                }
            }
        }
        return null;
    }

    private static int[] calculateConnected(Node hackedNode) {
        int[] isConnected = new int[4];
        for (int i = 0; i < 4; i++) {
            int ny = hackedNode.y + dy[i];
            int nx = hackedNode.x + dx[i];
            if (isInGrid(ny, nx)) {
                if (ny == hackedNode.prevY && nx == hackedNode.prevX) {
                    isConnected[i] = -1;
                } else if (i == 0) {
                    if (grid[ny][nx] == '|' || grid[ny][nx] == '+' || grid[ny][nx] == '1' || grid[ny][nx] == '4') {
                        isConnected[i] = 1;
                    }
                } else if (i == 1) {
                    if (grid[ny][nx] == '|' || grid[ny][nx] == '+' || grid[ny][nx] == '2' || grid[ny][nx] == '3') {
                        isConnected[i] = 1;
                    }
                } else if (i == 2) {
                    if (grid[ny][nx] == '-' || grid[ny][nx] == '+' || grid[ny][nx] == '1' || grid[ny][nx] == '2') {
                        isConnected[i] = 1;
                    }
                } else {
                    if (grid[ny][nx] == '-' || grid[ny][nx] == '+' || grid[ny][nx] == '3' || grid[ny][nx] == '4') {
                        isConnected[i] = 1;
                    }
                }
            }
        }
        return isConnected;
    }

    private static boolean isInGrid(int ny, int nx) {
        return 0 <= ny && ny < R && 0 <= nx && nx < C;
    }

    private static class Node {
        int y;
        int x;
        int prevY;
        int prevX;

        public Node(int y, int x, int prevY, int prevX) {
            this.y = y;
            this.x = x;
            this.prevY = prevY;
            this.prevX = prevX;
        }
    }
}