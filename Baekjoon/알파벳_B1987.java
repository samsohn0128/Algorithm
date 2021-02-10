package Baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 알파벳_B1987 {

	static int R, C;
	static boolean[] visited = new boolean[26];
	static char[][] grid;
	static int ans, cnt;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		grid = new char[R][C];

		for (int i = 0; i < R; i++)
			grid[i] = br.readLine().toCharArray();

		dfs(0, 0);

		System.out.println(ans);
		br.close();
	}

	static void dfs(int y, int x) {
		cnt++;
		visited[grid[y][x] - 'A'] = true;
		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			if (ny >= 0 && ny < R && nx >= 0 && nx < C) {
				if (!visited[grid[ny][nx] - 'A']) {
					dfs(ny, nx);
				}
			}

		}
		ans = Math.max(ans, cnt);
		cnt--;
		visited[grid[y][x] - 'A'] = false;
	}
}
