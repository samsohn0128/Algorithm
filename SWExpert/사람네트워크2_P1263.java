package SWExpert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 사람네트워크2_P1263 {
	static int T;
	static int N;
	static int[][] adj = new int[1001][1001];
	static int[][] dist = new int[1001][1001];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					adj[i][j] = Integer.parseInt(st.nextToken());
					if (i == j)
						dist[i][j] = 0;
					else if (adj[i][j] != 0)
						dist[i][j] = adj[i][j];
					else
						dist[i][j] = 1000;
				}
			}

			for (int k = 0; k < N; k++) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
					}
				}
			}
			int ans = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				int temp = 0;
				for (int j = 0; j < N; j++) {
					temp += dist[i][j];
				}
				ans = Math.min(ans, temp);
			}
			System.out.println("#" + tc + " " + ans);
		}
	}
}