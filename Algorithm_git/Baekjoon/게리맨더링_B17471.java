package Baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 게리맨더링_B17471 {

	static int N;
	static int[] population = new int[11];
	static int[] src = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
	static int[] tgt = new int[10];
	static boolean[] selected = new boolean[11];
	@SuppressWarnings("unchecked")
	static ArrayList<Integer>[] adjacent = new ArrayList[11];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			population[i] = Integer.parseInt(st.nextToken());
			adjacent[i] = new ArrayList<>();
		}

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken());
			for (int j = 0; j < K; j++) {
				adjacent[i].add(Integer.parseInt(st.nextToken()));
			}
		}

		comb(1, 0);
		System.out.println(ans);

		br.close();
	}

	static int ans = -1;

	static void comb(int srcIdx, int tgtIdx) {
		if (tgtIdx != 0 && tgtIdx != N) {
			boolean chk1 = bfs(tgt[0], tgtIdx, true);
			int idx = 1;
			while (selected[idx])
				idx++;
			boolean chk2 = bfs(idx, N - tgtIdx, false);
			if (chk1 && chk2) {
				int sum1 = 0;
				int sum2 = 0;
				for (int i = 1; i <= N; i++) {
					if (!selected[i])
						sum1 += population[i];
					else
						sum2 += population[i];
				}
				if (ans == -1)
					ans = Math.abs(sum1 - sum2);
				else
					ans = Math.min(ans, Math.abs(sum1 - sum2));
			}
		}
		if (srcIdx == N + 1) {
			return;
		}
		selected[src[srcIdx]] = true;
		tgt[tgtIdx] = src[srcIdx];
		comb(srcIdx + 1, tgtIdx + 1);
		selected[src[srcIdx]] = false;
		comb(srcIdx + 1, tgtIdx);
	}

	static boolean bfs(int n, int size, boolean flag) {
		boolean[] visited = new boolean[11];
		Queue<Integer> q = new LinkedList<>();
		q.offer(n);
		visited[n] = true;
		int cnt = 1;
		while (!q.isEmpty()) {
			int temp = q.poll();
			for (int it : adjacent[temp]) {
				if (flag) {
					if (!visited[it] && selected[it]) {
						visited[it] = true;
						q.offer(it);
						cnt++;
					}
				} else {
					if (!visited[it] && !selected[it]) {
						visited[it] = true;
						q.offer(it);
						cnt++;
					}
				}
			}
		}
		return cnt == size ? true : false;
	}
}