package Baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class 영역구하기_B2583 {

	static int M, N, K;
	static int grid[][];
	static boolean visited[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		grid = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());

			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());

			for (int j = x1; j < x2; j++) {
				for (int k = y1; k < y2; k++) {
					grid[j][k] = 1;
				}
			}
		}
		int ans = 0;
		ArrayList<Integer> ansArea = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visited[i][j] && grid[i][j] != 1) {
					ans++;
					area = 0;
					dfs(i, j);
					ansArea.add(area);
				}
			}
		}

		Collections.sort(ansArea);

		System.out.println(ans);
		for (Integer i : ansArea) {
			System.out.print(i + " ");
		}

		br.close();
	}

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int area;

	static void dfs(int x, int y) {
		visited[x][y] = true;
		area++;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
				if (!visited[nx][ny] && grid[nx][ny] != 1) {
					dfs(nx, ny);
				}
			}
		}
	}
}
