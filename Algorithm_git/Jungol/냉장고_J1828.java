package Jungol;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 냉장고_J1828 {

	static int N; // 총 화학 물질의 수
	static int[] minTemp; // 최소 온도
	static int[] maxTemp; // 최대 온도
	static boolean[] visited; // 이미 보관 처리되었는지 체크

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력을 받을 BufferedReader
		StringTokenizer st; // 입력을 처리할 StringTokenizer

		N = Integer.parseInt(br.readLine()); // 총 화학 물질의 수 입력
		minTemp = new int[N]; // 최소 온도를 저장할 배영 생
		maxTemp = new int[N]; // 최대 온도를 저장할 배영 생
		visited = new boolean[N]; // 이미 보관 처리 되었는지 체크할 배열 생성
		for (int i = 0; i < N; i++) { // 모든 화학 물질에 대해
			st = new StringTokenizer(br.readLine());
			minTemp[i] = Integer.parseInt(st.nextToken()); // 최소 온도 입력
			maxTemp[i] = Integer.parseInt(st.nextToken()); // 최대 온도 입력
		}

		int refCnt = 0; // 냉장고 개수
		int chemCnt = 0; // 처리한 화학물질의 개수
		while (chemCnt < N) {
			refCnt++; // 냉장고 한 대 증가
			int maxCnt = 0; // 화학 물질을 가장 많이 처리한 경우의 개수를 저장할 변수
			int maxT = 0; // 화학 물질을 가장 많이 처리한 경우의 온도를 저장할 변수
			for (int j = -270; j <= 10000; j++) { // -270도 ~ 10000도 까지
				int cnt = 0; // 각 온도마다 처리한 화학물질 개수 저장
				for (int i = 0; i < N; i++) {
					if (minTemp[i] <= j && j <= maxTemp[i] && !visited[i]) { // 온도가 알맞은 경우, 아직 처리하지 않은 경우
						cnt++; // 개수 증가
					}
				}
				if (maxCnt < cnt) { // 최대값, 그때의 온도 갱신
					maxCnt = cnt;
					maxT = j;
				}
			}

			for (int i = 0; i < N; i++) {
				if (minTemp[i] <= maxT && maxT <= maxTemp[i] && !visited[i]) { // 최대 온도에 해당하면
					visited[i] = true; // 처리
					chemCnt++; // 처리한 화학물질 개수 증가
				}
			}
		}
		System.out.println(refCnt);// 정답 출력

		br.close();
	}
}