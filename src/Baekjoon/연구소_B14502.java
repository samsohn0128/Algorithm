package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 연구소_B14502 {

	static int N, M;
	static int[][] grid = new int[9][9];
	static int[][] gridOrigin = new int[9][9];
	static ArrayList<Node> src = new ArrayList<>();
	static Node[] tgt = new Node[3];
	static int ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				gridOrigin[i][j] = Integer.parseInt(st.nextToken());
				if (gridOrigin[i][j] == 0) {
					src.add(new Node(i, j));
				}
			}
		}
		comb(0, 0);
		System.out.println(ans);

		br.close();
	}

	static void comb(int srcIdx, int tgtIdx) {
		if (tgtIdx == 3) {
			copyGrid();
			for (int i = 0; i < 3; i++) {
				grid[tgt[i].y][tgt[i].x] = 1;
			}
			ans = Math.max(ans, simulate());
			return;
		}
		if (srcIdx == src.size()) {
			return;
		}
		tgt[tgtIdx] = src.get(srcIdx);
		comb(srcIdx + 1, tgtIdx + 1);
		comb(srcIdx + 1, tgtIdx);
	}

	static void copyGrid() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				grid[i][j] = gridOrigin[i][j];
			}
		}
	}

	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	static int simulate() {
		Queue<Node> q = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (grid[i][j] == 2) {
					q.offer(new Node(i, j));
				}
			}
		}

		while (!q.isEmpty()) {
			Node temp = q.poll();
			for (int i = 0; i < 4; i++) {
				int ny = temp.y + dy[i];
				int nx = temp.x + dx[i];
				if (ny >= 0 && ny < N && nx >= 0 && nx < M) {
					if (grid[ny][nx] == 0) {
						grid[ny][nx] = 2;
						q.offer(new Node(ny, nx));
					}
				}
			}
		}

		int ret = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (grid[i][j] == 0)
					ret++;
			}
		}
		return ret;
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