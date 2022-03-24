package SWExpert;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 한빈이와SpotMart_P9229 {

	static int T;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력을 받기위한 BufferedReader

		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int[] snacks = new int[N];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				snacks[i] = Integer.parseInt(st.nextToken());
			}

			int ans = -1;
			for (int i = 0; i < N; i++) {
				for (int j = i + 1; j < N; j++) {
					if (snacks[i] + snacks[j] <= M && ans < snacks[i] + snacks[j])
						ans = snacks[i] + snacks[j];
				}
			}
			System.out.println("#" + tc + " " + ans);
		}
		br.close();
	}

	static int ansScore;
}