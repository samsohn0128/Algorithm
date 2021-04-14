package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 단절점_B11266 {

	static int V, E;
	static int[] visited;
	static boolean[] ans;
	static ArrayList<Integer>[] adj;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		adj = new ArrayList[V + 1];
		for (int i = 1; i <= V; i++)
			adj[i] = new ArrayList<>();

		visited = new int[V + 1];
		ans = new boolean[V + 1];

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adj[a].add(b);
			adj[b].add(a);
		}

		for (int i = 1; i <= V; i++) {
			if (visited[i] == 0)
				dfs(i, true);
		}

		StringBuilder sb = new StringBuilder();
		int ansCnt = 0;
		for (int i = 1; i <= V; i++) {
			if (ans[i]) {
				sb.append(i).append(" ");
				ansCnt++;
			}
		}
		System.out.println(ansCnt);
		System.out.println(sb);

		br.close();
	}

	static int order;

	static int dfs(int num, boolean root) {
		int ret = visited[num] = ++order;
		int cnt = 0;

		for (Integer it : adj[num]) {
			if (visited[it] == 0) {
				++cnt;
				int low = dfs(it, false);
				if (!root && low >= visited[num]) {
					ans[num] = true;
				}
				ret = Math.min(ret, low);
			} else {
				ret = Math.min(ret, visited[it]);
			}
		}

		if (root && cnt >= 2) {
			ans[num] = true;
		}
		return ret;
	}

}