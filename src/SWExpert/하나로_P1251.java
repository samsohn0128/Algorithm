package SWExpert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 하나로_P1251 {
	static int T;
	static int N;
	static int[] X, Y;
	static double E;
	static PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
		@Override
		public int compare(Node n1, Node n2) {
			double distDiff = n1.dist - n2.dist;
			if (distDiff < 0) {
				return -1;
			} else if (distDiff > 0) {
				return 1;
			} else {
				return n1.id - n2.id;
			}
		}
	}); // 0 : distance, 1 : index
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			X = new int[N];
			Y = new int[N];
			visited = new boolean[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				X[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				Y[i] = Integer.parseInt(st.nextToken());
			}
			E = Double.parseDouble(br.readLine());

			long ans = Math.round(E * prim());
			System.out.println("#" + tc + " " + ans);
		}
	}

	static double prim() {
		double ret = 0;
		pq.offer(new Node(0, 0));

		while (!pq.isEmpty()) {
			Node temp = pq.poll();
			if (visited[temp.id])
				continue;

			visited[temp.id] = true;
			ret += temp.dist * temp.dist;
			for (int i = 0; i < N; i++) {
				if (!visited[i]) {
					double dist = getDist(X[temp.id], Y[temp.id], X[i], Y[i]);
					if (!Double.isNaN(dist)) {
						pq.offer(new Node(dist, i));
					}
				}
			}
		}
		return ret;
	}

	static double getDist(int x1, int y1, int x2, int y2) {
		long tempX = x2 - x1;
		long tempY = y2 - y1;
		long temp1 = tempX * tempX;
		long temp2 = tempY * tempY;
		long dist = temp1 + temp2;
		return Math.sqrt(dist);
	}

	static class Node {
		double dist;
		int id;

		public Node(double dist, int id) {
			this.dist = dist;
			this.id = id;
		}
	}
}