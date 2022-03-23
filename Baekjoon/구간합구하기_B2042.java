package Baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 구간합구하기_B2042 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		long[] input = new long[N + 1];
		long[] tree = new long[4 * (N + 1)];
		for (int i = 1; i <= N; i++) {
			input[i] = Integer.parseInt(br.readLine());
		}
		init(input, tree, 1, 1, N);

		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());
			if (a == 1) {
				update(tree, 1, 1, N, b, c - input[b]);
				input[b] = c;
			} else if (a == 2) {
				long ret = sum(tree, 1, 1, N, b, (int) c);
				System.out.println(ret);
			}
		}

		br.close();
	}

	static long init(long[] arr, long[] tree, int nodeNum, int start, int end) {
		if (start == end) {
			return tree[nodeNum] = arr[start];
		} else {
			return tree[nodeNum] = init(arr, tree, nodeNum * 2, start, (start + end) / 2)
					+ init(arr, tree, nodeNum * 2 + 1, (start + end) / 2 + 1, end);
		}
	}

	static long sum(long[] tree, int nodeNum, int start, int end, int left, int right) {
		if (left > end || right < start) {
			return 0;
		} else if (left <= start && end <= right) {
			return tree[nodeNum];
		} else {
			return sum(tree, nodeNum * 2, start, (start + end) / 2, left, right)
					+ sum(tree, nodeNum * 2 + 1, (start + end) / 2 + 1, end, left, right);
		}
	}

	static void update(long[] tree, int nodeNum, int start, int end, int index, long diff) {
		if (index < start || index > end) {
			return;
		}

		tree[nodeNum] += diff;
		if (start != end) {
			update(tree, nodeNum * 2, start, (start + end) / 2, index, diff);
			update(tree, nodeNum * 2 + 1, (start + end) / 2 + 1, end, index, diff);
		}
	}
}