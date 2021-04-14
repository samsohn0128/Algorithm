package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 최단경로_B1753 {

	static int V, E, K;
	static int[] dist = new int[20001];
	static Node[] nodes = new Node[20001];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());

		for (int i = 1; i <= V; i++) {
			dist[i] = Integer.MAX_VALUE;
			nodes[i] = new Node(i);
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			nodes[u].adj.add(new Integer[] { v, w });
		}

		PriorityQueue<Integer[]> pq = new PriorityQueue<Integer[]>(new Comparator<Integer[]>() {
			@Override
			public int compare(Integer[] o1, Integer[] o2) {
				return o1[0] - o2[0];
			}
		});// 현재깢의 거리로 정렬

		pq.offer(new Integer[] { 0, K }); // [0] : 현재까지의 거리, [1] : 현재 노드 번호
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
		int id;
		ArrayList<Integer[]> adj = new ArrayList<>(); // [0] : 목적지, [1] : 가중치

		Node(int id) {
			this.id = id;
		}
	}
}