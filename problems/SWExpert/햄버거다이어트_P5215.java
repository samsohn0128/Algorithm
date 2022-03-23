package SWExpert;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 햄버거다이어트_P5215 {

	static int T;
	static int N, L;
	static int[] tasteScore;
	static int[] calories;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력을 받기위한 BufferedReader
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			tasteScore = new int[N];
			calories = new int[N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				tasteScore[i] = Integer.parseInt(st.nextToken());
				calories[i] = Integer.parseInt(st.nextToken());
			}
			ansScore = 0;
			comb(0, 0, 0);
			System.out.println("#" + tc + " " + ansScore);
		}

		br.close();
	}

	static int ansScore;

	public static void comb(int idx, int curScore, int curCal) {
		if (idx == N)
			return;

		comb(idx + 1, curScore, curCal);
		curCal += calories[idx];
		if (curCal <= L) {
			curScore += tasteScore[idx];
			ansScore = Math.max(ansScore, curScore);
			comb(idx + 1, curScore, curCal);
		}
	}
}