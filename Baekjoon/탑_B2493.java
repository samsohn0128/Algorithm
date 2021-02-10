package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class 탑_B2493 {

	static int N; // 타워의 개수
	static int[] towers; // 타워의 높이를 저장할 배열
	static int[] ans; // 정답을 저장할 배열
	static Stack<Tower> towerStack = new Stack<>(); // 정답을 계산하기위한 스텍

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력을 받기위한 BufferedReader

		N = Integer.parseInt(br.readLine()); // 타워의 개수 입력
		towers = new int[N]; // 타워의 높이를 저장할 배열 생성
		ans = new int[N]; // 정답을 저장할 배열 생성
		StringTokenizer st = new StringTokenizer(br.readLine()); // 입력을 나눠서 받기위한 StringTokenizer
		for (int i = 0; i < N; i++) // 타워의 개수 만큼
			towers[i] = Integer.parseInt(st.nextToken()); // 타워의 높이 정보를 저장

		// 모든 타워는 레이저 신호를 왼쪽 방향으로만 발사하므로
		for (int i = N - 1; i >= 0; i--) { // 타워를 입력받은 순서의 역순으로
			while (!towerStack.isEmpty()) { // 타워 스텍이 존재하는 동안 (처음에는 비어있으므로 넘어감)
				if (towers[i] >= towerStack.peek().height) { // 타워의 높이 비교 -> 왼쪽이 더 높다면
					ans[towerStack.pop().index] = i + 1; // 더 낮은 타워의 정답을 왼쪽 타워의 인덱스로 저장
				} else { // 타워의 높이 비교 -> 오른쪽이 더 높다면
					break; // 멈춤
				}
			}
			towerStack.push(new Tower(towers[i], i)); // 다음 계산을 위해 스텍에 저장
		}
		
		while (!towerStack.isEmpty()) // 모든 계산을 완료후 스텍에 남은 타워가 있다면 -> 신호를 받아줄 수 있는 타워가 없다.
			ans[towerStack.pop().index] = 0; // 정답에 0 을 저장

		for (int i = 0; i < N; i++) { // 타워의 개수 만큼
			System.out.print(ans[i] + " "); // 정답을 출력
		}
		br.close();
	}
}

class Tower {
	public int height;
	public int index;

	public Tower(int height, int index) {
		this.height = height;
		this.index = index;
	}
}