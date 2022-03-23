package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Sort마스터배지훈의후계자_B20551 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] input = new int[N];
		Node[] quest = new Node[M];
		int[] ans = new int[M];
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(input);
		for (int i = 0; i < M; i++) {
			quest[i] = new Node(Integer.parseInt(br.readLine()), i);
		}
		Arrays.sort(quest, (n1, n2) -> n1.num - n2.num);

		int inputIdx = 0;
		int questIdx = 0;
		Arrays.fill(ans, -1);
		while (inputIdx < N && questIdx < M) {
			if (input[inputIdx] == quest[questIdx].num) {
				ans[quest[questIdx].index] = inputIdx;
				questIdx++;
			} else if (input[inputIdx] > quest[questIdx].num) {
				questIdx++;
			} else {
				inputIdx++;
			}
		}
		for (int i : ans)
			System.out.println(i);

		br.close();
	}

	static class Node {
		int num;
		int index;

		public Node(int num, int index) {
			this.num = num;
			this.index = index;
		}

		@Override
		public String toString() {
			return "Node [num=" + num + ", index=" + index + "]";
		}
	}
}