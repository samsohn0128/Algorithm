package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 행성터널_B2887 {
	static int N;
	static Node[] nodes;
	static int[] roots;
	static PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> e1.dist - e2.dist);
	static long ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		nodes = new Node[N];
		roots = new int[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			nodes[i] = new Node(i, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()));
			roots[i] = i;
		}
		Arrays.sort(nodes, (n1, n2) -> n1.x - n2.x);
		for (int i = 0; i < N - 1; i++) {
			pq.offer(new Edge(nodes[i], nodes[i + 1]));
		}
		Arrays.sort(nodes, (n1, n2) -> n1.y - n2.y);
		for (int i = 0; i < N - 1; i++) {
			pq.offer(new Edge(nodes[i], nodes[i + 1]));
		}
		Arrays.sort(nodes, (n1, n2) -> n1.z - n2.z);
		for (int i = 0; i < N - 1; i++) {
			pq.offer(new Edge(nodes[i], nodes[i + 1]));
		}

		while (!pq.isEmpty()) {
			Edge temp = pq.poll();
			int rn1 = find(temp.n1.id);
			int rn2 = find(temp.n2.id);
			if (rn1 != rn2) {
				union(rn1, rn2);
				ans += temp.dist;
			}
		}
		System.out.println(ans);
	}

	static int find(int n) {
		if (n == roots[n]) {
			return n;
		}
		return roots[n] = find(roots[n]);
	}

	static void union(int a, int b) {
		int ra = find(a);
		int rb = find(b);

		roots[ra] = rb;
	}

	static class Edge {
		Node n1;
		Node n2;
		int dist;

		public Edge(Node n1, Node n2) {
			this.n1 = n1;
			this.n2 = n2;
			dist = Math.min(Math.abs(n1.x - n2.x), Math.min(Math.abs(n1.y - n2.y), Math.abs(n1.z - n2.z)));
		}
	}

	static class Node {
		int id;
		int x;
		int y;
		int z;
		int dist;

		public Node(int id, int x, int y, int z) {
			this.id = id;
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}
}