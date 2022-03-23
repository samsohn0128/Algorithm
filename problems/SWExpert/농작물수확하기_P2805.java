package SWExpert;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 농작물수확하기_P2805 {

	static int T;
	static int N;
	static int midN;
	static int[][] grid;
	static int ans, index;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			grid = new int[N][N];
			midN = N / 2;
			index = 0;
			ans = 0;

			for (int i = 0; i < N; i++) {
				char[] temp = br.readLine().toCharArray();
				for (int j = 0; j < N; j++) {
					grid[i][j] = temp[j] - '0';
				}
			}
			recur(0);
			System.out.println("#" + tc + " " + ans);
		}

		br.close();
	}

	static void recur(int cnt) {
		for (int i = midN - cnt; i <= midN + cnt; i++)
			ans += grid[index][i];
		index++;
		
		if (cnt == midN)
			return;
		
		recur(cnt + 1);
		
		for (int i = midN - cnt; i <= midN + cnt; i++)
			ans += grid[index][i];
		index++;
	}
}