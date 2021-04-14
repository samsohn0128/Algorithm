package Baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 키순서_B2458{

	static int N, M;
	static Node[] nodes;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		nodes = new Node[N + 1];
		visited = new boolean[N + 1];
		for (int i = 1; i <= N; i++) {
			nodes[i] = new Node(i);
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			nodes[a].parents.add(b);
			nodes[b].children.add(a);
		}

		int ans = 0;
		for (int i = 1; i <= N; i++) {
			Arrays.fill(visited, false);
			int children = countChildren(nodes[i]);
			int parents = countParents(nodes[i]);
			if (children + parents == N - 1)
				ans++;
		}
		System.out.println(ans);
		br.close();
	}

	static int countChildren(Node n) {
		visited[n.id] = true;
		int ret = 0;
		for (int i = 0; i < n.children.size(); i++) {
			if (!visited[n.children.get(i)]) {
				ret++;
				ret += countChildren(nodes[n.children.get(i)]);
			}
		}
		return ret;
	}

	static int countParents(Node n) {
		visited[n.id] = true;
		int ret = 0;
		for (int i = 0; i < n.parents.size(); i++) {
			if (!visited[n.parents.get(i)]) {
				ret++;
				ret += countParents(nodes[n.parents.get(i)]);
			}
		}
		return ret;
	}

	static class Node {
		int id;
		ArrayList<Integer> parents = new ArrayList<>();
		ArrayList<Integer> children = new ArrayList<>();
		int digree = 0;

		public Node(int id) {
			this.id = id;
		}
	}
}