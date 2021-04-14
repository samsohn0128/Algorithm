package Baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 배열돌리기2_B16927 {

	static int N, M, R;
	static int input[][];

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

		counterclockwise();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(input[i][j] + " ");
			}
			System.out.println();
		}
		br.close();
	}

	static void counterclockwise() {
		int cnt = 0;
		while (cnt < Math.min(N, M) / 2) {
			int numOfSide = 2 * (N - (2 * cnt)) + 2 * (M - (2 * cnt)) - 4;
			int rTemp = R % numOfSide;
				for (int r = 0; r < rTemp; r++) {
					int temp = input[cnt][M - cnt - 1];
					for (int i = cnt + 1; i < N - cnt; i++) {
						input[i - 1][M - cnt - 1] = input[i][M - cnt - 1];
					}
					for (int j = M - cnt - 2; j >= cnt; j--) {
						input[N - cnt - 1][j + 1] = input[N - cnt - 1][j];
					}
					for (int i = N - cnt - 2; i >= cnt; i--) {
						input[i + 1][cnt] = input[i][cnt];
					}
					for (int j = cnt + 1; j < M - cnt - 1; j++) {
						input[cnt][j - 1] = input[cnt][j];
					}
					input[cnt][M - cnt - 2] = temp;
				}
			cnt++;
		}
	}
}