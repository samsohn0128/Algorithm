package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 경비원_B2564 {
	static int W, H, N;
	static Node[] nodes;
	static Node dongeun;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(br.readLine());
		nodes = new Node[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			nodes[i] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		st = new StringTokenizer(br.readLine());
		dongeun = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		int ans = 0;
		for (int i = 0; i < N; i++) {
			switch (dongeun.num) {
			case 1:
				switch (nodes[i].num) {
				case 1:
					ans += Math.abs(dongeun.dist - nodes[i].dist);
					break;
				case 2:
					ans += Math.min(dongeun.dist + nodes[i].dist + H, W - dongeun.dist + H + W - nodes[i].dist);
					break;
				case 3:
					ans += dongeun.dist + nodes[i].dist;
					break;
				case 4:
					ans += W - dongeun.dist + nodes[i].dist;
				}
				break;
			case 2:
				switch (nodes[i].num) {
				case 1:
					ans += Math.min(dongeun.dist + nodes[i].dist + H, W - dongeun.dist + H + W - nodes[i].dist);
					break;
				case 2:
					ans += Math.abs(dongeun.dist - nodes[i].dist);
					break;
				case 3:
					ans += dongeun.dist + H - nodes[i].dist;
					break;
				case 4:
					ans += W - dongeun.dist + H - nodes[i].dist;
					break;
				}
				break;
			case 3:
				switch (nodes[i].num) {
				case 1:
					ans += dongeun.dist + nodes[i].dist;
					break;
				case 2:
					ans += H - dongeun.dist + nodes[i].dist;
					break;
				case 3:
					ans += Math.abs(dongeun.dist - nodes[i].dist);
					break;
				case 4:
					ans += Math.min(dongeun.dist + W + nodes[i].dist, H - dongeun.dist + W + H - nodes[i].dist);
					break;
				}
				break;
			case 4:
				switch (nodes[i].num) {
				case 1:
					ans += dongeun.dist + W - nodes[i].dist;
					break;
				case 2:
					ans += H - dongeun.dist + W - nodes[i].dist;
					break;
				case 3:
					ans += Math.min(dongeun.dist + W + nodes[i].dist, H - dongeun.dist + W + H - nodes[i].dist);
					break;
				case 4:
					ans += Math.abs(dongeun.dist - nodes[i].dist);
					break;
				}
				break;
			}
		}
		System.out.println(ans);
		br.close();
	}

	static class Node {
		int num;
		int dist;

		public Node(int num, int dist) {
			this.num = num;
			this.dist = dist;
		}
	}
}