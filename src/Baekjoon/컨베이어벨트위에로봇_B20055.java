package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class 컨베이어벨트위에로봇_B20055 {

	static int N, K;
	static boolean[] robot = new boolean[101];
	static List<Integer> list = new LinkedList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 2 * N; i++) {
			list.add(Integer.parseInt(st.nextToken()));
		}
		int ans = 1;
		while (true) {
			boolean ret = simulate();
			if (!ret)
				break;
			ans++;
		}
		System.out.println(ans);
		br.close();
	}

	static boolean simulate() {
		// 1
		int rear = list.remove(2 * N - 1);
		list.add(0, rear);

		for (int i = N - 2; i > 0; i--) {
			robot[i] = robot[i - 1];
		}
		robot[0] = false;
		robot[N - 1] = false;
		// 2
		for (int i = N - 1; i > 0; i--) {
			if (!robot[i] && robot[i - 1] && list.get(i) > 0) {
				robot[i] = true;
				robot[i - 1] = false;
				list.set(i, list.get(i) - 1);
			}
		}
		robot[N - 1] = false;
		// 3
		if (list.get(0) > 0) {
			robot[0] = true;
			list.set(0, list.get(0) - 1);
		}
		// 4
		int cnt = 0;
		for (int i = 0; i < 2 * N; i++) {
			if (list.get(i) == 0)
				cnt++;
		}
		if (cnt >= K)
			return false;
		else
			return true;
	}
}