package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 롤케이크_B3985 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int L = Integer.parseInt(br.readLine());
		int N = Integer.parseInt(br.readLine());
		boolean[] visited = new boolean[L + 1];
		int ansLen1 = 0, ansLen2 = 0;
		int ans1 = 0, ans2 = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			if (ansLen1 < n2 - n1 + 1) {
				ansLen1 = n2 - n1 + 1;
				ans1 = i + 1;
			}

			int cnt = 0;
			for (int j = n1; j <= n2; j++) {
				if (!visited[j]) {
					cnt++;
					visited[j] = true;
				}
			}
			if (ansLen2 < cnt) {
				ansLen2 = cnt;
				ans2 = i + 1;
			}
		}
		System.out.println(ans1);
		System.out.println(ans2);
		br.close();
	}
}