import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11505_구간곱구하기 {

	static final int INF = 1000000007;
	static int N, M, K;
	static long[] numbers;
	static long[] segmentTree;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		numbers = new long[N + 1];
		int treeHeight = (int) Math.ceil(baseLog(N, 2));
		int treeSize = (1 << (treeHeight + 1));
		segmentTree = new long[treeSize];
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(br.readLine());
		}
		makeSegmentTree(1, 0, N - 1);
		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if (a == 1) {
				updateSegmentTree(1, 0, N - 1, b - 1, c);
				numbers[b - 1] = c;
			} else if (a == 2) {
				System.out.println(getMulti(1, 0, N - 1, b - 1, c - 1));
			}
		}
	}

	static long makeSegmentTree(int nodeNum, int start, int end) {
		if (start == end)
			return segmentTree[nodeNum] = numbers[start];
		int mid = (start + end) / 2;
		long left = makeSegmentTree(nodeNum * 2, start, mid);
		long right = makeSegmentTree(nodeNum * 2 + 1, mid + 1, end);
		return segmentTree[nodeNum] = (left * right) % INF;
	}

	static long updateSegmentTree(int nodeNum, int start, int end, int idx, int val) {
		if (idx < start || end < idx)
			return segmentTree[nodeNum];
		else if (start == end)
			return segmentTree[nodeNum] = val;
		else {
			int mid = (start + end) / 2;
			long left = updateSegmentTree(nodeNum * 2, start, mid, idx, val);
			long right = updateSegmentTree(nodeNum * 2 + 1, mid + 1, end, idx, val);
			return segmentTree[nodeNum] = (left * right) % INF;
		}
	}

	static long getMulti(int nodeNum, int start, int end, int left, int right) {
		if (end < left || right < start)
			return 1;
		else if (left <= start && end <= right)
			return segmentTree[nodeNum];
		else {
			int mid = (start + end) / 2;
			long leftMulti = getMulti(nodeNum * 2, start, mid, left, right);
			long rightMulti = getMulti(nodeNum * 2 + 1, mid + 1, end, left, right);
			return (leftMulti * rightMulti) % INF;
		}
	}

	static double baseLog(int x, int base) {
		return Math.log(x) / Math.log(base);
	}
}