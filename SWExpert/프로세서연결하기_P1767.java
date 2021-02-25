package SWExpert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 프로세서연결하기_P1767 {
	static int T;
	static int N;
	static boolean[][] visited;
	static List<Integer[]> cores = new ArrayList<>();
	static int ans;
	static int ansCnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력을 받기위한 BufferedReader
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			ans = Integer.MAX_VALUE;
			ansCnt = 0;
			visited = new boolean[N][N];
			cores.clear();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int num = Integer.parseInt(st.nextToken());
					if (num == 1) {
						cores.add(new Integer[] { i, j });
						visited[i][j] = true;
					}
				}
			}
			dfs(0, 0, 0);
			System.out.println("#" + tc + " " + ans);
		}

		br.close();
	}

	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	static void dfs(int index, int length, int cnt) {
		if (index == cores.size()) {
			if (ansCnt < cnt) {
				ans = length;
				ansCnt = cnt;
			} else if (ansCnt == cnt)
				ans = Math.min(ans, length);
			return;
		}

		if (chkVisited(cores.get(index)[0], cores.get(index)[1], 0)) {
			updateVisited(cores.get(index)[0], cores.get(index)[1], 0, true);
			dfs(index + 1, length + cores.get(index)[0], cnt + 1);
			updateVisited(cores.get(index)[0], cores.get(index)[1], 0, false);
		}
		if (chkVisited(cores.get(index)[0], cores.get(index)[1], 1)) {
			updateVisited(cores.get(index)[0], cores.get(index)[1], 1, true);
			dfs(index + 1, length + N - 1 - cores.get(index)[0], cnt + 1);
			updateVisited(cores.get(index)[0], cores.get(index)[1], 1, false);
		}
		if (chkVisited(cores.get(index)[0], cores.get(index)[1], 2)) {
			updateVisited(cores.get(index)[0], cores.get(index)[1], 2, true);
			dfs(index + 1, length + cores.get(index)[1], cnt + 1);
			updateVisited(cores.get(index)[0], cores.get(index)[1], 2, false);
		}
		if (chkVisited(cores.get(index)[0], cores.get(index)[1], 3)) {
			updateVisited(cores.get(index)[0], cores.get(index)[1], 3, true);
			dfs(index + 1, length + N - 1 - cores.get(index)[1], cnt + 1);
			updateVisited(cores.get(index)[0], cores.get(index)[1], 3, false);
		}
		dfs(index + 1, length, cnt);
	}

	static boolean chkVisited(int y, int x, int dir) {
		int i = 1;
		while (true) {
			int ny = y + dy[dir] * i;
			int nx = x + dx[dir] * i;
			if (ny == N || ny == -1 || nx == N || nx == -1)
				break;

			if (visited[ny][nx])
				return false;
			++i;
		}
		return true;
	}

	static void updateVisited(int y, int x, int dir, boolean flag) {
		int i = 1;
		while (true) {
			int ny = y + dy[dir] * i;
			int nx = x + dx[dir] * i;
			if (ny == N || ny == -1 || nx == N || nx == -1)
				break;

			visited[ny][nx] = flag;
			++i;
		}
	}
}