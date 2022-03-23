package Baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class 세진바이러스_B15783 {

	static int N, M;
	@SuppressWarnings("unchecked")
	static ArrayList<Integer>[] edges = new ArrayList[100001];
	static Stack<Integer> s = new Stack<>();
	static int[] scc = new int[100001];
	static int[] discover = new int[100001];
	static boolean[] visited = new boolean[100001];
	static int[] indegree;

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
			edges[a].add(b);
		}
		for (int i = 0; i < N; i++) {
			if (discover[i] == -1) {
				dfs1(i);
			}
		}

		indegree = new int[sccNum];

		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				dfs2(i);
			}
		}

		int ans = 0;
		for (int i = 0; i < sccNum; i++) {
			if (indegree[i] == 0) {
				ans++;
			}
		}
		System.out.println(ans);
		br.close();
	}

	static int cnt;
	static int sccNum;

	static int dfs1(int n) {
		s.push(n);
		discover[n] = ++cnt;
		int ret = discover[n];
		for (int i = 0; i < edges[n].size(); i++) {
			if (discover[edges[n].get(i)] == -1) {
				ret = Math.min(ret, dfs1(edges[n].get(i)));
			} else if (scc[edges[n].get(i)] == -1) {
				ret = Math.min(ret, discover[edges[n].get(i)]);
			}
		}

		if (ret == discover[n]) {
			while (!s.isEmpty()) {
				int temp = s.pop();
				scc[temp] = sccNum;
				if (ret == discover[temp])
					break;
			}
			sccNum++;
		}
		return ret;
	}

	static void dfs2(int n) {
		visited[n] = true;
		for (int i = 0; i < edges[n].size(); i++) {
			if (!visited[edges[n].get(i)]) {
				if (scc[n] != scc[edges[n].get(i)]) {
					indegree[scc[edges[n].get(i)]]++;
					dfs2(edges[n].get(i));
				}
			} else if (scc[n] != scc[edges[n].get(i)]) {
				indegree[scc[edges[n].get(i)]]++;
			}
		}
	}

	static void init() {
		for (int i = 0; i < N; i++) {
			edges[i] = new ArrayList<>();
			discover[i] = -1;
			scc[i] = -1;
		}
	}
}