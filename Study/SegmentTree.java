package Study;

import java.util.ArrayList;

public class SegmentTree {
	static long init(ArrayList<Long> input, ArrayList<Long> tree, int nodeNum, int start, int end) {
		if (start == end) {
			tree.set(nodeNum, input.get(start));
			return tree.get(nodeNum);
		} else {
			tree.set(nodeNum, init(input, tree, nodeNum * 2, start, (start + end) / 2)
					+ init(input, tree, nodeNum * 2 + 1, (start + end) / 2 + 1, end));
			return tree.get(nodeNum);
		}

	}

	static long sum(ArrayList<Long> tree, int nodeNum, int start, int end, int left, int right) {
		if (left > end || right < start) {
			return 0;
		}
		if (left <= start && end <= right) {
			return tree.get(nodeNum);
		}

		return sum(tree, nodeNum * 2, start, (start + end) / 2, left, right)
				+ sum(tree, nodeNum * 2 + 1, (start + end) / 2 + 1, end, left, right);
	}

	static void update(ArrayList<Long> tree, int nodeNum, int start, int end, int index, long diff) {
		if (index < start || index > end) {
			return;
		}

		tree.set(nodeNum, tree.get(nodeNum) + diff);
		if (start != end) {
			update(tree, nodeNum * 2, start, (start + end) / 2, index, diff);
			update(tree, nodeNum * 2 + 1, (start + end) / 2 + 1, end, index, diff);
		}
	}

	static class Change {
		int idx;
		long value;

		public Change(int first, long second) {
			this.idx = first;
			this.value = second;
		}
	}
}