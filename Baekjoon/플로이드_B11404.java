package Baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 플로이드_B11404 {

	static int N, M;
	static int[][] edges;
	static int[][] dist;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		edges = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++)
			for (int j = 1; j <= N; j++)
				edges[i][j] = 10000001;

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			edges[a][b] = Math.min(edges[a][b], w);
		}

		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (i == j)
						continue;
					edges[i][j] = Math.min(edges[i][j], edges[i][k] + edges[k][j]);
				}
			}
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (edges[i][j] == 10000001)
					System.out.print("0 ");
				else
					System.out.print(edges[i][j] + " ");
			}
			System.out.println();
		}

		br.close();
	}
}