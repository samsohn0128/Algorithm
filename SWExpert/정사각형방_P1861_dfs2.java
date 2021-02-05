package SWExpert;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.StringTokenizer;

public class 정사각형방_P1861_dfs2 {

	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	// 상하좌우

	static int T;
	static int N;
	static int[][] grid;
	static int cnt;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt")); // input.txt로부터 입력을 받아옴
		System.setOut(new PrintStream("myOutput.txt")); // myOutput.txt로 정답을 출력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력을 받기위한 BufferedReader

		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			grid = new int[N][N];

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					grid[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int ans = -1;
			int ansY = -1;
			int ansX = -1;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					cnt = 1;
					dfs(i, j);
					if (cnt > ans) {
						ans = cnt;
						ansY = i;
						ansX = j;
					} else if (cnt == ans) {
						if (grid[i][j] < grid[ansY][ansX]) {
							ansY = i;
							ansX = j;
						}
					}
				}
			}
			System.out.println("#" + tc + " " + grid[ansY][ansX] + " " + ans);
		}

		br.close();
	}

	static void dfs(int y, int x) {
		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];

			if (ny >= 0 && ny < N && nx >= 0 && nx < N) {
				if (grid[ny][nx] == grid[y][x] + 1) {
					cnt++;
					dfs(ny, nx);
				}
			}
		}
	}
}