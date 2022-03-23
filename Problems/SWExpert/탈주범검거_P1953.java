package SWExpert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 탈주범검거_P1953 {
	static int T;
	static int N, M, R, C, L;
	static int[][] grid = new int[51][51];

	static boolean[][] visited = new boolean[51][51];
	static Queue<int[]> q = new LinkedList<>();
	static int[][] dy = new int[8][];
	static int[][] dx = new int[8][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력을 받기위한 BufferedReader
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
					visited[i][j] = false;
				}
			}

			int ans = 1;
			q.offer(new int[] { R, C, L - 1 });
			visited[R][C] = true;
			while (!q.isEmpty()) {
				int[] temp = q.poll();

				if (temp[2] > 0) {
					int p = grid[temp[0]][temp[1]];
					for (int i = 0; i < dy[p].length; i++) {
						int ny = temp[0] + dy[p][i];
						int nx = temp[1] + dx[p][i];
						if (ny >= 0 && ny < N && nx >= 0 && nx < M) {
							if (!visited[ny][nx] && grid[ny][nx] != 0) {
								boolean flag = false;
								for (int j = 0; j < dy[grid[ny][nx]].length; j++) {
									if (ny + dy[grid[ny][nx]][j] == temp[0] && nx + dx[grid[ny][nx]][j] == temp[1]) {
										flag = true;
										break;
									}
								}
								if (flag) {
									visited[ny][nx] = true;
									q.offer(new int[] { ny, nx, temp[2] - 1 });
									ans++;
								}
							}
						}
					}
				}
			}
			System.out.println("#" + tc + " " + ans);
		}
		br.close();
	}
}