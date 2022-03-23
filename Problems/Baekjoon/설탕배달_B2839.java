package Baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 설탕배달_B2839 {
	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		int ans = -1;
		int num5 = 0;
		while (5 * num5 <= N) {
			int temp = N;
			temp -= 5 * num5;
			if (temp % 3 == 0) {
				int num3 = temp / 3;
				if (ans == -1)
					ans = num3 + num5;
				else
					ans = Math.min(ans, num3 + num5);
			}
			num5++;
		}

		System.out.println(ans);

		br.close();
	}
}