package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class 가장긴증가하는부분수열5_B14003 {

	static int N;
	static int[] input = new int[1000001];
	static int[] len = new int[1000001];
	static ArrayList<Integer> list = new ArrayList<>();
	static Stack<Integer> s = new Stack<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}

		list.add(-1000000001);
		for (int i = 0; i < N; i++) {
			if (list.get(list.size() - 1) < input[i]) {
				list.add(input[i]);
				len[i] = list.size() - 1;
			} else {
				int idx = binarySearch(input[i]);
				list.set(idx, input[i]);
				len[i] = idx;
			}
		}
		list.remove(0);
		int result = list.size();
		System.out.println(result);

		for (int i = N - 1; i >= 0; i--) {
			if (len[i] == result) {
				s.push(input[i]);
				result--;
			}
		}
		while (!s.isEmpty()) {
			System.out.print(s.pop() + " ");
		}

		br.close();
	}

	static int binarySearch(int n) {
		int l = 0;
		int r = list.size() - 1;
		while (l < r) {
			int mid = (l + r) >> 1;
			if (list.get(mid) < n) {
				l = mid + 1;
			} else {
				r = mid;
			}
		}
		return r;
	}
}