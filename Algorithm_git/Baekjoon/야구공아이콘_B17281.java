package Baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 야구공아이콘_B17281 {

	static int N;
	static int[][] input;
	static int[] batters = { 2, 3, 4, 5, 6, 7, 8, 9 };
	static boolean[] visited = new boolean[10];
	static int ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		input = new int[N + 1][10];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= 9; j++) {
				input[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		play();
		while (np()) {
			play();
		}
		
		System.out.println(ans);
		br.close();
	}

	static boolean np() {
		int i = batters.length - 1;
		while (i > 0 && batters[i - 1] >= batters[i])
			--i;

		if (i == 0)
			return false;

		int j = batters.length - 1;
		while (batters[i - 1] >= batters[j])
			--j;
		swap(batters, i - 1, j);

		int k = batters.length - 1;
		while (i < k) {
			swap(batters, i++, k--);
		}
		return true;
	}

	static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	static void play() {
		int inning = 1;
		int curNum = 1;
		int score = 0;
		int out = 0;
		boolean[] runner = new boolean[4];

		while (inning <= N) {
			int b = curNum == 4 ? 1 : curNum < 4 ? batters[curNum - 1] : batters[curNum - 2];
			switch (input[inning][b]) {
			case 0:
				out++;
				if (out == 3) {
					inning++;
					for (int i = 0; i < 4; i++)
						runner[i] = false;
					out = 0;
				}
				break;
			case 1:
				if (runner[3])
					score++;
				runner[3] = runner[2];
				runner[2] = runner[1];
				runner[1] = true;
				break;
			case 2:
				if (runner[3])
					score++;
				if (runner[2])
					score++;
				runner[3] = runner[1];
				runner[2] = true;
				runner[1] = false;
				break;
			case 3:
				if (runner[3])
					score++;
				if (runner[2])
					score++;
				if (runner[1])
					score++;
				runner[3] = true;
				runner[2] = false;
				runner[1] = false;
				break;
			case 4:
				if (runner[3])
					score++;
				if (runner[2])
					score++;
				if (runner[1])
					score++;
				score++;
				for (int i = 0; i < 4; i++)
					runner[i] = false;
				break;
			}
			if (++curNum == 10)
				curNum = 1;
		}
		ans = Math.max(ans, score);
	}
}