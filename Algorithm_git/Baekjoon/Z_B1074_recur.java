package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Z_B1074_recur {

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
		z(0, 0);
		System.out.println(ans);
		br.close();
	}

	static void z(int y, int x) {
		if (N == 1)
			return;
		N /= 2;

		if (r < y + N && c < x + N) {
			z(y, x);
		} else if (r < y + N && c >= x + N) {
			ans += N * N;
			z(y, x + N);
		} else if (r >= y + N && c < x + N) {
			ans += 2 * N * N;
			z(y + N, x);
		} else if (r >= y + N && c >= x + N) {
			ans += 3 * N * N;
			z(y + N, x + N);
		}
	}

}