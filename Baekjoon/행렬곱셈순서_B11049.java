package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 행렬곱셈순서_B11049 {

	static int N;
	static int[][] RC = new int[501][2];
	static int[][] dp = new int[501][501];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			RC[i][0] = Integer.parseInt(st.nextToken());
			RC[i][1] = Integer.parseInt(st.nextToken());
		}
		topDown(0, N - 1);
		System.out.println(dp[0][N - 1]);

		br.close();
	}

	static int topDown(int s, int e) {
		if (s >= e) {
			return dp[s][e] = 0;
		}
		if (dp[s][e] != 0) {
			return dp[s][e];
		}
		if (e - s == 1) {
			return dp[s][e] = RC[s][0] * RC[s][1] * RC[e][1];
		}

		int min = Integer.MAX_VALUE;
		for (int i = s; i < e; i++) {
			min = Math.min(min, topDown(s, i) + topDown(i + 1, e) + RC[s][0] * RC[i][1] * RC[e][1]);
		}

		return dp[s][e] = min;
	}
}