package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 가장긴증가하는부분수열_B11053 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[] input = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		int[] len = new int[N];
		int ans = 0;
		for (int i = 0; i < N; i++) {
			len[i] = 1;
			for (int j = 0; j < i; j++) {
				if (input[j] < input[i] && len[j] + 1 > len[i]) {
					len[i] = len[j] + 1;
				}
			}
			ans = Math.max(ans, len[i]);
		}
		System.out.println(ans);
		br.close();
	}
}