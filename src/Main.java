import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final int BOARD_SIZE = 19;
    private static int[][] board = new int[BOARD_SIZE + 1][BOARD_SIZE + 1];
    private static final int[] dy = {1, 1, 0, -1};
    private static final int[] dx = {0, 1, 1, 1};

    public static void main(String[] args) throws Exception {
        init();
        String answer = solution();
        System.out.println(answer);
    }

    private static void init() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 1; i <= BOARD_SIZE; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= BOARD_SIZE; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        br.close();
    }

    private static String solution() {
        StringBuilder sb = new StringBuilder();
        Node node;
        Node startNode = new Node(1, 1);
        while (true) {
            node = findOneOrTwo(startNode);

            if (node == null) {
                startNode.y = 1;
                startNode.x++;
            } else if (isWinner(node)) {
                break;
            } else {
                if (startNode.y == BOARD_SIZE) {
                    startNode.x++;
                    startNode.y = 1;
                } else {
                    startNode.y++;
                }
            }

            if (startNode.x > BOARD_SIZE) {
                break;
            }
        }

        if (startNode.x < BOARD_SIZE + 1) {
            sb.append(board[node.y][node.x]).append('\n');
            sb.append(node.y).append(' ').append(node.x);
        } else {
            sb.append(0);
        }
        return sb.toString();
    }

    private static Node findOneOrTwo(Node startNode) {
        for (int i = startNode.y; i <= BOARD_SIZE; i++) {
            if (board[i][startNode.x] != 0) {
                return new Node(i, startNode.x);
            }
        }
        return null;
    }

    private static boolean isWinner(Node node) {
        int directionIndex = 0;
        while (directionIndex < 4) {
            int direction = findDirection(node, directionIndex);
            if (direction == -1) {
                return false;
            }
            int count = 0;
            while (true) {
                int ny = node.y + count * dy[direction];
                int nx = node.x + count * dx[direction];
                if (!isInBoard(ny, nx) || board[ny][nx] != board[node.y][node.x]) {
                    directionIndex++;
                    break;
                } else {
                    count++;
                }
            }

            if (count == 5) {
                return true;
            } else {
                directionIndex++;
            }
        }

        return false;
    }

    private static int findDirection(Node node, int from) {
        for (int i = from; i < 4; i++) {
            int ny = node.y + dy[i];
            int nx = node.x + dx[i];
            if (isInBoard(ny, nx) && board[ny][nx] == board[node.y][node.x]) {
                return i;
            }
        }
        return -1;
    }

    private static boolean isInBoard(int y, int x) {
        return 0 < y && y <= BOARD_SIZE && 0 < x && x <= BOARD_SIZE;
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