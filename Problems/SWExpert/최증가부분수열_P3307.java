package SWExpert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 최증가부분수열_P3307 {
	static int T;
	static int N;
	static int[] input = new int[1000];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				input[i] = Integer.parseInt(st.nextToken());
			}
			int[] len = new int[N];
			len[0] = 1;
			int ans = 0;
			for (int i = 1; i < N; i++) {
				len[i] = 1;
				for (int j = i - 1; j >= 0; j--) {
					if (input[j] < input[i]) {
						len[i] = Math.max(len[i], len[j] + 1);
					}
				}
				ans = Math.max(ans, len[i]);
			}
			System.out.println("#" + tc + " " + ans);
		}
	}
}