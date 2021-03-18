package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class DFS와BFS_B1260 {

	static int N, M, V;
	static boolean[][] adj;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());

		adj = new boolean[N + 1][N + 1];
		visited = new boolean[N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			adj[n1][n2] = true;
			adj[n2][n1] = true;
		}
		dfs(V);
		System.out.println();
		updateVisited(false);
		bfs(V);

		br.close();
	}

	static void dfs(int n) {
		visited[n] = true;
		System.out.print(n + " ");
		for (int i = 1; i <= N; i++) {
			if (adj[n][i] && !visited[i]) {
				dfs(i);
			}
		}
	}

	static void bfs(int n) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(n);
		visited[n] = true;
		while (!q.isEmpty()) {
			int temp = q.poll();
			System.out.print(temp + " ");
			for (int i = 1; i <= N; i++) {
				if (adj[temp][i] && !visited[i]) {
					visited[i] = true;
					q.offer(i);
				}
			}
		}
	}

	static void updateVisited(boolean flag) {
		for (int i = 1; i <= N; i++) {
			visited[i] = flag;
		}
	}
}