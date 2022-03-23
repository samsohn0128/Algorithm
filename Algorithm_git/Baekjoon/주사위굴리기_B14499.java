package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 주사위굴리기_B14499 {

	static int N, M, K;
	static int[][] grid = new int[21][21];
	static Node dice;
	static int up = 1, east = 3, south = 5;
	static int[] num = new int[7];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		dice = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		K = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			int cmd = Integer.parseInt(st.nextToken());
			boolean flag = true;
			switch (cmd) {
			case 1:
				flag = move1();
				break;
			case 2:
				flag = move2();
				break;
			case 3:
				flag = move3();
				break;
			case 4:
				flag = move4();
				break;
			}
			if (flag) {
				if (grid[dice.y][dice.x] == 0) {
					grid[dice.y][dice.x] = num[7 - up];
				} else {
					num[7 - up] = grid[dice.y][dice.x];
					grid[dice.y][dice.x] = 0;
				}
				System.out.println(num[up]);
			}
		}

		br.close();
	}

	static boolean move1() {
		if (dice.x + 1 < M) {
			int temp = up;
			up = 7 - east;
			east = temp;
			dice.x++;
			return true;
		} else
			return false;
	}

	static boolean move2() {
		if (dice.x - 1 >= 0) {
			int temp = up;
			up = east;
			east = 7 - temp;
			dice.x--;
			return true;
		}
		return false;
	}

	static boolean move3() {
		if (dice.y - 1 >= 0) {
			int temp = up;
			up = south;
			south = 7 - temp;
			dice.y--;
			return true;
		}
		return false;
	}

	static boolean move4() {
		if (dice.y + 1 < N) {
			int temp = up;
			up = 7 - south;
			south = temp;
			dice.y++;
			return true;
		}
		return false;
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