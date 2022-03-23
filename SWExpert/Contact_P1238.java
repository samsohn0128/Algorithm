package SWExpert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Contact_P1238 {
	static int T;
	static int N, S;
	static boolean[][] adjacent = new boolean[101][101];
	static boolean[] visited = new boolean[101];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력을 받기위한 BufferedReader
		StringTokenizer st;

		T = 10;
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			S = Integer.parseInt(st.nextToken());

			for (int i = 1; i <= 100; i++) {
				for (int j = 1; j <= 100; j++) {
					adjacent[i][j] = false;
				}
				visited[i] = false;
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N / 2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				adjacent[from][to] = true;
			}

			Queue<Integer> q = new LinkedList<>();
			q.offer(S);
			visited[S] = true;
			int size = 1;
			int ans = 0;
			int tempMax = 0;
			while (!q.isEmpty()) {
				int temp = q.poll();
//				System.out.println(temp);
				tempMax = Math.max(tempMax, temp);
				for (int i = 1; i <= 100; i++) {
					if (adjacent[temp][i] && !visited[i]) {
						visited[i] = true;
						q.offer(i);
					}
				}
				if (--size == 0) {
					size = q.size();
					if (size == 0) {
						ans = tempMax;
					}
					tempMax = 0;
				}
			}
			System.out.println("#" + tc + " " + ans);
		}
		br.close();
	}
}
//5656 벽돌깨기
//5648 원자소멸시뮬레이션
//2117 홈방범서비스
//5644 무선충전