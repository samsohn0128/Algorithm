package Baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 전화번호목록_B5052 {

	static int T, N;
	static String[] input;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			N = Integer.parseInt(br.readLine());
			input = new String[N];
			for (int i = 0; i < N; i++)
				input[i] = br.readLine();

			Arrays.sort(input, (s1, s2) -> {
				char[] c1 = s1.toCharArray();
				char[] c2 = s2.toCharArray();
				int len = Math.min(c1.length, c2.length);
				for (int i = 0; i < len; i++) {
					if (c1[i] != c2[i]) {
						return c1[i] - c2[i];
					}
				}
				return c1.length - c2.length;
			});
			
			boolean ans = true;
			for (int i = 0; i < N - 1; i++) {
				if(!chk(input[i],input[i+1])) {
					ans=false;
					break;
				}
			}
			
			if (ans) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
		}
		br.close();
	}

	static boolean chk(String s1, String s2) {
		char[] c1 = s1.toCharArray();
		char[] c2 = s2.toCharArray();

		int len = Math.min(c1.length, c2.length);

		for (int i = 0; i < len; i++) {
			if (c1[i] != c2[i])
				return true;
		}

		return false;
	}
}