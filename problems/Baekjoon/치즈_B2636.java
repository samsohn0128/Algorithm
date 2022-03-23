package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 치즈_B2636 {
	static int N, M;
	static int[][] grid = new int[101][101];
	static boolean[][] visited = new boolean[101][101];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int ansCnt = 0;
		int ansHour = 0;
		while (true) {
			int cnt = count();
			if (cnt == 0)
				break;

			ansCnt = cnt;
			ansHour++;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					visited[i][j] = false;
				}
			}
			bfs(new Integer[] { 0, 0 });
		}
		System.out.println(ansHour);
		System.out.println(ansCnt);
		br.close();
	}

	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	static void bfs(Integer[] node) {
		Queue<Integer[]> q = new LinkedList<>();
		q.offer(node);
		visited[node[0]][node[1]] = true;
		while (!q.isEmpty()) {
			Integer[] temp = q.poll();
			for (int i = 0; i < 4; i++) {
				int ny = temp[0] + dy[i];
				int nx = temp[1] + dx[i];
				if (ny >= 0 && ny < N && nx >= 0 && nx < M) {
					if (!visited[ny][nx] && grid[ny][nx] == 0) {
						visited[ny][nx] = true;
						q.offer(new Integer[] { ny, nx });
					} else if (!visited[ny][nx] && grid[ny][nx] == 1) {
						visited[ny][nx] = true;
						grid[ny][nx] = 0;
					}
				}
			}
		}
	}

	static int count() {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (grid[i][j] == 1)
					cnt++;
			}
		}
		return cnt;
	}
}