package SWExpert;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 최적경로_P1247 {
	static int T;
	static int N;
	static Node company, house;
	static Node[] customers;
	static Node[] tgt;
	static boolean[] visited;
	static int ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력을 받기위한 BufferedReader
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			init();

			st = new StringTokenizer(br.readLine());
			company = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			house = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			for (int i = 0; i < N; i++) {
				customers[i] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			perm(0, 0);
			System.out.println("#" + tc + " " + ans);
		}

		br.close();
	}

	static void perm(int tgtIdx, int dist) {
		if (dist >= ans)
			return;
		if (tgtIdx == N) {
			ans = Math.min(ans, dist + getDist(tgt[tgtIdx - 1], house));
			return;
		}

		for (int i = 0; i < N; i++) {
			if (visited[i])
				continue;

			tgt[tgtIdx] = customers[i];
			visited[i] = true;
			if (tgtIdx == 0)
				perm(tgtIdx + 1, dist + getDist(company, tgt[tgtIdx]));
			else
				perm(tgtIdx + 1, dist + getDist(tgt[tgtIdx - 1], tgt[tgtIdx]));
			visited[i] = false;
		}
	}

	static int getDist(Node n1, Node n2) {
		return Math.abs(n1.y - n2.y) + Math.abs(n1.x - n2.x);
	}

	static void init() {
		customers = new Node[N];
		tgt = new Node[N];
		visited = new boolean[N];
		ans = Integer.MAX_VALUE;
	}

	static class Node {
		int y;
		int x;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}