package SWExpert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 탈주범검거_P1953_2 {
	static int T;
	static int ans;

	static int N, M, R, C, L;
	static int[][] grid = new int[51][51];
	static int[][] dy = new int[8][];
	static int[][] dx = new int[8][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		dy[1] = new int[] { -1, 1, 0, 0 };
		dx[1] = new int[] { 0, 0, -1, 1 };
		dy[2] = new int[] { -1, 1 };
		dx[2] = new int[] { 0, 0 };
		dy[3] = new int[] { 0, 0 };
		dx[3] = new int[] { -1, 1 };
		dy[4] = new int[] { -1, 0 };
		dx[4] = new int[] { 0, 1 };
		dy[5] = new int[] { 1, 0 };
		dx[5] = new int[] { 0, 1 };
		dy[6] = new int[] { 1, 0 };
		dx[6] = new int[] { 0, -1 };
		dy[7] = new int[] { -1, 0 };
		dx[7] = new int[] { 0, -1 };

		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					grid[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			Queue<Node> q = new LinkedList<>();
			q.offer(new Node(R, C, grid[R][C]));
			grid[R][C] = 0;
			ans = 1;
			int size = 1;
			int cnt = 1;
			while (!q.isEmpty()) {
				if (cnt == L)
					break;

				Node temp = q.poll();
				for (int i = 0; i < dy[temp.num].length; i++) {
					int ny = temp.y + dy[temp.num][i];
					int nx = temp.x + dx[temp.num][i];
					if (ny >= 0 && ny < N && nx >= 0 && nx < M) {
						if (grid[ny][nx] != 0) {
							if (ny - temp.y == 1) {
								if (grid[ny][nx] == 1 || grid[ny][nx] == 2 || grid[ny][nx] == 4 || grid[ny][nx] == 7) {
									q.offer(new Node(ny, nx, grid[ny][nx]));
									grid[ny][nx] = 0;
									ans++;
								}
							} else if (ny - temp.y == -1) {
								if (grid[ny][nx] == 1 || grid[ny][nx] == 2 || grid[ny][nx] == 5 || grid[ny][nx] == 6) {
									q.offer(new Node(ny, nx, grid[ny][nx]));
									grid[ny][nx] = 0;
									ans++;
								}
							} else if (nx - temp.x == 1) {
								if (grid[ny][nx] == 1 || grid[ny][nx] == 3 || grid[ny][nx] == 6 || grid[ny][nx] == 7) {
									q.offer(new Node(ny, nx, grid[ny][nx]));
									grid[ny][nx] = 0;
									ans++;
								}
							} else if (nx - temp.x == -1) {
								if (grid[ny][nx] == 1 || grid[ny][nx] == 3 || grid[ny][nx] == 4 || grid[ny][nx] == 5) {
									q.offer(new Node(ny, nx, grid[ny][nx]));
									grid[ny][nx] = 0;
									ans++;
								}
							}
						}
					}
				}
				if (--size == 0) {
					size = q.size();
					cnt++;
				}
			}
			System.out.println("#" + tc + " " + ans);
		}
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