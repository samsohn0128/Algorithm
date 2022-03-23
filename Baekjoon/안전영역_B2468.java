package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 안전영역_B2468 {

	static int N;
	static int[][] grid;
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		grid = new int[N][N];
		visited = new boolean[N][N];

		int maxHeight = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
				maxHeight = Math.max(maxHeight, grid[i][j]);
			}
		}

		int ans = 0;
		for (int h = 0; h < maxHeight; h++) {
			int cnt = 0;

			for (int i = 0; i < N; i++)
				Arrays.fill(visited[i], false);

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j] && grid[i][j] > h) {
						dfs(i, j, h);
						cnt++;
					}
				}
			}
			ans = Math.max(ans, cnt);
		}

		System.out.println(ans);

		br.close();
	}

	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	static void dfs(int y, int x, int h) {
		visited[y][x] = true;
		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			if (ny >= 0 && ny < N && nx >= 0 && nx < N) {
				if (!visited[ny][nx] && grid[ny][nx] > h) {
					dfs(ny, nx, h);
				}
			}
		}
	}
}