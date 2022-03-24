package Baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 색종이붙이기_B17136 {

	static int[][] grid = new int[10][10];
	static boolean[][] visited = new boolean[10][10];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for (int i = 0; i < 10; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 10; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(0,0,0);
		if(ans == Integer.MAX_VALUE)
			ans = -1;
		System.out.println(ans);
		
		br.close();
	}

	static int[] papers = new int[6];
	static int ans = Integer.MAX_VALUE;

	static void dfs(int y, int x, int cnt) {
		if (ans <= cnt)
			return;

		while (grid[y][x] == 0) {
			if (++y >= 10) {
				if (++x >= 10) {
					ans = Math.min(ans, cnt);
					return;
				}
				y = 0;
			}
		}

		for (int l = 5; l >= 1; l--) {
			if (y + l > 10 || x + l > 10 || papers[l] == 5)
				continue;

			if (paper(y, x, l)) {
				update(y, x, l, 0);
				papers[l]++;
				dfs(y, x, cnt + 1);

				update(y, x, l, 1);
				papers[l]--;
			}
		}
	}

	static boolean paper(int y, int x, int l) {
		for (int i = 0; i < l; i++) {
			for (int j = 0; j < l; j++) {
				if (grid[y + i][x + j] == 0 || visited[y + i][x + j]) {
					return false;
				}
			}
		}
		return true;
	}

	static void update(int y, int x, int l, int value) {
		for (int i = 0; i < l; i++) {
			for (int j = 0; j < l; j++) {
				grid[y + i][x + j] = value;
			}
		}
	}
}