package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 가장긴증가하는부분수열2_B12015 {
	static int N;
	static int[] input = new int[1000001];
	static ArrayList<Integer> list = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		list.add(0);
		for (int i = 0; i < N; i++) {
			if (list.get(list.size() - 1) < input[i]) {
				list.add(input[i]);
			} else {
				int idx = binarySearch(input[i]);
				list.set(idx, input[i]);
			}
		}
		System.out.println(list.size() - 1);
		br.close();
	}

	static int binarySearch(int n) {
		int l = 0;
		int r = list.size() - 1;
		int mid = 0;
		while (l < r) {
			mid = (l + r) >> 1;
			if (list.get(mid) < n) {
				l = mid + 1;
			} else if (list.get(mid) >= n) {
				r = mid;
			}
		}
		return r;
	}
}