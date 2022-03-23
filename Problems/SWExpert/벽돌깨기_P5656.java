package SWExpert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 벽돌깨기_P5656 {
	static int T;
	static int ans;

	static int N, W, H;
	static int[][] grid;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			grid = new int[H][W];
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					grid[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			ans = Integer.MAX_VALUE;
			perm(0, grid);
			System.out.println("#" + tc + " " + ans);
		}
	}

	static void perm(int n, int[][] arr) {
		if (n == N) {
			int cnt = 0;
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					if (arr[i][j] != 0) {
						cnt++;
					}
				}
			}
			ans = Math.min(ans, cnt);
			return;
		}
		for (int i = 0; i < W; i++) {
			perm(n + 1, simulate(i, arr));
		}
	}

	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	static int[][] simulate(int n, int[][] arr) {
		int[][] ret = new int[H][W];
		for (int i = 0; i < H; i++) {
			System.arraycopy(arr[i], 0, ret[i], 0, W);
		}

		Queue<Node> q = new LinkedList<>();
		for (int i = 0; i < H; i++) {
			if (ret[i][n] != 0) {
				q.offer(new Node(i, n, ret[i][n]));
				ret[i][n] = 0;
				break;
			}
		}

		while (!q.isEmpty()) {
			Node temp = q.poll();
			for (int i = 0; i < 4; i++) {
				for (int j = 1; j < temp.num; j++) {
					int ny = temp.y + dy[i] * j;
					int nx = temp.x + dx[i] * j;
					if (ny >= 0 && ny < H && nx >= 0 && nx < W) {
						if (ret[ny][nx] != 0) {
							q.offer(new Node(ny, nx, ret[ny][nx]));
							ret[ny][nx] = 0;
						}
					} else {
						break;
					}
				}
			}
		}

		for (int i = 0; i < W; i++) {
			for (int j = H - 1; j >= 0; j--) {
				if (ret[j][i] == 0) {
					boolean flag = false;
					for (int k = j - 1; k >= 0; k--) {
						if (ret[k][i] != 0) {
							ret[j][i] = ret[k][i];
							ret[k][i] = 0;
							flag = true;
							break;
						}
					}
					if (!flag) {
						break;
					}
				}
			}
		}
		return ret;
	}

	static class Node {
		int y;
		int x;
		int num;

		public Node(int y, int x, int num) {
			this.y = y;
			this.x = x;
			this.num = num;
		}
	}
}