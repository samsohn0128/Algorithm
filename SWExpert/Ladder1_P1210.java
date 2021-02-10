package SWExpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Ladder1_P1210 {

	static int[][] grid = new int[100][100];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int tc = 1; tc <= 10; tc++) {
			br.readLine();
			int x = 0;
			int y = 99;
			for (int i = 0; i < 100; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 100; j++) {
					grid[i][j] = Integer.parseInt(st.nextToken());
					if (grid[i][j] == 2) {
						x = j;
					}
				}
			}

			int direction = 0;
			while (y > 0) {
				while (x - 1 >= 0 && grid[y][x - 1] == 1 && direction != 2) {
					direction = 1;
					x--;
				}
				while (x + 1 < 100 && grid[y][x + 1] == 1 && direction != 1) {
					direction = 2;
					x++;
				}
				y--;
				direction = 0;
			}
			System.out.println("#" + tc + " " + x);
		}

		br.close();
	}
}
