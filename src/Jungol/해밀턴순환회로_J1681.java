package Jungol;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 해밀턴순환회로_J1681 {
	static int N;
	static int[][] adj = new int[13][13];
	static boolean[] visited = new boolean[13];
	static int sum;
	static int ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				adj[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(1, 0);
		System.out.println(ans);
		br.close();
	}

	static void dfs(int cur, int cnt) {
		if (ans != 0 && ans < sum)
			return;
		if (cnt == N - 1) {
			if (adj[cur][1] != 0) {
				if (ans == 0)
					ans = sum + adj[cur][1];
				else
					ans = Math.min(ans, sum + adj[cur][1]);
			}
			return;
		}
		visited[cur] = true;
		for (int i = 1; i <= N; i++) {
			if (!visited[i] && adj[cur][i] != 0) {
				sum += adj[cur][i];
				dfs(i, cnt + 1);
				sum -= adj[cur][i];
			}
		}
		visited[cur] = false;
	}
}