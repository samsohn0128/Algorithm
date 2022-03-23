package Baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 비밀번호제작_B20304 {

	static int N; // 관리자 패스워드의 최대값
	static int M; // 해커가 사용한 패스워드의 개수
	static int[] hacker; // 해커가 사용한 패스워드
	static int[] dist = new int[1000001];

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력을 받기위한 BufferedReader
		StringTokenizer st;// 입력을 처리하기위한 StringTokenizer

		N = Integer.parseInt(br.readLine()); // 관리자 패스워드의 최대값 입력
		M = Integer.parseInt(br.readLine()); // 해커가 사용한 패스워드의 개수 입력
		hacker = new int[M]; // 해커가 사용한 패스워드를 저장할 배열 생성
		Arrays.fill(dist, -1);

		Queue<Integer> q = new LinkedList<>();

		st = new StringTokenizer(br.readLine()); // 해커가 사용한 패스워드 입력
		for (int i = 0; i < M; i++) {
			hacker[i] = Integer.parseInt(st.nextToken()); // 해커가 사용한 패스워드 저장
			dist[hacker[i]] = 0;
			q.offer(hacker[i]);
		}

		while (!q.isEmpty()) {
			int temp = q.poll();
			for (int i = 1; i <= N; i <<= 1) {
				int next = (temp & i) > 0 ? temp - i : temp + i;
				if (next <= N && dist[next] == -1) {
					dist[next] = dist[temp] + 1;
					q.offer(next);
				}
			}
		}

		int maxScore = 0; // 가장 높은 보안 척도를 저장할 변수
		for (int i = 0; i <= N; i++) {
			maxScore = Math.max(maxScore, dist[i]);
		}

		System.out.println(maxScore); // 정답 출력
		br.close();
	}
}
