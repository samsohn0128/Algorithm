package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 뱀_B3190 {

	static int N, K, L;
	static int[][] grid = new int[101][101];
	static Cmd[] commands = new Cmd[101];
	static Node haed;
	static Node tail;
	static int dir = 0; // 0: right, 1: down, 2: left, 3: up
	static Queue<Integer> q = new LinkedList<>();

	static int[] dy = { 0, 1, 0, -1 };
	static int[] dx = { 1, 0, -1, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			grid[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1; // apple
		}
		L = Integer.parseInt(br.readLine());
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			commands[i] = new Cmd(Integer.parseInt(st.nextToken()), st.nextToken().charAt(0));
		}
		grid[1][1] = 2;// snake
		haed = new Node(1, 1);
		tail = new Node(1, 1);
		int cmdIdx = 0;
		int ans = 0;
		while (true) {
			boolean alive = move();
			ans++;
			if (!alive)
				break;

			if (cmdIdx < L && ans == commands[cmdIdx].second) {
				turn(commands[cmdIdx].direction);
				cmdIdx++;
			}
		}
		System.out.println(ans);
		br.close();
	}

	static void print() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				System.out.print(grid[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	static boolean move() {
		int ny = haed.y + dy[dir];
		int nx = haed.x + dx[dir];
		if (ny > 0 && ny <= N && nx > 0 && nx <= N) {
			q.offer(dir);

			if (grid[ny][nx] == 0) {
				grid[tail.y][tail.x] = 0;
				int temp = q.poll();
				tail.y += dy[temp];
				tail.x += dx[temp];
			} else if (grid[ny][nx] == 2) {
				return false;
			}
			grid[ny][nx] = 2;
			haed.y = ny;
			haed.x = nx;
		} else {
			return false;
		}
		return true;
	}

	static void turn(char c) {
		if (c == 'L') {
			if (--dir == -1) {
				dir = 3;
			}
		} else if (c == 'D') {
			if (++dir == 4) {
				dir = 0;
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

	static class Cmd {
		int second;
		char direction;

		public Cmd(int second, char direction) {
			this.second = second;
			this.direction = direction;
		}
	}
}