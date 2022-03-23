package SWExpert;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.StringTokenizer;

public class 수영장_P1952_DP {
	static int T;
	static int[] price = new int[4];
	static int[] month = new int[13];
	static int[] dp = new int[13];

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("sample_input.txt")); // sample_input.txt로부터 입력을 받아옴
		System.setOut(new PrintStream("sample_myOutput.txt")); // sample_myOutput.txt로 정답을 출력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력을 받기위한 BufferedReader
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++)
				price[i] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= 12; i++) {
				month[i] = Integer.parseInt(st.nextToken());
				dp[i] = Integer.MAX_VALUE;
			}
			dp[0] = 0;
			for (int i = 1; i <= 12; i++) {
				dp[i] = Math.min(dp[i - 1] + price[0] * month[i], dp[i - 1] + price[1]);
				if (i >= 3)
					dp[i] = Math.min(dp[i - 3] + price[2], dp[i]);
			}
			dp[12] = Math.min(dp[12], price[3]);
			System.out.println("#" + tc + " " + dp[12]);
		}
		br.close();
	}
}