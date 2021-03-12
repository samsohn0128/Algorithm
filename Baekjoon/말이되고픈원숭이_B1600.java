package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 말이되고픈원숭이_B1600 {

	static int K, W, H;
	static int[][] grid;
	static boolean[][][] visited;
	static int ans = -1;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		K = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		grid = new int[H][W];
		visited = new boolean[K + 1][H][W];

		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		bfs(new Node(0, 0, 0, 0));
		System.out.println(ans);

		br.close();
	}

	static int[] dy = { -1, -2, -2, -1, 1, 2, 2, 1, -1, 1, 0, 0 };
	static int[] dx = { -2, -1, 1, 2, 2, 1, -1, -2, 0, 0, -1, 1 };

	static void bfs(Node n) {
		Queue<Node> q = new LinkedList<>();
		visited[n.kCnt][n.y][n.x] = true;
		q.offer(n);

		while (!q.isEmpty()) {
			Node temp = q.poll();

			if (temp.y == H - 1 && temp.x == W - 1) {
				ans = temp.cnt;
				break;
			}

			for (int i = 0; i < 12; i++) {
				if (temp.kCnt == K && i < 8)
					continue;

				int ny = temp.y + dy[i];
				int nx = temp.x + dx[i];
				if (ny >= 0 && ny < H && nx >= 0 && nx < W) {
					if (i < 8) {
						if (!visited[temp.kCnt + 1][ny][nx] && grid[ny][nx] == 0) {
							visited[temp.kCnt + 1][ny][nx] = true;
							q.offer(new Node(ny, nx, temp.kCnt + 1, temp.cnt + 1));
						}
					} else {
						if (!visited[temp.kCnt][ny][nx] && grid[ny][nx] == 0) {
							visited[temp.kCnt][ny][nx] = true;
							q.offer(new Node(ny, nx, temp.kCnt, temp.cnt + 1));
						}
					}
				}
			}
		}
	}

	static class Node {
		int y;
		int x;
		int kCnt;
		int cnt;

		public Node(int y, int x, int kCnt, int cnt) {
			this.y = y;
			this.x = x;
			this.kCnt = kCnt;
			this.cnt = cnt;
		}
	}
}