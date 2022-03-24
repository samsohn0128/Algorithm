package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Z_B1074_loop {

	static int N, r, c;

	static int ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		N = (int) Math.pow(2, N);
		// 원점
		int y = 0;
		int x = 0;

		while (N > 1) {
			N /= 2;

			if (r < y + N && c < x + N) {

			} else if (r < y + N && c >= x + N) {
				ans += N * N;
				x += N;
			} else if (r >= y + N && c < x + N) {
				ans += 2 * N * N;
				y += N;
			} else if (r >= y + N && c >= x + N) {
				ans += 3 * N * N;
				x += N;
				y += N;
			}
		}
		System.out.println(ans);
		br.close();
	}
}