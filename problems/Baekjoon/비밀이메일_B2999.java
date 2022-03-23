package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 비밀이메일_B2999 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		char[] input = br.readLine().toCharArray();
		int N = input.length;

		int R = 0;
		int C = 0;
		for (int i = 1; i < N; i++) {
			if (N % i == 0) {
				if (i <= N / i) {
					C = i;
					R = N / i;
				}
			}
		}
		char[][] grid = new char[R][C];
		int index = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				grid[i][j] = input[index++];
			}
		}

		for (int j = 0; j < C; j++) {
			for (int i = 0; i < R; i++) {
				System.out.print(grid[i][j]);
			}
		}

		br.close();
	}
}