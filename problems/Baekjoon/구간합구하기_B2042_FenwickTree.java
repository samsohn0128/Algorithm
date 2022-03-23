import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2042_구간합구하기_FenwickTree {
	static int N, M, K;
	static long[] numbers = new long[1000001];
	static long[] fenwickTree = new long[1000001];

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		for (int i = 1; i <= N; i++) {
			numbers[i] = Long.parseLong(br.readLine());
		}
		makeFenwickTree();
		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			if (a == 1) {
				int b = Integer.parseInt(st.nextToken());
				long c = Long.parseLong(st.nextToken());
				updateFenwickTree(b, c - numbers[b]);
				numbers[b] = c;
			} else if (a == 2) {
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				System.out.println(sum(c) - sum(b - 1));
			}
		}
	}

	public static void makeFenwickTree() {
		for (int i = 1; i <= N; i++) {
			updateFenwickTree(i, numbers[i]);
		}
	}

	public static void updateFenwickTree(int idx, long diff) {
		while (idx <= N) {
			fenwickTree[idx] += diff;
			idx += (idx & -idx);
		}
	}

	public static long sum(int idx) {
		long ret = 0;
		while (idx > 0) {
			ret += fenwickTree[idx];
			idx -= (idx & -idx);
		}
		return ret;
	}
}
