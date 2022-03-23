package SWExpert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 요리사_P4012 {
	static int T;
	static int N;
	static int[][] grid = new int[17][17];
	static int[] src = new int[17];
	static boolean[] visited = new boolean[17];
	static int[] tgt1;
	static int[] tgt2;
	static int ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력을 받기위한 BufferedReader
		StringTokenizer st;

		for (int i = 1; i <= 16; i++)
			src[i] = i;

		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= N; j++) {
					grid[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			tgt1 = new int[N / 2];
			tgt2 = new int[N / 2];
			for (int i = 1; i <= N; i++)
				src[i] = i;
			ans = Integer.MAX_VALUE;
			comb(0);
			System.out.println("#" + tc + " " + ans);
		}
		br.close();
	}

	static void comb(int srcIdx) {
		if (srcIdx == N + 1) {
			if (chk())
				ans = Math.min(ans, cal());
			return;
		}

		visited[srcIdx] = true;
		comb(srcIdx + 1);
		visited[srcIdx] = false;
		comb(srcIdx + 1);
	}

	static boolean chk() {
		int cnt = 0;
		for (int i = 1; i <= N; i++)
			if (visited[i])
				cnt++;

		if (cnt == N / 2)
			return true;
		else
			return false;
	}

	static int cal() {
		int tgtIdx1 = 0;
		int tgtIdx2 = 0;
		for (int i = 1; i <= N; i++)
			if (visited[i])
				tgt1[tgtIdx1++] = i;
			else
				tgt2[tgtIdx2++] = i;

		int ret = 0;
		for (int i = 0; i < N / 2; i++)
			for (int j = 0; j < N / 2; j++)
				ret += grid[tgt1[i]][tgt1[j]];

		for (int i = 0; i < N / 2; i++)
			for (int j = 0; j < N / 2; j++)
				ret -= grid[tgt2[i]][tgt2[j]];

		return ret > 0 ? ret : -ret;
	}
}