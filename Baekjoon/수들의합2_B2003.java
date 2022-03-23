package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 수들의합2_B2003 {

	static int N, M;
	static int[] input;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		input = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}

		int start = 0;
		int sum = 0;
		int ans = 0;
		for (int i = 0; i < N; i++) {
			sum += input[i];
			while (sum > M) {
				sum -= input[start++];
			}
			if (sum == M) {
				ans++;
				sum -= input[start++];
			}
		}
		System.out.println(ans);

		br.close();
	}
}