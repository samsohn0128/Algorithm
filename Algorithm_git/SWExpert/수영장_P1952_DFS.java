package SWExpert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 수영장_P1952_DFS {
	static int T;
	static int[] price = new int[4];
	static int[] month = new int[12];
	static int ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력을 받기위한 BufferedReader
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++)
				price[i] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 12; i++)
				month[i] = Integer.parseInt(st.nextToken());

			ans = Integer.MAX_VALUE;
			dfs(0, 0);
			ans = Math.min(ans, price[3]);
			System.out.println("#" + tc + " " + ans);
		}
		br.close();
	}

	static void dfs(int n, int cost) {
		if (n >= 12) {
			ans = Math.min(ans, cost);
			return;
		}
		dfs(n + 1, cost + Math.min(month[n] * price[0], price[1]));
		dfs(n + 3, cost + price[2]);
	}
}