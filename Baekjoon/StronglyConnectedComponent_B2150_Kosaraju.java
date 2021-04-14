package Baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;

public class StronglyConnectedComponent_B2150_Kosaraju {

	static int V, E;
	@SuppressWarnings("unchecked")
	static ArrayList<Integer>[][] edges = new ArrayList[2][10001];
	static boolean visited[][] = new boolean[2][10001];
	static Stack<Integer> s = new Stack<>();
	static ArrayList<ArrayList<Integer>> ans = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		for (int j = 0; j < 2; j++)
			for (int i = 1; i <= V; i++)
				edges[j][i] = new ArrayList<>();

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			edges[0][a].add(b);
			edges[1][b].add(a);
		}

		for (int i = 1; i <= V; i++) {
			if (!visited[0][i]) {
				dfs1(i);
			}
		}

		while (!s.isEmpty()) {
			int temp = s.pop();
			if (!visited[1][temp]) {
				ArrayList<Integer> push = new ArrayList<>();
				dfs2(temp, push);
				ans.add(push);
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

	static void dfs1(int n) {
		visited[0][n] = true;
		for (int i = 0; i < edges[0][n].size(); i++) {
			if (!visited[0][edges[0][n].get(i)]) {
				dfs1(edges[0][n].get(i));
			}
		}
		s.push(n);
	}

	static void dfs2(int n, ArrayList<Integer> list) {
		visited[1][n] = true;
		for (int i = 0; i < edges[1][n].size(); i++) {
			if (!visited[1][edges[1][n].get(i)]) {
				dfs2(edges[1][n].get(i), list);
			}
		}
		list.add(n);
	}
}