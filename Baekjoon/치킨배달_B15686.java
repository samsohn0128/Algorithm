package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 치킨배달_B15686 {

	static int N, M;
	static ArrayList<Node> house = new ArrayList<>();
	static ArrayList<Node> chicken = new ArrayList<>();
	static Node[] tgt;
	static int ans = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		tgt = new Node[M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken());
				if (num == 1)
					house.add(new Node(i, j));
				else if (num == 2)
					chicken.add(new Node(i, j));
			}
		}
		comb(0, 0);
		System.out.println(ans);

		br.close();
	}

	static void comb(int srcIdx, int tgtIdx) {
		if (tgtIdx == M) {
			int sum = 0;
			for (Node n : house)
				sum += getMinDist(n);
			ans = Math.min(ans, sum);
			return;
		}

		if (srcIdx == chicken.size())
			return;

		comb(srcIdx + 1, tgtIdx);
		tgt[tgtIdx] = chicken.get(srcIdx);
		comb(srcIdx + 1, tgtIdx + 1);
	}

	static int getMinDist(Node n) {
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < M; i++) {
			min = Math.min(min, getDist(n, tgt[i]));
		}
		return min;
	}

	static int getDist(Node n1, Node n2) {
		return Math.abs(n1.r - n2.r) + Math.abs(n1.c - n2.c);
	}

	static class Node {
		int r;
		int c;

		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}