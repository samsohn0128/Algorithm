package Baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 도로네트워크_B3176 {

	static int N, K;
	static ArrayList<Edge>[] edges;
	static int[] depths;
	static boolean[] visited;
	static int[][] parents, distMax, distMin;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		edges = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			edges[i] = new ArrayList<>();
		}

		depths = new int[N + 1];
		parents = new int[N + 1][17];
		distMax = new int[N + 1][17];
		distMin = new int[N + 1][17];

		visited = new boolean[N + 1];

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			edges[a].add(new Edge(b, w));
			edges[b].add(new Edge(a, w));
		}

		dfs(1, 0);
		setUp();

		K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			Edge ans = lca(a, b);
			System.out.println(ans.to + " " + ans.weight);
		}

		br.close();
	}

	static Edge lca(int a, int b) {
		int x = a;
		int y = b;
		if (depths[a] > depths[b]) {
			y = a;
			x = b;
		}

		int ansMin = 1000001;
		int ansMax = 0;

		for (int i = 16; i >= 0; i--) {
			if (depths[y] - depths[x] >= 1 << i) {
				ansMax = Math.max(ansMax, distMax[y][i]);
				ansMin = Math.min(ansMin, distMin[y][i]);
				y = parents[y][i];
			}
		}

		if (x == y) {
			return new Edge(ansMin, ansMax);
		}

		for (int i = 16; i >= 0; i--) {
			if (parents[y][i] != parents[x][i]) {
				ansMax = Math.max(ansMax, Math.max(distMax[x][i], distMax[y][i]));
				ansMin = Math.min(ansMin, Math.min(distMin[x][i], distMin[y][i]));
				x = parents[x][i];
				y = parents[y][i];
			}
		}
		ansMax = Math.max(ansMax, Math.max(distMax[x][0], distMax[y][0]));
		ansMin = Math.min(ansMin, Math.min(distMin[x][0], distMin[y][0]));
		
		return new Edge(ansMin, ansMax);
	}

	static void setUp() {
		for (int j = 1; j < 17; j++) {
			for (int i = 1; i <= N; i++) {
				parents[i][j] = parents[parents[i][j - 1]][j - 1];
				distMax[i][j] = Math.max(distMax[i][j - 1], distMax[parents[i][j - 1]][j - 1]);
				distMin[i][j] = Math.min(distMin[i][j - 1], distMin[parents[i][j - 1]][j - 1]);
			}
		}
	}

	static void dfs(int num, int depth) {
		visited[num] = true;
		depths[num] = depth;
		for (Edge edge : edges[num]) {
			if (!visited[edge.to]) {
				parents[edge.to][0] = num;
				distMax[edge.to][0] = edge.weight;
				distMin[edge.to][0] = edge.weight;
				dfs(edge.to, depth + 1);
			}
		}
	}

	static class Edge {
		int to;
		int weight;

		public Edge(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
	}
}