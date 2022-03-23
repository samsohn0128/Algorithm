package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 배열돌리기3_B16935 {

	static int N, M, R; // N : 배열의 행의 크기, M : 배열의 열의 크기, R : 연산의 수
	static int input[][]; // 입력받을 행렬을 저장할 변수
	static int ans[][]; // 회전된 행렬을 저장할 변수

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력을 받기 위한 BufferedReader

		StringTokenizer st = new StringTokenizer(br.readLine()); // 입력을 처리하기 위한 StringTokenizer
		N = Integer.parseInt(st.nextToken()); // 행렬의 행의 크기 입력
		M = Integer.parseInt(st.nextToken()); // 행렬의 열의 크기 입력
		R = Integer.parseInt(st.nextToken()); // 연사의 수 입력
		input = new int[N][M]; // 배열 생성

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				input[i][j] = Integer.parseInt(st.nextToken()); // 행렬 입력
			}
		}

		st = new StringTokenizer(br.readLine()); // 연산 입력
		for (int i = 0; i < R; i++) { // 연사의 수 만큼
			switch (Integer.parseInt(st.nextToken())) {
			case 1: // 1번 연산인 경우
				upDown(); // 상하 반전 메소드 호출
				break;
			case 2: // 2번 연산인 경우
				leftRight(); // 좌우 반전 메소드 호출
				break;
			case 3: // 3번 연산인 경우
				right90(); // 오른쪽으로 90도 회전 메소드 호출
				break;
			case 4: // 4번 연산인 경우
				left90(); // 왼쪽으로 90도 회전 메소드 호출
				break;
			case 5: // 5번 연산인 경우
				clockwise(); // 시계방향 회전 메소드 호출
				break;
			case 6: // 6번 연산인 경우
				counterclockwise(); // 반시계방향 회전 메소드 호출
				break;
			}
			input = ans; // 연산이 완료된 행렬은 다시 input으로 변경
			N = input.length; // 행렬의 행의 크기 갱신
			M = input[0].length; // 행렬의 열의 크기 갱신
		}

		// 행렬 출력
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(input[i][j] + " ");
			}
			System.out.println();
		}

		br.close();
	}

	// 상하 반전 메소드
	static void upDown() {
		ans = new int[N][M]; // 회전된 행렬을 담을 배열 생성
		for (int i = 0; i < N / 2; i++) { // 상하를 기준으로 절반만큼
			// 상하 변경
			ans[i] = input[N - i - 1];
			ans[N - i - 1] = input[i];
		}
	}

	// 좌우 반전 메소드
	static void leftRight() {
		ans = new int[N][M]; // 회전된 행렬을 담을 배열 생성
		for (int i = 0; i < N; i++) { // 모든 행에 대해서
			for (int j = 0; j < M / 2; j++) { // 좌우를 기준으로 절만큼
				// 좌우변경
				ans[i][j] = input[i][M - j - 1];
				ans[i][M - j - 1] = input[i][j];
			}
		}
	}

	// 오른쪽으로 90도 회전 메소드
	static void right90() {
		ans = new int[M][N]; // 회전된 행렬을 담을 배열 생성
		for (int i = 0; i < N; i++) { // 모든 행에 대해서
			for (int j = 0; j < M; j++) { // 모든 열에 대해서
				ans[j][N - i - 1] = input[i][j]; // 오른쪽으로 90도 회전
			}
		}
	}

	// 왼쪽으로 90도 회전 메소드
	static void left90() {
		ans = new int[M][N]; // 회전된 행렬을 담을 배열 생성
		for (int i = 0; i < N; i++) { // 모든 행에 대해서
			for (int j = 0; j < M; j++) { // 모든 열에 대해서
				ans[M - j - 1][i] = input[i][j]; // 왼쪽으로 90도 회
			}
		}
	}

	// 시계방향 회전 메소드
	static void clockwise() {
		ans = new int[N][M]; // 회전된 행렬을 담을 배열 생성
		for (int i = 0; i < N; i++) { // 모든 행에 대해서
			for (int j = 0; j < M; j++) { // 모든 열에 대해서
				if (i < N / 2 && j < M / 2) { // 왼쪽 위 그룹
					ans[i][j + M / 2] = input[i][j]; // 오른쪽으로 밀기
				} else if (i < N / 2 && j >= M / 2) { // 오른쪽 위 그룹
					ans[i + N / 2][j] = input[i][j]; // 아래로 밀기
				} else if (i >= N / 2 && j >= M / 2) { // 오른쪽 아래 그룹
					ans[i][j - M / 2] = input[i][j]; // 왼쪽으로 밀기
				} else if (i >= N / 2 && j < M / 2) { // 왼쪽 아래 그룹
					ans[i - N / 2][j] = input[i][j]; // 위로 밀기
				}
			}
		}
	}

	// 반시계방향 회전 메소드
	static void counterclockwise() {
		ans = new int[N][M]; // 회전된 행렬을 담을 배열 생성
		for (int i = 0; i < N; i++) { // 모든 행에 대해서
			for (int j = 0; j < M; j++) { // 모든 열에 대해서
				if (i < N / 2 && j < M / 2) { // 왼쪽 위 그룹
					ans[i + N / 2][j] = input[i][j]; // 아래로 밀기
				} else if (i < N / 2 && j >= M / 2) { // 오른쪽 위 그룹
					ans[i][j - M / 2] = input[i][j]; // 왼쪽으로 밀기
				} else if (i >= N / 2 && j >= M / 2) { // 오른쪽 아래 그룹
					ans[i - N / 2][j] = input[i][j]; // 위로 밀기
				} else if (i >= N / 2 && j < M / 2) { // 왼쪽 아래 그룹
					ans[i][j + M / 2] = input[i][j]; // 오른쪽으로 밀기
				}
			}
		}
	}
}
