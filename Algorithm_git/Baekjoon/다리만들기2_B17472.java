package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 다리만들기2_B17472 {

	static int N, M;
	static int[][] grid = new int[11][11];
	static int cnt = 0;

	static PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> e1.dist - e2.dist);
	static int[] root;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

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
				if (grid[i][j] == 1)
					grid[i][j] = -1;
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (grid[i][j] == -1) {
					cnt++;
					setNum(i, j);
				}
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (grid[i][j] != 0) {
					for (int k = 0; k < 4; k++) {
						Pair ret = getDist(i, j, k);
						if (ret != null) {
							pq.offer(new Edge(grid[i][j], ret.first, ret.second));
						}
					}
				}
			}
		}

		root = new int[cnt + 1];
		makeSet();
		int ans = 0;
		while (!pq.isEmpty()) {
			Edge temp = pq.poll();
			boolean flag = union(temp.from, temp.to);
			if (flag) {
				ans += temp.dist;
			}
		}

		boolean flag = true;
		int finalRoot = find(1);
		for (int i = 2; i <= cnt; i++) {
			if (find(root[i]) != finalRoot) {
				flag = false;
				break;
			}
		}
		if (flag)
			System.out.println(ans);
		else
			System.out.println(-1);

		br.close();
	}

	static void makeSet() {
		for (int i = 1; i <= cnt; i++) {
			root[i] = i;
		}
	}

	static int find(int n) {
		if (root[n] == n)
			return n;
		return root[n] = find(root[n]);
	}

	static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		if (rootA != rootB) {
			root[rootA] = rootB;
			return true;
		}
		return false;
	}

	static Pair getDist(int y, int x, int dir) {
		int dist = 1;
		while (true) {
			int ny = y + dy[dir] * dist;
			int nx = x + dx[dir] * dist;
			if (ny >= 0 && ny < N && nx >= 0 && nx < M) {
				if (grid[ny][nx] != grid[y][x]) {
					if (grid[ny][nx] != 0) {
						if (dist - 1 >= 2)
							return new Pair(grid[ny][nx], dist - 1);
						else
							return null;
					} else {
						dist++;
					}
				} else {
					return null;
				}
			} else {
				return null;
			}
		}
	}

	static void setNum(int y, int x) {
		grid[y][x] = cnt;
		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			if (ny >= 0 && ny < N && nx >= 0 && nx < M) {
				if (grid[ny][nx] == -1) {
					setNum(ny, nx);
				}
			}
		}
	}

	static class Edge {
		int from;
		int to;
		int dist;

		public Edge(int from, int to, int dist) {
			this.from = from;
			this.to = to;
			this.dist = dist;
		}
	}

	static class Pair {
		int first;
		int second;

		public Pair(int first, int second) {
			this.first = first;
			this.second = second;
		}
	}
}