package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class 소수의연속합_B1644 {

	static int N;
	static int[] minFactor;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		minFactor = new int[N + 1];

		eratosthenes();
		Queue<Integer> q = new LinkedList<>();
		int sum = 0;
		int ans = 0;
		for (int i = 2; i <= N; i++) {
			if (minFactor[i] == i) {
				q.offer(i);
				sum += i;
				while (sum > N) {
					sum -= q.poll();
				}
				if (sum == N) {
					ans++;
				}
			}
		}

		System.out.println(ans);

		br.close();
	}

	static void eratosthenes() {
		minFactor[0] = -1;
		minFactor[1] = -1;
		for (int i = 2; i <= N; i++)
			minFactor[i] = i;

		for (int i = 2; i * i <= N; i++) {
			if (minFactor[i] == i) {
				for (int j = i * i; j <= N; j += i) {
					if (minFactor[j] == j) {
						minFactor[j] = i;
					}
				}
			}
		}
	}
}