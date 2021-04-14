package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 에라토스테네스의체_B2960 {

	static final int MAX = 1001;
	static int[] minFactor = new int[MAX];
	static int N, K;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		int ans = eratosthenes();

		System.out.println(ans);

		br.close();
	}

	static int eratosthenes() {
		int cnt = 0;

		minFactor[0] = minFactor[1] = -1;
		for (int i = 2; i <= N; i++) {
			minFactor[i] = i;
		}
		
//		for (int i = 2; i * i<= N; i++) {
		for (int i = 2; i<= N; i++) {
			if (minFactor[i] == i) {
				if (++cnt == K)
					return i;
				for (int j = i * i; j <= N; j += i) {
					if (minFactor[j] == j) {
						minFactor[j] = i;
						if (++cnt == K) {
							return j;
						}
					}
				}
			}
		}
		return 0;
	}
}