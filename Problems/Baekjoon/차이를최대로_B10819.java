package Baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 차이를최대로_B10819 {

	static int N;
	static int[] arr;
	static int[] tgt;
	static boolean[] visited;
	static int ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		tgt = new int[N];
		visited = new boolean[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		perm(0);
		System.out.println(ans);

		br.close();
	}

	static void perm(int idx) {
		if (idx == N) {
			int ret = calculate();
			ans = Math.max(ans, ret);
			return;
		}
		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				tgt[idx] = arr[i];
				perm(idx + 1);
				visited[i] = false;
			}
		}
	}

	static int calculate() {
		int ret = 0;
		for (int i = 0; i < N - 1; i++) {
			ret += Math.abs(tgt[i] - tgt[i + 1]);
		}
		return ret;
	}

}
