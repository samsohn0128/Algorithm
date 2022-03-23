package SWExpert;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 디저트카페_P2015 {
	static int T;
	static int N;
	static int[][] grid;
	static boolean[] visited = new boolean[101];

	static int ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력을 받기위한 BufferedReader
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			grid = new int[N][N];
			ans = -1;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					grid[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					dfs(i, j, i, j, 0, 1);
				}
			}
			System.out.println("#" + tc + " " + ans);
		}

		br.close();
	}

	static int[] dy = { 1, 1, -1, -1 };
	static int[] dx = { 1, -1, -1, 1 };

	static void dfs(int startY, int startX, int y, int x, int dir, int depth) {
		visited[grid[y][x]] = true;
		int ny = y + dy[dir];
		int nx = x + dx[dir];
		if (ny >= 0 && ny < N && nx >= 0 && nx < N) {
			if (!visited[grid[ny][nx]]) {
				dfs(startY, startX, ny, nx, dir, depth + 1);
			} else if (ny == startY && nx == startX) {
				ans = Math.max(ans, depth);
			}
		}
		if (++dir == 4) {
			visited[grid[y][x]] = false;
			return;
		}
		ny = y + dy[dir];
		nx = x + dx[dir];
		if (startY != y || startX != x) {
			if (ny >= 0 && ny < N && nx >= 0 && nx < N) {
				if (!visited[grid[ny][nx]]) {
					dfs(startY, startX, ny, nx, dir, depth + 1);
				} else if (ny == startY && nx == startX) {
					ans = Math.max(ans, depth);
				}
			}
		}

		visited[grid[y][x]] = false;
	}
}