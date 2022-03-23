import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 탈출_B3055 {

    private static int R, C;
    private static char[][] grid;
    private static int[][] hedgeDay, waterDay;

    private static Node hedgeNode;

    private static int[] dy = {-1, 0, 1, 0}, dx = {0, -1, 0, 1};

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        init(br);
        waterBfs();
        int answer = hedgeBfs();
        if (answer == -1) {
            System.out.println("KAKTUS");
        } else {
            System.out.println(answer);
        }
        br.close();
    }

    private static int hedgeBfs() {
        Queue<Node> hedgeQueue = new LinkedList<>();
        hedgeQueue.offer(hedgeNode);
        grid[hedgeNode.y][hedgeNode.x] = '-';
        int hedgeSize = 1;
        int count = 1;
        boolean found = false;
        while (!hedgeQueue.isEmpty()) {
            Node hedgeTemp = hedgeQueue.poll();
            for (int i = 0; i < 4; i++) {
                int ny = hedgeTemp.y + dy[i];
                int nx = hedgeTemp.x + dx[i];
                if (0 <= ny && ny < R && 0 <= nx && nx < C) {
                    if (count < waterDay[ny][nx] || waterDay[ny][nx] == -1) {
                        if (grid[ny][nx] == '.' || grid[ny][nx] == 'D') {
                            if (grid[ny][nx] == 'D') {
                                found = true;
                                break;
                            }
                            grid[ny][nx] = '-';
                            hedgeQueue.offer(new Node(ny, nx));
                        }
                    }
                }
            }
            if (found) {
                break;
            }
            if (--hedgeSize == 0) {
                hedgeSize = hedgeQueue.size();
                count++;
            }
        }
        return found ? count : -1;
    }

    private static void waterBfs() {
        Queue<Node> waterQueue = new LinkedList<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (grid[i][j] == '*') {
                    waterDay[i][j] = 0;
                    waterQueue.offer(new Node(i, j));
                }
            }
        }
        int waterSize = waterQueue.size();
        int count = 1;
        while (!waterQueue.isEmpty()) {
            Node waterTemp = waterQueue.poll();
            for (int i = 0; i < 4; i++) {
                int ny = waterTemp.y + dy[i];
                int nx = waterTemp.x + dx[i];
                if (0 <= ny && ny < R && 0 <= nx && nx < C) {
                    if (grid[ny][nx] == '.' && waterDay[ny][nx] == -1) {
                        waterDay[ny][nx] = count;
                        waterQueue.offer(new Node(ny, nx));
                    }
                }
            }
            if (--waterSize == 0) {
                waterSize = waterQueue.size();
                count++;
            }
        }
    }

    private static void printGrid(String message) {
        System.out.println("============" + message + "===================");
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                System.out.print(grid[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void init(BufferedReader br) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        grid = new char[R][C];
        hedgeDay = new int[R][C];
        waterDay = new int[R][C];
        for (int i = 0; i < R; i++) {
            grid[i] = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                hedgeDay[i][j] = waterDay[i][j] = -1;
                if (grid[i][j] == 'S') {
                    hedgeNode = new Node(i, j);
                }
            }
        }
    }

    private static class Node {
        int y;
        int x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

}