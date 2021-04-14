package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A화살표B_B16953 {
	static int A, B;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());

		int ret = dfs(A, 0);
		System.out.println(ret + 1);
		br.close();
	}

	static int dfs(long n, int depth) {
		if (n == B) {
			return depth;
		} else if (n > B) {
			return -2;
		}

		int ret1 = dfs(2 * n, depth + 1);
		int ret2 = dfs(10 * n + 1, depth + 1);

		if (ret1 == -2 && ret2 == -2)
			return -2;
		else if (ret1 == -2)
			return ret2;
		else if (ret2 == -2)
			return ret1;
		else
			return Math.min(ret1, ret2);
	}
}