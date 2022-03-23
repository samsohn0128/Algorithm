package Baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 네트워크연결_B1922 {

	static int N, M;
	static int[][] adj;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		adj = new int[N + 1][N + 1];
		visited = new boolean[N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			adj[a][b] = c;
			adj[b][a] = c;
		}

		int ans = 0;
		PriorityQueue<Adjacent> pq = new PriorityQueue<>((a1, a2) -> a1.c - a2.c);
		for (int i = 1; i <= N; i++) {
			if (adj[1][i] > 0)
				pq.offer(new Adjacent(i, adj[1][i]));
		}
		visited[1] = true;
		while (!pq.isEmpty()) {
			Adjacent temp = pq.poll();
			if (!visited[temp.b]) {
				visited[temp.b] = true;
				ans += temp.c;

				for (int i = 1; i <= N; i++) {
					if (!visited[i] && adj[temp.b][i] > 0) {
						pq.offer(new Adjacent(i, adj[temp.b][i]));
					}
				}
			}
		}
		System.out.println(ans);
		br.close();
	}

	static class Adjacent {
		int b;
		int c;

		public Adjacent(int b, int c) {
			this.b = b;
			this.c = c;
		}
	}

}