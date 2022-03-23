package SWExpert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 숫자만들기_P4008 {
	static int T;
	static int N;
	static int[] oper = new int[4];
	static int[] num = new int[12];
	static int max, min;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력을 받기위한 BufferedReader
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++)
				oper[i] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++)
				num[i] = Integer.parseInt(st.nextToken());

			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;

			dfs(num[0], 1);

			System.out.println("#" + tc + " " + (max - min));
		}
		br.close();
	}

	static void dfs(int result, int pos) {
		if (pos == N) {
			max = Math.max(max, result);
			min = Math.min(min, result);
		}

		// +
		if (oper[0] > 0) {
			oper[0]--;
			dfs(result + num[pos], pos + 1);
			oper[0]++;
		}

		// -
		if (oper[1] > 0) {
			oper[1]--;
			dfs(result - num[pos], pos + 1);
			oper[1]++;
		}

		// *
		if (oper[2] > 0) {
			oper[2]--;
			dfs(result * num[pos], pos + 1);
			oper[2]++;
		}

		// /
		if (oper[3] > 0) {
			oper[3]--;
			dfs(result / num[pos], pos + 1);
			oper[3]++;
		}
	}
}

//4008 5656