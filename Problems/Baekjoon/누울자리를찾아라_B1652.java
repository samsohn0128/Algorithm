package Baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 누울자리를찾아라_B1652 {

	static int N;
	static char[][] grid;
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		grid = new char[N][N];
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++)
			grid[i] = br.readLine().toCharArray();

		int ansHorizontal = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N - 1; j++) {
				if (grid[i][j] == '.' && !visited[i][j])
					if (lieDownHorizontal(i, j) >= 2)
						ansHorizontal++;
			}
		}

		for (int j = 0; j < N; j++) {
			for (int i = 0; i < N; i++) {
				visited[i][j] = false;
			}
		}

		int ansVertical = 0;
		for (int j = 0; j < N; j++) {
			for (int i = 0; i < N - 1; i++) {
				if (grid[i][j] == '.' && !visited[i][j])
					if (lieDownVertical(i, j) >= 2)
						ansVertical++;
			}
		}

		System.out.println(ansHorizontal + " " + ansVertical);

		br.close();
	}

	static int lieDownHorizontal(int y, int x) {
		int cnt = 0;
		while (x < N) {
			if (grid[y][x] == '.') {
				cnt++;
				visited[y][x] = true;
			} else {
				break;
			}
			++x;
		}
		return cnt;
	}

	static int lieDownVertical(int y, int x) {
		int cnt = 0;
		while (y < N) {
			if (grid[y][x] == '.') {
				cnt++;
				visited[y][x] = true;
			} else {
				break;
			}
			++y;
		}

		return cnt;
	}

}