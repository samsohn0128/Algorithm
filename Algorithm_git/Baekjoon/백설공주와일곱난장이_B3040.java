package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 백설공주와일곱난장이_B3040 {

	static int[] src = new int[9]; // 입력받을 9개의 숫자를 저장할 배열
	static int[] tgt = new int[7]; // 조합을 이용한 결과물을 저장할 배열
	static int[] ans = new int[7]; // 정답 저장할 배열
	static boolean flag;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력을 받을 BufferedReader

		for (int i = 0; i < 9; i++)
			src[i] = Integer.parseInt(br.readLine()); // 9개의 숫자 입력

		comb(0, 0);// 모든 조합 탐색

		br.close();
	}

	static void comb(int srcIdx, int tgtIdx) {
		if (flag)
			return;

		if (tgtIdx == 7) { // 7개의 조합이 완성된 경우
			if (chk() == 100) { // 모든 숫자의 합이 100인 경우
				for (int i = 0; i < 7; i++) {
					System.out.println(tgt[i]);
				}
				flag = true;
			}
			return;
		}
		if (srcIdx == 9) // 입력 배열을 끝까지 탐색한 경우
			return;

		comb(srcIdx + 1, tgtIdx); // 선택하지않고 다음으로 넘김
		tgt[tgtIdx] = src[srcIdx]; // 선택
		comb(srcIdx + 1, tgtIdx + 1); // 선택하고 다음으로 넘김
	}

	// 총합 구하기
	static int chk() {
		int ret = 0;// 리턴할 숫자를 저장할 변수
		for (int i = 0; i < 7; i++) {
			ret += tgt[i]; // 모두 더하기
		}
		return ret;
	}
}