package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 미세먼지안녕_B17144 {

	static int R, C, T;
	static int[][][] grid;
	static int[] air = new int[2];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		grid = new int[R][C][2];
		int airIdx = 0;
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				grid[i][j][0] = Integer.parseInt(st.nextToken());
				if (grid[i][j][0] == -1) {
					air[airIdx++] = i;
				}
			}
		}
		for (int i = 0; i < T; i++) {
			simulate();
		}
		int ans = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (grid[i][j][0] > 0)
					ans += grid[i][j][0];
			}
		}
		System.out.println(ans);
		br.close();
	}

	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	static void simulate() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (grid[i][j][0] > 0) {
					for (int k = 0; k < 4; k++) {
						int ny = i + dy[k];
						int nx = j + dx[k];
						if (ny >= 0 && ny < R && nx >= 0 && nx < C) {
							if (grid[ny][nx][0] != -1) {
								grid[ny][nx][1] += grid[i][j][0] / 5;
								grid[i][j][1] -= grid[i][j][0] / 5;
							}
						}
					}
				}
			}
		}

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				grid[i][j][0] += grid[i][j][1];
				grid[i][j][1] = 0;
			}
		}

		int y = air[0] - 1;
		int x = 0;
		while (y > 0) {
			grid[y][x][0] = grid[y - 1][x][0];
			y--;
		}
		while (x < C - 1) {
			grid[y][x][0] = grid[y][x + 1][0];
			x++;
		}
		while (y < air[0]) {
			grid[y][x][0] = grid[y + 1][x][0];
			y++;
		}
		while (x > 1) {
			grid[y][x][0] = grid[y][x - 1][0];
			x--;
		}
		grid[y][x][0] = 0;

		y = air[1] + 1;
		x = 0;
		while (y < R - 1) {
			grid[y][x][0] = grid[y + 1][x][0];
			y++;
		}
		while (x < C - 1) {
			grid[y][x][0] = grid[y][x + 1][0];
			x++;
		}
		while (y > air[1]) {
			grid[y][x][0] = grid[y - 1][x][0];
			y--;
		}
		while (x > 1) {
			grid[y][x][0] = grid[y][x - 1][0];
			x--;
		}
		grid[y][x][0] = 0;
	}
}