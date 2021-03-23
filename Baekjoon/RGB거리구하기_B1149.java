package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RGB거리구하기_B1149 {
	static int N;
	static int[][] price = new int[1001][3];
	static int[][] dp = new int[1001][3];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				price[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < 3; i++) {
			dp[1][i] = price[1][i];
		}
		for (int i = 2; i <= N; i++) {
			for (int j = 0; j < 3; j++) {
				int beforeMin = Integer.MAX_VALUE;
				for (int k = 0; k < 3; k++) {
					if (j != k) {
						beforeMin = Math.min(beforeMin, dp[i - 1][k]);
					}
				}
				dp[i][j] = beforeMin + price[i][j];
			}
		}

		int ans = Integer.MAX_VALUE;
		for (int i = 0; i < 3; i++) {
			ans = Math.min(ans, dp[N][i]);
		}
		System.out.println(ans);

		br.close();
	}
}