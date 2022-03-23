package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 색종이_B2563 {

	static int N; // 색종이의 개수
	static boolean[][] arr = new boolean[100][100]; // 흰색 도화지 영역

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력을 받기 위한 BufferedReader

		N = Integer.parseInt(br.readLine()); // 색종이의 개수 입력

		for (int i = 0; i < N; i++) { // 모든 색종이에 대해
			StringTokenizer st = new StringTokenizer(br.readLine()); // 입력을 처리하기 위한 StringToker
			int x = Integer.parseInt(st.nextToken());// 왼쪽 변으로부터의 거리 입력
			int y = Integer.parseInt(st.nextToken());// 아래쪽 변으로부터의 거리 입력

			for (int j = x; j < x + 10; j++) { // 정사각형의 왼쪽 변에서부터 10만큼
				for (int k = y; k < y + 10; k++) { // 정사각형의 아래쪽 변에서부터 10만큼
					arr[j][k] = true; // 영역 색칠
				}
			}
		}

		int ans = 0; // 정답을 저장할 변수
		for (int i = 0; i < 100; i++) { // 모든 도화지 영역에 대해
			for (int j = 0; j < 100; j++) { // 모든 도화지 영역에 대해
				if (arr[i][j]) // 영역이 색칠되어있다면
					ans++; // 정답 증가
			}
		}
		System.out.println(ans);// 정답 출력
		br.close();
	}

	// 정사각형 클래스
	static class Square {
		int x; // 왼쪽 변으로부터의 거리
		int y; // 아래쪽 변으로부터의 거리

		// 생성자
		public Square(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}
}
