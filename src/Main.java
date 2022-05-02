import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static final int TAGGER = 1, RUNNER = 2, TREE = 4;

    private static int N, M, H, K;
    private static int[][] grid;
    private static NodeDir[] runners;
    private static Node[] trees;
    private static Tagger tagger;
    private static int answer;

    private static int[] dy = {1, 0, -1, 0}, dx = {0, 1, 0, -1}; // 하 우 상 좌

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        init(br);
        printGrid();
        for (int i = 0; i < K; i++) {
            playRunner();
            playTagger(i);
            printGrid();
        }
        System.out.println(answer);
        br.close();
    }

    private static void printGrid() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                System.out.print(grid[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("============================================");
    }

    private static void init(BufferedReader br) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        grid = new int[N + 1][N + 1];
        runners = new NodeDir[M];
        trees = new Node[H];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken()) - 1;
            grid[y][x] += RUNNER;
            runners[i] = new NodeDir(y, x, dir);
        }
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            grid[y][x] += TREE;
        }
        int middle = N / 2 + 1;

        tagger = new Tagger(middle, middle, 2, 1, 2, true);
        grid[middle][middle] += TAGGER;
    }

    private static void playRunner() {
        for (int i = 0; i < runners.length; i++) {
            if (runners[i].dir < 0) {
                continue;
            }
            int manhattanDistance = getManhattanDistance(runners[i]);
            if (manhattanDistance <= 3) {
                int ny = runners[i].y + dy[runners[i].dir];
                int nx = runners[i].x + dx[runners[i].dir];
                if (ny > 0 && ny <= N && nx > 0 && nx <= N) {
                    moveRunner(ny, nx, i);
                } else {
                    runners[i].dir = (runners[i].dir + 2) % 4;
                    int ny2 = runners[i].y + dy[runners[i].dir];
                    int nx2 = runners[i].x + dx[runners[i].dir];
                    moveRunner(ny2, nx2, i);
                }
            }
        }
    }

    private static int getManhattanDistance(NodeDir runner) {
        return Math.abs(tagger.y - runner.y) + Math.abs(tagger.x - runner.x);
    }

    private static void moveRunner(int ny, int nx, int i) {
        if ((grid[ny][nx] & TAGGER) == 0) {
            grid[ny][nx] += RUNNER;
            grid[runners[i].y][runners[i].x] -= RUNNER;
            runners[i].y = ny;
            runners[i].x = nx;
        }
    }

    private static void playTagger(int t) {
        moveTagger();
        if (tagger.moveCount == tagger.maxMoveCount) {
            tagger.moveCount = 0;
            changeTaggerDir();
        }
        answer += (t + 1) * getScore();
    }

    private static void moveTagger() {
        grid[tagger.y][tagger.x] -= TAGGER;
        tagger.y += dy[tagger.dir];
        tagger.x += dx[tagger.dir];
        grid[tagger.y][tagger.x] += TAGGER;
        tagger.moveCount++;
    }

    private static void changeTaggerDir() {
        if ((tagger.y == 0 && tagger.x == 0) || (tagger.y == N / 2 + 1 && tagger.x == N / 2 + 1)) {
            tagger.increasing = !tagger.increasing;
            tagger.dir = (tagger.dir + 2) % 4;
            tagger.turnCount = 0;
        } else if (tagger.increasing) {
            tagger.dir--;
            if (tagger.dir < 0) {
                tagger.dir += 4;
            }
            tagger.turnCount++;
            if (tagger.turnCount == tagger.maxTurnCount) {
                tagger.turnCount = 0;
                tagger.maxMoveCount++;
                if (tagger.maxMoveCount == N - 1) {
                    tagger.maxTurnCount++;
                }
            }
        } else {
            tagger.dir = (tagger.dir + 1) % 4;
            tagger.turnCount++;
            if (tagger.turnCount == tagger.maxTurnCount) {
                tagger.turnCount = 0;
                if (tagger.maxMoveCount == N - 1) {
                    tagger.maxTurnCount--;
                }
                tagger.maxMoveCount--;
            }
        }
    }

    private static int getScore() {
        int score = 0;
        for (int i = 0; i < 3; i++) {
            int temp = grid[tagger.y + dy[tagger.dir] * i][tagger.x + dx[tagger.dir] * i];
            if ((temp & TREE) == 0 && (temp & RUNNER) == 1) {
                score++;
            }
        }
        return score;
    }

    private static class Node {
        int y;
        int x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    private static class NodeDir extends Node {
        int dir;

        public NodeDir(int y, int x, int dir) {
            super(y, x);
            this.dir = dir;
        }
    }

    private static class Tagger extends NodeDir {
        int moveCount;
        int maxMoveCount;
        int turnCount;
        int maxTurnCount;
        boolean increasing;

        public Tagger(int y, int x, int dir, int maxMove, int maxTurnCount, boolean increasing) {
            super(y, x, dir);
            this.maxMoveCount = maxMove;
            this.maxTurnCount = maxTurnCount;
            this.increasing = increasing;
        }
    }
}