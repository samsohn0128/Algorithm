package Baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 적록색약_B10026 {

	static int N;
	static char grid[][];
	static boolean visited1[][];
	static boolean visited2[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		grid = new char[N][N];
		visited1 = new boolean[N][N];
		visited2 = new boolean[N][N];
		for (int i = 0; i < N; i++)
			grid[i] = br.readLine().toCharArray();

		int ans1 = 0;
		int ans2 = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited1[i][j]) {
					ans1++;
					dfs(i, j);
				}
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited2[i][j]) {
					ans2++;
					dfs2(i, j);
				}
			}
		}
		System.out.println(ans1 + " " + ans2);

		br.close();
	}

	static int dy[] = { -1, 1, 0, 0 };
	static int dx[] = { 0, 0, -1, 1 };

	static void dfs(int y, int x) {
		visited1[y][x] = true;
		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			if (ny >= 0 && ny < N && nx >= 0 && nx < N && !visited1[ny][nx]) {
				if (grid[y][x] == grid[ny][nx]) {
					dfs(ny, nx);
				}
			}
		}
	}

	static void dfs2(int y, int x) {
		visited2[y][x] = true;
		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];

			if (ny >= 0 && ny < N && nx >= 0 && nx < N && !visited2[ny][nx]) {
				if (grid[y][x] == 'R' || grid[y][x] == 'G') {
					if (grid[ny][nx] == 'R' || grid[ny][nx] == 'G') {
						dfs2(ny, nx);
					}
				} else if (grid[y][x] == grid[ny][nx]) {
					dfs2(ny, nx);
				}
			}
		}
	}
}
