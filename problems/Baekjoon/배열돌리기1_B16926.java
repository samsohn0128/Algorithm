package Baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 배열돌리기1_B16926 {

	static int N, M, R;
	static int input[][];
	static int ans[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		input = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				input[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < R; i++) {
			counterclockwise();
			input = ans;
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(input[i][j] + " ");
			}
			System.out.println();
		}

		br.close();
	}

	static void counterclockwise() {
		ans = new int[N][M];
		int cnt = 0;
		while (cnt < Math.min(N, M) / 2) {
			for (int j = cnt + 1; j < M - cnt; j++) {
				ans[cnt][j - 1] = input[cnt][j];
			}
			for (int i = cnt; i < N - cnt - 1; i++) {
				ans[i + 1][cnt] = input[i][cnt];
			}
			for (int j = cnt; j < M - cnt - 1; j++) {
				ans[N - cnt - 1][j + 1] = input[N - cnt - 1][j];
			}
			for (int i = cnt + 1; i < N - cnt; i++) {
				ans[i - 1][M - cnt - 1] = input[i][M - cnt - 1];
			}
			cnt++;
		}
	}
}
