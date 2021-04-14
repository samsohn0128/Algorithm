package Baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 나이트의이동_B7562 {

	static int T;
	static int I;
	static int curY, curX, tarY, tarX;
	static int grid[][];

	static int[] dy = { -2, -1, 1, 2, 2, 1, -1, -2 };
	static int[] dx = { 1, 2, 2, 1, -1, -2, -2, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			I = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			curY = Integer.parseInt(st.nextToken());
			curX = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			tarY = Integer.parseInt(st.nextToken());
			tarX = Integer.parseInt(st.nextToken());

			grid = new int[I][I];
			for (int i = 0; i < I; i++) {
				for (int j = 0; j < I; j++) {
					grid[i][j] = 1000000007;
				}
			}
			dist = 0;
			bfs(curY, curX);

			System.out.println(grid[tarY][tarX]);
		}

		br.close();
	}

	static int dist;

	static void bfs(int y, int x) {
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(y, x));
		int size = 1;
		grid[y][x] = 0;

		while (!q.isEmpty()) {
			if (size-- == 0) {
				size = q.size();
				dist++;
			}
			Node temp = q.poll();
			if (temp.y == tarY && temp.x == tarX) {
				break;
			}

			for (int i = 0; i < 8; i++) {
				int ny = temp.y + dy[i];
				int nx = temp.x + dx[i];
				if (ny >= 0 && ny < I && nx >= 0 && nx < I) {
					if (grid[ny][nx] > grid[temp.y][temp.x] + 1) {
						grid[ny][nx] = grid[temp.y][temp.x] + 1;
						q.offer(new Node(ny, nx));
					}
				}
			}
		}
	}

	static class Node {
		public int y;
		public int x;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}

	}
}
