import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 인구이동_B16234 {

    private static int N, L, R;
    private static int[][] grid;
    private static boolean[][] visited;
    private static boolean flag;

    private static int[] dy = {-1, 0, 1, 0}, dx = {0, -1, 0, 1};

    public static void main(String[] args) throws Exception {
        init();
        int answer = 0;
        while (!flag) {
            visited = new boolean[N][N];
            flag = true;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        union(i, j);
                    }
                }
            }
            if (!flag) {
                answer++;
            }
        }
        System.out.println(answer);
    }

    public static void union(int y, int x) {
        Queue<Country> unionQueue = new LinkedList<>();
        Queue<Country> afterUnionQueue = new LinkedList<>();
        int unionPopulation = 0;
        Country country = new Country(y, x);
        unionQueue.offer(country);
        visited[y][x] = true;
        while (!unionQueue.isEmpty()) {
            Country tempCountry = unionQueue.poll();
            afterUnionQueue.offer(tempCountry);
            unionPopulation += grid[tempCountry.y][tempCountry.x];
            for (int i = 0; i < 4; i++) {
                int ny = tempCountry.y + dy[i];
                int nx = tempCountry.x + dx[i];
                if (ny >= 0 && ny < N && nx >= 0 && nx < N) {
                    int diff = Math.abs(grid[ny][nx] - grid[tempCountry.y][tempCountry.x]);
                    if (!visited[ny][nx] && L <= diff && diff <= R) {
                        visited[ny][nx] = true;
                        unionQueue.offer(new Country(ny, nx));
                    }
                }
            }
        }
        int size = afterUnionQueue.size();
        if (size > 1) {
            flag = false;
            while (!afterUnionQueue.isEmpty()) {
                Country tempCountry = afterUnionQueue.poll();
                grid[tempCountry.y][tempCountry.x] = unionPopulation / size;
            }
        }

    }

    private static void init() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        grid = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static class Country {
        int y;
        int x;

        public Country(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}