package Baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 좋은수열_B2661 {

	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		recur(1, "1");
		br.close();
	}

	static boolean stop = false;

	public static void recur(int len, String str) {
		if (stop) {
			return;
		}

		if (len == N) {
			stop = true;
			System.out.println(str);
		} else {
			for (int i = 1; i <= 3; i++) {
				if (isSatisfy(str + i)) {
					recur(len + 1, str + i);
				}
			}
		}
	}

	public static boolean isSatisfy(String str) {
		int len = str.length();
		int loop = len / 2;
		int start = len - 1;
		int end = len;

		for (int i = 1; i <= loop; i++) {
			if (str.substring(start - i, end - i).equals(str.substring(start, end))) {
				return false;
			}
			start--;
		}
		return true;
	}
}