package Baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 보물섬_B2589 {

	static int N, M;
	static char[][] grid;
	static int[][] dist;
	static int ans;
	static Queue<Node> q = new LinkedList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		grid = new char[N][M];
		dist = new int[N][M];
		for (int i = 0; i < N; i++)
			grid[i] = br.readLine().toCharArray();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (grid[i][j] == 'L') {
					distInit();
					bfs(i, j);
					maxAns();
				}
			}
		}
		System.out.println(ans);

		br.close();
	}
	
	static void maxAns() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (ans < dist[i][j])
					ans = dist[i][j];
			}
		}
	}
	
	static void distInit() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				dist[i][j] = 0;
			}
		}
	}

	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	
	static void bfs(int y, int x) {
		q.offer(new Node(y, x));
		while (!q.isEmpty()) {
			Node temp = q.poll();
			for (int i = 0; i < 4; i++) {
				int ny = temp.y + dy[i];
				int nx = temp.x + dx[i];
				if (ny >= 0 && ny < N && nx >= 0 && nx < M && grid[ny][nx] == 'L') {
					if (ny != y || nx != x) {
						if (dist[ny][nx] == 0 || dist[ny][nx] > dist[temp.y][temp.x] + 1) {
							dist[ny][nx] = dist[temp.y][temp.x] + 1;
							q.offer(new Node(ny, nx));
						}
					}
				}

			}
		}
	}

	static class Node {
		int y;
		int x;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}

	}
}
