package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 빵집_B3109 {

	static int R, C;
	static int[][] grid;
	static boolean[][] visited;
	static boolean flag;
	static int ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		grid = new int[R][C];
		visited = new boolean[R][C];

		for (int i = 0; i < R; i++) {
			char[] input = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {

				if (j == 0)
					grid[i][j] = 1;
				else if (input[j] == '.')
					grid[i][j] = 0;
				else if (input[j] == 'x')
					grid[i][j] = 1;
			}
		}

		for (int i = 0; i < R; i++) {
			flag = false;
			dfs(i, 0);
		}
		System.out.println(ans);
		br.close();
	}

	// ru, r, rd
	static int[] dy = { -1, 0, 1 };

	// ru, r, rd, u, d
//	static int[] dy = { -1, 0, 1, -1, 1 };
//	static int[] dx = { 1, 1, 1, 0, 0 };

	static void dfs(int y, int x) {
		if (x == C - 1) {
			visited[y][x] = true;
			flag = true;
			ans++;
			return;
		}
		visited[y][x] = true;
		for (int i = 0; i < 3; i++) {
			if (flag)
				break;

			int ny = y + dy[i];
			int nx = x + 1;
			if (ny >= 0 && ny < R && nx >= 0 && nx < C) {
				if (grid[ny][nx] == 0 && !visited[ny][nx]) {
					dfs(ny, nx);
				}
			}
		}
	}
}