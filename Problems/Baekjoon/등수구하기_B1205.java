package Baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 등수구하기_B1205 {

	static int N, newScore, P;
	static int[] scores;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		newScore = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());

		if (N > 0) {
			scores = new int[N];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++)
				scores[i] = Integer.parseInt(st.nextToken());

			int ans = 1;
			int idx = 0;
			int cnt = 0;
			while (idx < N && newScore < scores[idx]) {
				ans++;
				idx++;
			}
			while (idx < N && newScore == scores[idx]) {
				cnt++;
				idx++;
			}
			if (ans - 1 + cnt < P) {
				System.out.println(ans);
			} else {
				System.out.println(-1);
			}
		} else {
			if(P==0)
				System.out.println(-1);
			else
				System.out.println(1);
		}

		br.close();
	}
}