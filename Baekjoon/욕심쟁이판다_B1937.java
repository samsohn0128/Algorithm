package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 욕심쟁이판다_B1937 {

	static int N;
	static int[][] grid;
	static int[][] dp;
	static int ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		grid = new int[N][N];
		dp = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			Arrays.fill(dp[i], -1);
			for (int j = 0; j < N; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				dfs(i, j);
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				ans = Math.max(ans, dp[i][j]);
			}
		}

		System.out.println(ans + 1);

		br.close();
	}

	static int[] dy = { -1, 1, 0, 0, };
	static int[] dx = { 0, 0, -1, 1 };

	static int dfs(int y, int x) {
		int ret = 0;
		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			if (ny >= 0 && ny < N && nx >= 0 && nx < N) {
				if (grid[ny][nx] > grid[y][x]) {
					if (dp[ny][nx] == -1) {
						ret = Math.max(ret, dfs(ny, nx) + 1);
					} else {
						ret = Math.max(ret, dp[ny][nx] + 1);
					}
				}
			}
		}
		return dp[y][x] = ret;
	}
}