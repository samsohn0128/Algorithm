package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 쿼드트리_B1992 {

	static int N; // 영상의 크기
	static int[][] input; // 영상을 저장할 배열
	static StringBuilder sb = new StringBuilder(); // 압축한 결과 저장할 StringBuilder

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력을 받기위한 BufferedReader

		N = Integer.parseInt(br.readLine()); // 영상의 크기 입력
		input = new int[N][N]; // 영상을 저장할 배열 저장
		for (int i = 0; i < N; i++) {
			char[] temp = br.readLine().toCharArray();
			for (int j = 0; j < N; j++) {
				input[i][j] = temp[j] - '0'; // 영상 입력
			}
		}

		recur(0, 0, N); // 재귀 호출
		System.out.println(sb); // 압축한 결과 출력
		br.close();
	}

	static void recur(int y, int x, int n) {
		int ret = chk(y, x, n); // 압축이 가능한지 확인
		if (n == 1) { // 기저조건
			sb.append(ret); // 압축할 번호 저장
			return;
		}

		n /= 2; // 영상 나누기

		if (ret == -1) { // 0, 1이 섞여있다면 (압축이 불가능하다면)
			sb.append('('); // '(' 저장
			recur(y, x, n); // 영상의 왼쪽 위 압축
			recur(y, x + n, n); // 영상의 오른쪽 위 압축
			recur(y + n, x, n); // 영상의 왼쪽 아래 압축
			recur(y + n, x + n, n); // 영상의 오른쪽 아래 압축
			sb.append(')'); // ')' 저
		} else { //압축이 가능하다면
			sb.append(ret); //압축된 결과를 저장
		}
	}

	//영상이 압축 가능한지 확인하는 메소드
	static int chk(int y, int x, int n) {
		int num = input[y][x]; // 영상의 처음 숫자
		boolean flag = true; // 영상이 압축 가능한지 저장할 변수
		for (int i = y; i < y + n; i++) {
			for (int j = x; j < x + n; j++) {
				if (input[i][j] != num) { //영상의 처음 숫자와 다르다면
					flag = false; // 영상이 압축 불가능
					break;
				}
			}
			if (!flag)
				break;
		}
		return flag ? num : -1; // 압축 가능하다면 압축할 번호를, 불가능하다면 -1을 리턴
	}
}