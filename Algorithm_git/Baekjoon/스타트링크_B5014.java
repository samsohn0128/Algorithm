package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 스타트링크_B5014 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int f, s, g, u, d;
		st = new StringTokenizer(br.readLine());
		f = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		g = Integer.parseInt(st.nextToken());
		u = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		boolean[] visited = new boolean[f + 1];
		visited[s] = true;
		boolean chk = true;
		int cnt = 0;
		while (s != g) {
			cnt++;
			if (s < g) {
				if (s + u <= f && !visited[s + u]) {
					visited[s + u] = true;
					s += u;
				} else if (s - d > 0 && !visited[s - d]) {
					visited[s - d] = true;
					s -= d;
				} else {
					chk = false;
					break;
				}
			} else if (s > g) {
				if (s - d > 0 && !visited[s - d]) {
					visited[s - d] = true;
					s -= d;
				} else if (s + u <= f && !visited[s + u]) {
					visited[s + u] = true;
					s += u;
				} else {
					chk = false;
					break;
				}
			}
		}
		if (chk) {
			System.out.println(cnt);
		} else {
			System.out.println("use the stairs");
		}
		br.close();
	}
}