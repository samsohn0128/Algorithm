package Baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 게임개발_B1516{

	static int N;
	static Node[] nodes;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		nodes = new Node[N + 1];
		visited = new boolean[N + 1];
		for (int i = 1; i <= N; i++)
			nodes[i] = new Node(i);

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int cnt = 0;
			while (true) {
				int temp = Integer.parseInt(st.nextToken());
				if (temp == -1)
					break;

				if (cnt++ == 0) {
					nodes[i].time += temp;
				} else {
					nodes[i].requires.add(temp);
				}
			}
		}

		int cnt = 0;
		while (cnt < N) {
			for (int i = 1; i <= N; i++) {
				if (!visited[i]) {
					if (chk(nodes[i])) {
						cnt++;
						visited[i] = true;
						nodes[i].time += getTime(nodes[i]);
					}
				}
			}
		}
		for (int i = 1; i <= N; i++) {
			System.out.println(nodes[i].time);
		}

		br.close();
	}

	static int getTime(Node n) {
		int ret = 0;
		for (int i = 0; i < n.requires.size(); i++) {
			ret = Math.max(ret, nodes[n.requires.get(i)].time);
		}
		return ret;
	}

	static boolean chk(Node n) {
		for (int i = 0; i < n.requires.size(); i++) {
			if (!visited[n.requires.get(i)])
				return false;
		}
		return true;
	}

	static class Node {
		int id;
		int time;
		ArrayList<Integer> requires = new ArrayList<>();

		public Node(int id) {
			this.id = id;
		}
	}
}