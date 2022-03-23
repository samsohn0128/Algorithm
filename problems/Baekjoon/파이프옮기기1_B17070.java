package Baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 파이프옮기기1_B17070 {

	static int N;
	static int[][] grid = new int[17][17];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(1, 2, 0);
		System.out.println(cnt);

		br.close();
	}

	static int cnt;

	static void dfs(int y, int x, int direction) {
		if (y == N && x == N) {
			cnt++;
			return;
		}

		switch (direction) {
		case 0:
			if (x + 1 <= N)
				if (grid[y][x + 1] == 0)
					dfs(y, x + 1, 0);
			if (x + 1 <= N && y + 1 <= N)
				if (grid[y][x + 1] == 0 && grid[y + 1][x + 1] == 0 && grid[y + 1][x] == 0)
					dfs(y + 1, x + 1, 1);
			break;
		case 1:
			if (x + 1 <= N)
				if (grid[y][x + 1] == 0)
					dfs(y, x + 1, 0);
			if (y + 1 <= N)
				if (grid[y + 1][x] == 0)
					dfs(y + 1, x, 2);
			if (x + 1 <= N && y + 1 <= N)
				if (grid[y][x + 1] == 0 && grid[y + 1][x + 1] == 0 && grid[y + 1][x] == 0)
					dfs(y + 1, x + 1, 1);
			break;
		case 2:
			if (y + 1 <= N)
				if (grid[y + 1][x] == 0)
					dfs(y + 1, x, 2);
			if (y + 1 <= N && x + 1 <= N)
				if (grid[y + 1][x] == 0 && grid[y + 1][x + 1] == 0 && grid[y][x + 1] == 0)
					dfs(y + 1, x + 1, 1);
		}
	}
}