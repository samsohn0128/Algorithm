import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2042_구간합구하기_SegmentTree {

	static int N, M, K;
	static long[] numbers = new long[1000001];
	static long[] segmentTree;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		int segmentTreeHeight = (int) Math.ceil(baseLog(N, 2));
		int segmentTreeSize = (1 << (segmentTreeHeight + 1));
		segmentTree = new long[segmentTreeSize];

		for (int i = 0; i < N; i++) {
			numbers[i] = Long.parseLong(br.readLine());
		}
		makeSegmentTree(1, 0, N - 1);
		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			if (a == 1) {
				int b = Integer.parseInt(st.nextToken());
				long c = Long.parseLong(st.nextToken());
				update(1, 0, N - 1, b - 1, c - numbers[b - 1]);
				numbers[b - 1] = c;
			} else if (a == 2) {
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				System.out.println(sum(1, 0, N - 1, b - 1, c - 1));
			}
		}
	}

	public static long makeSegmentTree(int nodeNum, int start, int end) {
		if (start == end) {
			return segmentTree[nodeNum] = numbers[start];
		}

		int mid = (start + end) / 2;
		long left = makeSegmentTree(nodeNum * 2, start, mid);
		long right = makeSegmentTree(nodeNum * 2 + 1, mid + 1, end);
		return segmentTree[nodeNum] = left + right;
	}

	public static long sum(int nodeNum, int start, int end, int left, int right) {
		if (left > end || right < start)
			return 0;
		if (left <= start && end <= right)
			return segmentTree[nodeNum];
		int mid = (start + end) / 2;
		long leftSum = sum(nodeNum * 2, start, mid, left, right);
		long rightSum = sum(nodeNum * 2 + 1, mid + 1, end, left, right);
		return leftSum + rightSum;
	}

	public static void update(int nodeNum, int start, int end, int idx, long diff) {
		if (idx < start || idx > end)
			return;
		segmentTree[nodeNum] += diff;

		if (start != end) {
			int mid = (start + end) / 2;
			update(nodeNum * 2, start, mid, idx, diff);
			update(nodeNum * 2 + 1, mid + 1, end, idx, diff);
		}
	}

	public static double baseLog(int x, int base) {
		return Math.log(x) / Math.log(base);
	}
}