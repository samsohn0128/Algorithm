package Baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 타임머신_B11657 {

	static int N, M;
	static Edge[] edges;
	static long[] dist;
	static int[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		init();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			edges[i] = new Edge(a, b, w);
		}

		if (bellmanFord()) {
			for (int i = 2; i <= N; i++) {
				if (dist[i] == Integer.MAX_VALUE)
					System.out.println(-1);
				else
					System.out.println(dist[i]);
			}
		} else {
			System.out.println(-1);
		}

		br.close();
	}

	static boolean bellmanFord() {
		dist[1] = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < M; j++) {
				if (dist[edges[j].from] != Integer.MAX_VALUE) {
					if (dist[edges[j].to] > dist[edges[j].from] + edges[j].weight) {
						dist[edges[j].to] = dist[edges[j].from] + edges[j].weight;
					}
				}
			}
		}

		for (int i = 0; i < M; i++) {
			if (dist[edges[i].from] == Integer.MAX_VALUE)
				return true;
			else if (dist[edges[i].to] > dist[edges[i].from] + edges[i].weight)
				return false;
		}

		return true;
	}

	static void init() {
		edges = new Edge[M];
		dist = new long[N + 1];
		for (int i = 1; i <= N; i++) {
			dist[i] = Integer.MAX_VALUE;
		}

		visited = new int[N + 1];
	}

	static class Edge {
		int from;
		int to;
		int weight;

		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
	}
}