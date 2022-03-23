package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 나무자르기_B2805 {

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

		Arrays.sort(input);

		int left = 0;
		int right = input[N - 1];

		int mid = 0;
		while (left < right) {
			mid = (left + right) / 2;
			long ret = cut(mid);
			if (ret < M)
				right = mid - 1;
			else if (ret > M)
				left = mid + 1;
			else
				break;
		}
		mid = (left + right) / 2;
		long ret = cut(mid);
		if (ret < M)
			System.out.println(mid - 1);
		else
			System.out.println(mid);

		br.close();
	}

	static long cut(int num) {
		long ret = 0;
		for (int i = N - 1; i >= 0; i--) {
			if (input[i] > num)
				ret += input[i] - num;
			else
				break;
		}
		return ret;
	}
}