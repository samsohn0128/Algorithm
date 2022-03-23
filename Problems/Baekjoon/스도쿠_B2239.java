package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 스도쿠_B2239 {

	static int[][] grid = new int[9][9];
	static boolean[][] col = new boolean[9][10];
	static boolean[][] row = new boolean[9][10];
	static boolean[][] square = new boolean[9][10];
	static boolean flag;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < 9; i++) {
			char[] input = br.readLine().toCharArray();
			for (int j = 0; j < 9; j++) {
				grid[i][j] = input[j] - '0';
			}
		}
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				col[i][grid[i][j]] = true;
				row[i][grid[j][i]] = true;
			}
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				for (int i2 = 0; i2 < 3; i2++) {
					for (int j2 = 0; j2 < 3; j2++) {
						square[i * 3 + j][grid[i * 3 + i2][j * 3 + j2]] = true;
					}
				}
			}
		}

		recur(0, 0);
		br.close();
	}

	static void print() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(grid[i][j]);
			}
			System.out.println();
		}
	}

	static void recur(int y, int x) {
		if (y == 9) {
			print();
			flag = true;
			return;
		}

		if (grid[y][x] != 0) {
			if (x == 8)
				recur(y + 1, 0);
			else
				recur(y, x + 1);
		} else {
			for (int i = 0; i < 10; i++) {
				if (!col[y][i] && !row[x][i] && !square[y / 3 * 3 + x / 3][i]) {
					col[y][i] = true;
					row[x][i] = true;
					square[y / 3 * 3 + x / 3][i] = true;
					grid[y][x] = i;

					if (x == 8)
						recur(y + 1, 0);
					else
						recur(y, x + 1);
					if (flag)
						return;

					col[y][i] = false;
					row[x][i] = false;
					square[y / 3 * 3 + x / 3][i] = false;
					grid[y][x] = 0;
				}
			}
		}
	}
}