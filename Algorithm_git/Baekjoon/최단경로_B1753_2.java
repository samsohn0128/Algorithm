import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1753_최단경로 {

	static int V, E, K;
	static Node[] nodes;
	static int[] dist;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());
		nodes = new Node[V + 1];
		dist = new int[V + 1];
		for (int i = 1; i <= V; i++) {
			nodes[i] = new Node(i);
			dist[i] = Integer.MAX_VALUE;
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			nodes[u].adj.add(new Integer[] { v, w });
		}

		PriorityQueue<Integer[]> pq = new PriorityQueue<>((i1, i2) -> i1[0] - i2[0]);
		pq.offer(new Integer[] { 0, K }); // Integer[0] = 현재까지의 거리, Integer[1] = 다음 노드
		dist[K] = 0;
		while (!pq.isEmpty()) {
			Integer[] temp = pq.poll();
			if (dist[temp[1]] < temp[0])
				continue;
			for (Integer[] edge : nodes[temp[1]].adj) {
				if (dist[edge[0]] > temp[0] + edge[1]) {
					dist[edge[0]] = temp[0] + edge[1];
					pq.offer(new Integer[] { dist[edge[0]], edge[0] });
				}
			}

		}

		for (int i = 1; i <= V; i++) {
			if (dist[i] == Integer.MAX_VALUE)
				System.out.println("INF");
			else
				System.out.println(dist[i]);
		}
		br.close();
	}

	static class Node {
		int n;
		List<Integer[]> adj = new ArrayList<>();

		Node(int n) {
			this.n = n;
		}
	}

}