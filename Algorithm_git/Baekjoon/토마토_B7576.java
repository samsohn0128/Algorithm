package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 토마토_B7576 {
	static int N, M;
	static int[][] grid;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		grid = new int[N][M];
		Queue<Node> q = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
				if (grid[i][j] == 1)
					q.offer(new Node(i, j));
			}
		}
		int[] dy = { -1, 1, 0, 0 };
		int[] dx = { 0, 0, -1, 1 };
		int ans = 0;
		int size = q.size();
		while (!q.isEmpty()) {
			Node temp = q.poll();
			for (int i = 0; i < 4; i++) {
				int ny = temp.y + dy[i];
				int nx = temp.x + dx[i];
				if (ny >= 0 && ny < N && nx >= 0 && nx < M) {
					if (grid[ny][nx] == 0) {
						q.offer(new Node(ny, nx));
						grid[ny][nx] = 1;
					}
				}
			}
			if (--size == 0) {
				size = q.size();
				ans++;
			}
		}
		boolean flag = true;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (grid[i][j] == 0) {
					flag = false;
					break;
				}
			}
		}
		if (flag)
			System.out.println(ans - 1);
		else
			System.out.println(-1);
		br.close();
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