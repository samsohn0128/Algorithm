package Baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 연결요소의개수_B11724 {

	static int N, M;
	static int[][] edges;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		edges = new int[N + 1][N + 1];
		visited = new boolean[N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			edges[v1][v2] = 1;
			edges[v2][v1] = 1;
		}

		int ans = 0;
		for (int i = 1; i <= N; i++) {
			if (!visited[i]) {
				ans++;
				dfs(i);
			}
		}
		System.out.println(ans);
		br.close();
	}

	static void dfs(int v) {
		visited[v] = true;
		for (int i = 1; i <= N; i++) {
			if (!visited[i] && edges[v][i] == 1) {
				dfs(i);
			}
		}
	}
}
