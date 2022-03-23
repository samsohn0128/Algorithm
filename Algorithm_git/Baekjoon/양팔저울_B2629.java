package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 양팔저울_B2629 {

	static int N, M;
	static int[] weights = new int[31];
	static int[] beads = new int[8];
	static boolean[][] dp = new boolean[31][30 * 500 + 1];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			weights[i] = Integer.parseInt(st.nextToken());
		}

		recur(0, 0);

		M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			int bead = Integer.parseInt(st.nextToken());
			if (bead > 30 * 500) {
				System.out.print("N ");
			} else if (dp[N][bead]) {
				System.out.print("Y ");
			} else {
				System.out.print("N ");
			}
		}

		br.close();
	}

	static void recur(int wIdx, int totW) {
		if (wIdx > N) {
			return;
		}
		if (dp[wIdx][totW]) {
			return;
		}

		dp[wIdx][totW] = true;
		recur(wIdx + 1, totW + weights[wIdx]);
		recur(wIdx + 1, totW);
		recur(wIdx + 1, Math.abs(totW - weights[wIdx]));
	}
}