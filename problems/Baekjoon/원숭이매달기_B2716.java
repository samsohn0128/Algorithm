package Baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 원숭이매달기_B2716 {

	static int T;
	static char[] input;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			input = br.readLine().toCharArray();
			int depth = 0;
			int maxDepth = 0;
			for (int i = 0; i < input.length; i++) {
				if (input[i] == '[')
					depth++;
				else
					depth--;
				maxDepth = Math.max(maxDepth, depth);
			}
			int ans = 1;
			for (int i = 0; i < maxDepth; i++) {
				ans *= 2;
			}
			System.out.println(ans);
		}

		br.close();
	}

}