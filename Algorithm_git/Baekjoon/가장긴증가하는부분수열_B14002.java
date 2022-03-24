package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class 가장긴증가하는부분수열_B14002 {

	static int N;
	static int[] input = new int[1001];
	static int[] len = new int[1001];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}

		int result = 0;
		for (int i = 0; i < N; i++) {
			len[i] = 1;
			for (int j = 0; j < i; j++) {
				if (input[j] < input[i] && len[j] + 1 > len[i]) {
					len[i] = len[j] + 1;
				}
			}
			result = Math.max(result, len[i]);
		}
		System.out.println(result);
		Stack<Integer> s = new Stack<>();
		for (int i = N - 1; i >= 0; i--) {
			if (len[i] == result) {
				s.push(input[i]);
				result--;
			}
		}
		while (!s.isEmpty()) {
			System.out.print(s.pop() + " ");
		}

		br.close();
	}
}