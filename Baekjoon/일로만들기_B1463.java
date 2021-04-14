package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 일로만들기_B1463 {
	static int X;
	static int[] dp = new int[1000001];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		X = Integer.parseInt(br.readLine());
		for (int i = 1; i <= X; i++) {
			dp[i] = Integer.MAX_VALUE;
		}
		dp[1] = 0;
		dp[2] = 1;
		dp[3] = 1;
		for (int i = 4; i <= X; i++) {
			if (i % 2 == 0) {
				dp[i] = Math.min(dp[i], dp[i / 2] + 1);
			}
			if (i % 3 == 0) {
				dp[i] = Math.min(dp[i], dp[i / 3] + 1);
			}
			dp[i] = Math.min(dp[i], dp[i - 1] + 1);
		}
		System.out.println(dp[X]);

		br.close();
	}
}