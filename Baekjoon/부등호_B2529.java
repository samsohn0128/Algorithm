package Baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 부등호_B2529 {
	static int K;
	static char[] input;
	static int[] numMax, numMin, tgt;
	static boolean[] visited;
	static long ansMax, ansMin, ansTemp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		K = Integer.parseInt(br.readLine());
		input = new char[K];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++)
			input[i] = st.nextToken().charAt(0);

		numMax = new int[K + 1];
		numMin = new int[K + 1];
		tgt = new int[K + 1];
		visited = new boolean[K + 1];

		for (int i = 0; i < K + 1; i++)
			numMax[i] = 9 - i;
		for (int i = 0; i < K + 1; i++)
			numMin[i] = i;

		perm(numMax, 0, 0);
		cnt = 0;
		perm(numMin, 1, 0);

		StringBuilder sb = new StringBuilder();
		if (Long.toString(ansMax).length() < K + 1)
			sb.append("0");
		sb.append(ansMax).append("\n");
		if (Long.toString(ansMin).length() < K + 1)
			sb.append("0");
		sb.append(ansMin);
		System.out.println(sb.toString());
		br.close();
	}

	static int cnt = 0;

	static void perm(int[] arr, int minMax, int tgtIdx) {
		if (tgtIdx == tgt.length) {
			cnt++;
			chk(tgt, minMax);
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			if (!visited[i]) {
				visited[i] = true;
				tgt[tgtIdx] = arr[i];
				perm(arr, minMax, tgtIdx + 1);
				visited[i] = false;
			}
		}
	}

	static void chk(int[] arr, int minMax) {
		boolean flag = true;
		for (int i = 0; i < K; i++) {
			if (input[i] == '<') {
				if (arr[i] > arr[i + 1]) {
					flag = false;
					break;
				}
			} else if (input[i] == '>') {
				if (arr[i] < arr[i + 1]) {
					flag = false;
					break;
				}
			}
		}
		if (flag) {
			ansTemp = 0;
			long mul = 1;
			for (int i = 0; i < arr.length - 1; i++)
				mul *= 10;

			for (int i = 0; i < arr.length; i++) {
				ansTemp += arr[i] * mul;
				mul /= 10;
			}
			if (minMax == 0) {
				ansMax = Math.max(ansMax, ansTemp);
			} else {
				if (ansMin == 0)
					ansMin = ansTemp;
				else
					ansMin = Math.min(ansMin, ansTemp);
			}
		}
	}
}
