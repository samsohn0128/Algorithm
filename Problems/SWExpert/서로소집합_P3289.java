package SWExpert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 서로소집합_P3289 {
	static int T;
	static int N, M;
	static int[] root;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력을 받기위한 BufferedReader
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			root = new int[N + 1];
			makeSet();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int op = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				switch (op) {
				case 0:
					unionSet(a, b);
					break;
				case 1:
					if (findSet(a) == findSet(b))
						sb.append(1);
					else
						sb.append(0);
					break;
				}
			}
			System.out.println("#" + tc + " " + sb.toString());
		}
		br.close();
	}

	static void makeSet() {
		for (int i = 1; i <= N; i++) {
			root[i] = i;
		}
	}

	static int findSet(int n) {
		if (root[n] == n) {
			return n;
		}
		return root[n] = findSet(root[n]);
	}

	static void unionSet(int a, int b) {
		int rootA = findSet(a);
		int rootB = findSet(b);
		root[rootA] = rootB;
	}
}