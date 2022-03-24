package SWExpert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 프로세서연결하기_P1767 {
	static int N;
	static int[][] grid = new int[13][13];
	static ArrayList<Node> list = new ArrayList<>();
	static int maxCnt;
	static int minAns;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			list.clear();
			maxCnt = 0;
			minAns = Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine());
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					grid[i][j] = Integer.parseInt(st.nextToken());
					if (grid[i][j] == 1 && i != 0 && i != N - 1 && j != 0 && j != N - 1) {
						list.add(new Node(i, j));
					}
				}
			}
			dfs(0, 0, 0);
			System.out.println("#" + tc + " " + minAns);
		}
	}

	static void dfs(int idx, int len, int cnt) {
		if (idx == list.size()) {
			if (maxCnt < cnt) {
				maxCnt = cnt;
				minAns = len;
			} else if (maxCnt == cnt) {
				minAns = Math.min(minAns, len);
			}
			return;
		}
		for (int i = 0; i < 4; i++) {
			if (chk(list.get(idx), i)) {
				int ret = updateGrid(list.get(idx), i, 2);
				dfs(idx + 1, len + ret, cnt + 1);
				updateGrid(list.get(idx), i, 0);
			}
		}
		dfs(idx + 1, len, cnt);
	}

	// 상하좌우
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	static boolean chk(Node n, int dir) {
		for (int i = 1; i < N; i++) {
			int ny = n.y + dy[dir] * i;
			int nx = n.x + dx[dir] * i;
			if (ny >= 0 && ny < N && nx >= 0 && nx < N) {
				if (grid[ny][nx] != 0) {
					return false;
				}
			} else {
				break;
			}
		}
		return true;
	}

	static int updateGrid(Node n, int dir, int num) {
		int cnt = 0;
		for (int i = 1; i < N; i++) {
			int ny = n.y + dy[dir] * i;
			int nx = n.x + dx[dir] * i;
			if (ny >= 0 && ny < N && nx >= 0 && nx < N) {
				grid[ny][nx] = num;
				cnt++;
			} else {
				break;
			}
		}
		return cnt;
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