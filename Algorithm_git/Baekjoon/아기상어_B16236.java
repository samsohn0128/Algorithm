package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 아기상어_B16236 {

	static int N;
	static int[][] grid = new int[21][21];

	static Node shark;
	static int sharkSize = 2;
	static int cnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		grid = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
				if (grid[i][j] == 9) {
					grid[i][j] = 0;
					shark = new Node(i, j, 0);
				}
			}
		}

		int ans = 0;
		while (true) {
			int sec = bfs(shark);
			if (sec == -1)
				break;
			ans += sec;
			if (++cnt == sharkSize) {
				sharkSize++;
				cnt = 0;
			}
		}
		System.out.println(ans);
		br.close();
	}

	static int[] dy = { -1, 0, 0, 1 };
	static int[] dx = { 0, -1, 1, 0 };

	static int bfs(Node n) {
		boolean[][] visited = new boolean[N][N];
		PriorityQueue<Node> q = new PriorityQueue<>();
		q.offer(n);
		visited[n.y][n.x] = true;
		int size = 1;
		int sec = 0;
		boolean flag = false;
		while (!q.isEmpty()) {
			Node temp = q.poll();
			if (grid[temp.y][temp.x] < sharkSize && grid[temp.y][temp.x] > 0) {
				grid[shark.y][shark.x] = 0;
				shark = temp;
				grid[shark.y][shark.x] = 0;
				flag = true;
				break;
			}
			for (int i = 0; i < 4; i++) {
				int ny = temp.y + dy[i];
				int nx = temp.x + dx[i];
				if (ny >= 0 && ny < N && nx >= 0 && nx < N) {
					if (grid[ny][nx] <= sharkSize && !visited[ny][nx]) {
						visited[ny][nx] = true;
						q.offer(new Node(ny, nx, sec));
					}
				}
			}
			if (--size == 0) {
				size = q.size();
				sec++;
			}
		}
		return flag ? sec : -1;
	}

	static class Node implements Comparable<Node> {
		int y;
		int x;
		int dist;

		public Node(int y, int x, int dist) {
			this.y = y;
			this.x = x;
			this.dist = dist;
		}

		@Override
		public int compareTo(Node o) {
			return this.dist != o.dist ? this.dist - o.dist : this.y != o.y ? this.y - o.y : this.x - o.x;
		}
	}
}