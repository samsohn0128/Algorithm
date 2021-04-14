package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 스타트와링크_B14889{

	static int N;
	static int[][] grid = new int[21][21];
	static boolean[] visited = new boolean[21];
	static int ans = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		comb(0, 0);
		System.out.println(ans);
		br.close();
	}

	static void comb(int srcIdx, int cnt) {
		if (cnt == N / 2) {
			int gap = calculate();
			ans = Math.min(ans, gap);
			return;
		}
		if (srcIdx == N) {
			return;
		}

		visited[srcIdx] = true;
		comb(srcIdx + 1, cnt + 1);
		visited[srcIdx] = false;
		comb(srcIdx + 1, cnt);
	}

	static int calculate() {
		int team1 = 0, team2 = 0;
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				if (visited[i] && visited[j]) {
					team1 += grid[i][j] + grid[j][i];
					System.out.println("team1 : " + i + " " + j);
				} else if (!visited[i] && !visited[j]) {
					team2 += grid[i][j] + grid[j][i];
					System.out.println("team2 : " + i + " " + j);
				}
			}
		}
		for (int i = 1; i <= N; i++) {
			System.out.print(visited[i] + " ");
		}
		System.out.println();
		System.out.println(Math.abs(team1 - team2));
		System.out.println("===========");
		return Math.abs(team1 - team2);
	}
}