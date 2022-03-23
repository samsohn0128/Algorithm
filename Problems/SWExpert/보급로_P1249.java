package SWExpert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 보급로_P1249 {
	static int T;
	static int N;
	static int[][] grid = new int[101][101];
	static int[][] dist = new int[101][101];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			for (int i = 0; i < N; i++) {
				Arrays.fill(dist[i], -1);
				char[] input = br.readLine().toCharArray();
				for (int j = 0; j < N; j++) {
					grid[i][j] = input[j] - '0';
				}
			}

			Queue<Node> q = new LinkedList<>();
			q.offer(new Node(0, 0));
			dist[0][0] = 0;
			while (!q.isEmpty()) {
				Node temp = q.poll();
				for (int i = 0; i < 4; i++) {
					int ny = temp.y + dy[i];
					int nx = temp.x + dx[i];
					if (ny >= 0 && ny < N && nx >= 0 && nx < N) {
						if (dist[ny][nx] == -1) {
							dist[ny][nx] = dist[temp.y][temp.x] + grid[ny][nx];
							q.offer(new Node(ny, nx));
						} else if (dist[ny][nx] > dist[temp.y][temp.x] + grid[ny][nx]) {
							dist[ny][nx] = dist[temp.y][temp.x] + grid[ny][nx];
							q.offer(new Node(ny, nx));
						}
					}
				}
			}
			System.out.println("#" + tc + " " + dist[N - 1][N - 1]);
		}
	}

	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	static class Node {
		int y;
		int x;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}