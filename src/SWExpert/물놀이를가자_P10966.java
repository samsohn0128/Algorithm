package SWExpert;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 물놀이를가자_P10966 {
	static int T;
	static int N, M;
	static char[][] grid;
	static int[][] dist;
	static Queue<Node> q = new LinkedList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력을 받기위한 BufferedReader
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			grid = new char[N][M];
			dist = new int[N][M];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					dist[i][j] = -1;
				}
			}

			for (int i = 0; i < N; i++) {
				grid[i] = br.readLine().toCharArray();
				for (int j = 0; j < M; j++) {
					if (grid[i][j] == 'W') {
						q.offer(new Node(i, j));
						dist[i][j] = 0;
					}
				}
			}

			while (!q.isEmpty()) {
				Node temp = q.poll();
				for (int i = 0; i < 4; i++) {
					int ny = temp.y + dy[i];
					int nx = temp.x + dx[i];
					if (ny >= 0 && ny < N && nx >= 0 && nx < M) {
						if (dist[ny][nx] == -1) {
							dist[ny][nx] = dist[temp.y][temp.x] + 1;
							q.offer(new Node(ny, nx));
						}
					}
				}
			}
			int ans = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (grid[i][j] == 'L')
						ans += dist[i][j];
				}
			}
			System.out.println("#" + tc + " " + ans);
		}
		br.close();
	}

	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

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