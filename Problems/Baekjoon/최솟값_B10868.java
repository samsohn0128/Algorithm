import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10868_최솟값 {

	static final int INF = 1000000000;
	static int N, M;
	static int[] numbers;
	static int[] segmentTreeMin;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		numbers = new int[N];
		int treeHeight = (int) Math.ceil(baseLog(N, 2));
		int treeSize = (1 << (treeHeight + 1));
		segmentTreeMin = new int[treeSize];
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(br.readLine());
		}
		makeSegmentTreeMin(1, 0, N - 1);
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			System.out.println(getMin(1, 0, N - 1, a - 1, b - 1));
		}
	}

	static int makeSegmentTreeMin(int nodeNum, int start, int end) {
		if (start == end)
			return segmentTreeMin[nodeNum] = numbers[start];
		int mid = (start + end) / 2;
		int left = makeSegmentTreeMin(nodeNum * 2, start, mid);
		int right = makeSegmentTreeMin(nodeNum * 2 + 1, mid + 1, end);
		return segmentTreeMin[nodeNum] = Math.min(left, right);
	}

	static int getMin(int nodeNum, int start, int end, int left, int right) {
		if (end < left || right < start) {
			return INF;
		} else if (left <= start && end <= right) {
			return segmentTreeMin[nodeNum];
		} else {
			int mid = (start + end) / 2;
			int leftMin = getMin(nodeNum * 2, start, mid, left, right);
			int rightMin = getMin(nodeNum * 2 + 1, mid + 1, end, left, right);
			return Math.min(leftMin, rightMin);
		}
	}

	static double baseLog(int x, int base) {
		return Math.log(x) / Math.log(base);
	}
}