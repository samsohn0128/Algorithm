import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 로봇청소기_B14503 {
    private static int N, M;
    private static int r, c, d;
    private static int[][] grid;
    private static boolean[][] visited;
    private static int answer;

    public static void main(String[] args) throws Exception {
        init();
        clean(r, c, d);
        System.out.println(answer);
    }

    public static void clean(int y, int x, int d) {
        if (!visited[y][x]) {
            visited[y][x] = true;
            answer++;
        }
        for (int i = 1; i <= 4; i++) {
            d = d - 1 >= 0 ? d - 1 : d - 1 + 4;
            if (d == 0) {
                if (grid[y - 1][x] != 1 && !visited[y - 1][x]) {
                    clean(y - 1, x, d);
                    return;
                }
            } else if (d == 1) {
                if (grid[y][x + 1] != 1 && !visited[y][x + 1]) {
                    clean(y, x + 1, d);
                    return;
                }
            } else if (d == 2) {
                if (grid[y + 1][x] != 1 && !visited[y + 1][x]) {
                    clean(y + 1, x, d);
                    return;
                }
            } else if (d == 3) {
                if (grid[y][x - 1] != 1 && !visited[y][x - 1]) {
                    clean(y, x - 1, d);
                    return;
                }
            }
        }

        if (d == 0) {
            if (grid[y + 1][x] != 1) {
                clean(y + 1, x, d);
            }
        } else if (d == 1) {
            if (grid[y][x - 1] != 1) {
                clean(y, x - 1, d);
            }
        } else if (d == 2) {
            if (grid[y - 1][x] != 1) {
                clean(y - 1, x, d);
            }
        } else if (d == 3) {
            if (grid[y][x + 1] != 1) {
                clean(y, x + 1, d);
            }
        }

    }

    private static void init() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        grid = new int[N][M];
        visited = new boolean[N][M];
        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

}