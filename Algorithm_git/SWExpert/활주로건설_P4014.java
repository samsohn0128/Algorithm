package SWExpert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 활주로건설_P4014 {
	static int T;
	static int N, X;
	static int[][] grid = new int[21][21];
	static int ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					grid[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			ans = 0;
			horizontalCheck();
			verticalCheck();

			System.out.println("#" + tc + " " + ans);
		}
	}

	static void horizontalCheck() {
		for (int i = 0; i < N; i++) {
			boolean ansFlag = true;
			boolean flag = false;
			int cnt = 1;
			for (int j = 1; j < N; j++) {
				if (grid[i][j] - grid[i][j - 1] == 1) {
					if (flag) {
						ansFlag = false;
						break;
					} else {
						if (cnt >= X) {
							cnt = 1;
						} else {
							ansFlag = false;
							break;
						}
					}
				} else if (grid[i][j - 1] - grid[i][j] == 1) {
					if (flag) {
						ansFlag = false;
						break;
					} else {
						flag = true;
						cnt = 1;
					}
				} else if (grid[i][j] == grid[i][j - 1]) {
					++cnt;
					if (flag) {
						if (cnt >= X) {
							cnt = 0;
							flag = false;
						}
					}
				} else {
					ansFlag = false;
					break;
				}
			}
			if (ansFlag && !flag) {
				ans++;
			}
		}
	}

	static void verticalCheck() {
		for (int j = 0; j < N; j++) {
			boolean ansFlag = true;
			boolean flag = false;
			int cnt = 1;
			for (int i = 1; i < N; i++) {
				if (grid[i][j] - grid[i - 1][j] == 1) {
					if (flag) {
						ansFlag = false;
						break;
					} else {
						if (cnt >= X) {
							cnt = 1;
						} else {
							ansFlag = false;
							break;
						}
					}
				} else if (grid[i - 1][j] - grid[i][j] == 1) {
					if (flag) {
						ansFlag = false;
						break;
					} else {
						flag = true;
						cnt = 1;
					}
				} else if (grid[i][j] == grid[i - 1][j]) {
					++cnt;
					if (flag) {
						if (cnt >= X) {
							cnt = 0;
							flag = false;
						}
					}
				} else {
					ansFlag = false;
					break;
				}
			}
			if (ansFlag && !flag) {
				ans++;
			}
		}
	}
}