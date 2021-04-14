package Baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class 두번째로작은스패닝트리_B1626 {

	static int V, E;
	static ArrayList<Edge> edges = new ArrayList<>();

	static int[] root;
	static int[] parents;
	static int[] dist;
	static int[] depths;
	static boolean[] visited;
	static ArrayList<Edge>[] adj;
	static int ans, ans2;
	static int dfsCnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		init();

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			edges.add(new Edge(a, b, d));
		}

		kruskal();
		dfs(1, 0);
		boolean hasAns = true;
		if (dfsCnt < V) {
			hasAns = false;
		}

		if (hasAns) {
			for (Edge edge : edges) {
				if (!edge.used) {
					Pair dMax = lca(edge.from, edge.to);
					int subDist = dMax.first;
					
					if (dMax.first == edge.dist)
						subDist = dMax.second;

					if (subDist == -1)
						continue;

					if (ans == 0)
						ans = ans2 - subDist + edge.dist;
					else
						ans = Math.min(ans, ans2 - subDist + edge.dist);
				}
			}
			if (ans == 0)
				hasAns = false;
		}
		if (hasAns) {
			System.out.println(ans);
		} else {
			System.out.println(-1);
		}
		br.close();
	}

	static void kruskal() {
		Collections.sort(edges, (e1, e2) -> e1.dist - e2.dist);
		for (Edge edge : edges) {
			int r1 = find(edge.from);
			int r2 = find(edge.to);

			if (r1 != r2) {
				adj[edge.to].add(edge);
				adj[edge.from].add(edge);
				union(r1, r2);
				ans2 += edge.dist;
				edge.used = true;
			}
		}
	}

	static Pair lca(int a, int b) {
		int x = a;
		int y = b;
		if (depths[a] > depths[b]) {
			y = a;
			x = b;
		}

		int rMax = -1;
		int secRMax = -1;

		while (depths[y] > depths[x]) {
			if (rMax < dist[y]) {
				secRMax = rMax;
				rMax = dist[y];
			} else if (rMax > dist[y]) {
				secRMax = Math.max(secRMax, dist[y]);
			}
			y = parents[y];
		}

		if (x == y)
			return new Pair(rMax, secRMax);

		while (parents[y] != parents[x]) {
			int temp = Math.max(dist[y], dist[x]);
			if (rMax < temp) {
				secRMax = rMax;
				rMax = temp;
			} else if (rMax > temp) {
				secRMax = Math.max(secRMax, temp);
			}
			y = parents[y];
			x = parents[x];
		}

		int temp = Math.max(dist[y], dist[x]);
		if (rMax < temp) {
			secRMax = rMax;
			rMax = temp;
		} else if (rMax > temp) {
			secRMax = Math.max(secRMax, temp);
		}
		y = parents[y];
		x = parents[x];
		return new Pair(rMax, secRMax);
	}

	static void dfs(int n, int depth) {
		dfsCnt++;
		visited[n] = true;
		depths[n] = depth;
		for (Edge edge : adj[n]) {
			if (edge.from == n && !visited[edge.to]) {
				parents[edge.to] = n;
				dist[edge.to] = edge.dist;
				dfs(edge.to, depth + 1);
			} else if (edge.to == n && !visited[edge.from]) {
				parents[edge.from] = n;
				dist[edge.from] = edge.dist;
				dfs(edge.from, depth + 1);
			}
		}
	}

	@SuppressWarnings("unchecked")
	static void init() {
		root = new int[V + 1];
		parents = new int[V + 1];
		dist = new int[V + 1];
		depths = new int[V + 1];
		visited = new boolean[V + 1];
		adj = new ArrayList[V + 1];
		for (int i = 1; i <= V; i++) {
			root[i] = i;
			adj[i] = new ArrayList<>();
		}
	}

	static void union(int a, int b) {
		if (a != b) {
			root[a] = b;
		}
	}

	static int find(int n) {
		if (root[n] == n)
			return n;
		return root[n] = find(root[n]);
	}

	static class Node {
		int id;
		int parent;
		ArrayList<Integer> children = new ArrayList<>();
	}

	static class Edge {
		int from;
		int to;
		int dist;
		boolean used;

		public Edge(int from, int to, int dist) {
			this.from = from;
			this.to = to;
			this.dist = dist;
		}
	}

	static class Pair {
		int first;
		int second;

		public Pair(int first, int second) {
			this.first = first;
			this.second = second;
		}
	}
}