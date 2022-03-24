import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10999_구간합구하기2 {

	static int N, M, K;
	static long[] numbers;
	static long[] segTree;
	static long[] lazy;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		numbers = new long[N + 1];
		int segTreeHeight = (int) Math.ceil(baseLog(N, 2));
		int segTreeSize = 1 << (segTreeHeight + 1);
		segTree = new long[segTreeSize];
		lazy = new long[segTreeSize];
		for (int i = 0; i < N; i++) {
			numbers[i] = Long.parseLong(br.readLine());
		}
		makeSeg(1, 0, N - 1);
		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			if (a == 1) {
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				long d = Long.parseLong(st.nextToken());
				update(1, 0, N - 1, b - 1, c - 1, d);
			} else if (a == 2) {
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				System.out.println(sum(1, 0, N - 1, b - 1, c - 1));
			}
		}
	}

	static long makeSeg(int nodeNum, int start, int end) {
		if (start == end)
			return segTree[nodeNum] = numbers[start];
		int mid = (start + end) / 2;
		long left = makeSeg(nodeNum * 2, start, mid);
		long right = makeSeg(nodeNum * 2 + 1, mid + 1, end);
		return segTree[nodeNum] = left + right;
	}

	static void lazyUpdate(int nodeNum, int start, int end) {
		if (lazy[nodeNum] != 0) {
			segTree[nodeNum] += (end - start + 1) * lazy[nodeNum];
			if (start != end) {
				lazy[nodeNum * 2] += lazy[nodeNum];
				lazy[nodeNum * 2 + 1] += lazy[nodeNum];
			}
			lazy[nodeNum] = 0;
		}
	}

	static void update(int nodeNum, int start, int end, int left, int right, long diff) {
		lazyUpdate(nodeNum, start, end);
		if (end < left || right < start)
			return;
		else if (left <= start && end <= right) {
			segTree[nodeNum] += (end - start + 1) * diff;
			if (start != end) {
				lazy[nodeNum * 2] += diff;
				lazy[nodeNum * 2 + 1] += diff;
			}
			return;
		} else {
			int mid = (start + end) / 2;
			update(nodeNum * 2, start, mid, left, right, diff);
			update(nodeNum * 2 + 1, mid + 1, end, left, right, diff);
			segTree[nodeNum] = segTree[nodeNum * 2] + segTree[nodeNum * 2 + 1];
		}
	}

	static long sum(int nodeNum, int start, int end, int left, int right) {
		lazyUpdate(nodeNum, start, end);
		if (end < left || right < start)
			return 0;
		else if (left <= start && end <= right)
			return segTree[nodeNum];
		int mid = (start + end) / 2;
		long leftSum = sum(nodeNum * 2, start, mid, left, right);
		long rightSum = sum(nodeNum * 2 + 1, mid + 1, end, left, right);
		return leftSum + rightSum;
	}

	static double baseLog(int x, int base) {
		return Math.log(x) / Math.log(base);
	}
}