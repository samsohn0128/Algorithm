package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 게리맨더링_B17471_Bitmask {

	static int N;
	static int[] population = new int[11];
	static boolean[][] adj = new boolean[11][11];
	static int ans = -1;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			population[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			for (int j = 0; j < n; j++) {
				int num = Integer.parseInt(st.nextToken());
				adj[i][num] = true;
				adj[num][i] = true;
			}
		}
		comb(1, 0);
		System.out.println(ans);

		br.close();
	}

	static void comb(int num, int mask) {
		if (num == N + 1) {
			if (chk(mask)) {
				int popA = 0, popB = 0;
				for (int i = 1; i <= N; i++) {
					if ((mask & 1 << i) != 0) {
						popA += population[i];
					} else {
						popB += population[i];
					}
				}
				ans = ans == -1 ? Math.abs(popA - popB) : Math.min(ans, Math.abs(popA - popB));
			}
			return;
		}
		comb(num + 1, mask | 1 << num);
		comb(num + 1, mask);
	}

	static boolean chk(int mask) {
		int startA = 1, startB = 1;
		while ((mask & 1 << startA) == 0 && startA <= N) {
			startA++;
		}
		while ((mask & 1 << startB) != 0 && startB <= N) {
			startB++;
		}
		if (startA == N + 1 || startB == N + 1) {
			return false;
		}

		int visited = 0;
		Queue<Integer> q = new LinkedList<>();
		q.offer(startA);
		visited |= 1 << startA;
		while (!q.isEmpty()) {
			int temp = q.poll();
			for (int i = 1; i <= N; i++) {
				if (adj[temp][i] && (mask & 1 << i) != 0 && (visited & 1 << i) == 0) {
					q.offer(i);
					visited |= 1 << i;
				}
			}
		}
		for (int i = 1; i <= N; i++) {
			if ((mask & 1 << i) != 0 && (visited & 1 << i) == 0) {
				return false;
			}
		}

		visited = 0;
		q.offer(startB);
		visited |= 1 << startB;
		while (!q.isEmpty()) {
			int temp = q.poll();
			for (int i = 1; i <= N; i++) {
				if (adj[temp][i] && (mask & 1 << i) == 0 && (visited & 1 << i) == 0) {
					q.offer(i);
					visited |= 1 << i;
				}
			}
		}
		for (int i = 1; i <= N; i++) {
			if ((mask & 1 << i) == 0 && (visited & 1 << i) == 0) {
				return false;
			}
		}

		return true;
	}
}