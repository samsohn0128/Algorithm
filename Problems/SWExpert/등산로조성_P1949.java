package SWExpert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 등산로조성_P1949 {
	static int T;
	static int N, K;
	static int[][] grid;
	static boolean[][] visited;
	static ArrayList<Node> maxHeight = new ArrayList<>();
	static int ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력을 받기위한 BufferedReader
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			init();

			int max = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					grid[i][j] = Integer.parseInt(st.nextToken());
					max = Math.max(max, grid[i][j]);
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (grid[i][j] == max) {
						maxHeight.add(new Node(i, j));
					}
				}
			}

			for (Node n : maxHeight) {
				dfs(n, true, 1);
			}
			System.out.println("#" + tc + " " + ans);
		}

		br.close();
	}

	static void init() {
		grid = new int[N][N];
		visited = new boolean[N][N];
		maxHeight.clear();
		ans = 0;
	}

	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	static void dfs(Node n, boolean flag, int cnt) {
		ans = Math.max(ans, cnt);
		visited[n.y][n.x] = true;
		for (int i = 0; i < 4; i++) {
			int ny = n.y + dy[i];
			int nx = n.x + dx[i];
			if (ny >= 0 && ny < N && nx >= 0 && nx < N) {
				if (!visited[ny][nx] && grid[n.y][n.x] > grid[ny][nx]) {
					dfs(new Node(ny, nx), flag, cnt + 1);
				} else if (!visited[ny][nx] && flag && grid[ny][nx] - grid[n.y][n.x] < K) {
					int temp = grid[ny][nx];
					grid[ny][nx] = grid[n.y][n.x] - 1;
					dfs(new Node(ny, nx), false, cnt + 1);
					grid[ny][nx] = temp;
				}
			}
		}
		visited[n.y][n.x] = false;
	}

	static class Node {
		int y;
		int x;

		public Node(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}
}