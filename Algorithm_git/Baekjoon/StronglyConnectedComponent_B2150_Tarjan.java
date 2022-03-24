package Baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;

public class StronglyConnectedComponent_B2150_Tarjan {

	static int V, E;
	@SuppressWarnings("unchecked")
	static ArrayList<Integer>[] edges = new ArrayList[10001];
	static ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
	static Stack<Integer> s = new Stack<>();
	static boolean[] visited = new boolean[10001];
	static boolean[] scc = new boolean[10001];
	static int[] discover = new int[10001];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		for (int i = 1; i <= V; i++)
			edges[i] = new ArrayList<>();

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			edges[a].add(b);
		}

		for (int i = 1; i <= V; i++)
			Collections.sort(edges[i]);

		for (int i = 1; i <= V; i++) {
			if (!visited[i]) {
				dfs(i);
			}
		}
		
		for (ArrayList<Integer> list : ans)
			Collections.sort(list);
		Collections.sort(ans, (l1, l2) -> l1.get(0) - l2.get(0));
		

		System.out.println(ans.size());
		for (ArrayList<Integer> list : ans) {
			for (Integer i : list) {
				System.out.print(i + " ");
			}
			System.out.println(-1);
		}

		br.close();
	}

	static int cnt = 0;

	static int dfs(int n) {
		visited[n] = true;
		s.push(n);
		discover[n] = ++cnt;
		int ret = discover[n];
		for (int i = 0; i < edges[n].size(); i++) {
			if (!visited[edges[n].get(i)]) {
				ret = Math.min(ret, dfs(edges[n].get(i)));
			} else if (!scc[edges[n].get(i)]) {
				ret = Math.min(ret, discover[edges[n].get(i)]);
			}
		}
		if (discover[n] == ret) {
			ArrayList<Integer> push = new ArrayList<>();
			while (!s.isEmpty()) {
				int temp = s.pop();
				push.add(temp);
				scc[temp] = true;
				if (discover[temp] == ret)
					break;
			}
			ans.add(push);
		}
		return ret;
	}
}