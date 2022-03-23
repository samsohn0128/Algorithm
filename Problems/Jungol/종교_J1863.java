package Jungol;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 종교_J1863 {
	static int N, M;

	static int[] root = new int[50001];
	static int[] children = new int[50001];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		makeSet();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			unionSet(a, b);
		}

		int ans = 0;
		for (int i = 1; i <= N; i++) {
			if (root[i] == i)
				ans++;
		}
		System.out.println(ans);
	}

	static void makeSet() {
		for (int i = 1; i <= N; i++)
			root[i] = i;
	}

	static int findSet(int n) {
		if (root[n] == n)
			return n;

		return root[n] = findSet(root[n]);
	}

	static void unionSet(int a, int b) {
		int rootA = findSet(a);
		int rootB = findSet(b);

		if (children[rootA] > children[rootB]) {
			root[rootB] = rootA;
		} else {
			root[rootA] = rootB;
			if (children[rootA] == children[rootB]) {
				children[rootB]++;
			}
		}
	}
}