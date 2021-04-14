package Baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 삼대측정_B20299 {

	static int N, S, M; // N : 신청한 동이라 수, S : 팀원 3명의 능력합 가입 조건, M : 개인 능력치 가입 조건
	static int[] x1, x2, x3; // 신청한 동아리원들의 능력치
	static boolean[] allowed;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력을 위한 BufferedReader
		StringTokenizer st; // 입력 처리를 위한 StringTokenizer

		st = new StringTokenizer(br.readLine()); // N,S,M 입력
		N = Integer.parseInt(st.nextToken()); // N 저장
		S = Integer.parseInt(st.nextToken()); // S 저장
		M = Integer.parseInt(st.nextToken()); // M 저장

		x1 = new int[N];
		x2 = new int[N];
		x3 = new int[N];
		allowed = new boolean[N];

		int cnt = 0; // 가입이 가능한 동아리의 수

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine()); // 학생들의 능력치 입력
			x1[i] = Integer.parseInt(st.nextToken()); // 학생1의 능력치 입력
			x2[i] = Integer.parseInt(st.nextToken()); // 학생2의 능력치 입력
			x3[i] = Integer.parseInt(st.nextToken()); // 학생3의 능력치 입력
		}

		for (int i = 0; i < N; i++) {
			if (x1[i] + x2[i] + x3[i] >= S && x1[i] >= M && x2[i] >= M && x3[i] >= M) {
				cnt++;
				allowed[i] = true;
			}
		}

		System.out.println(cnt); // 가입이 가능한 동아리의 수 출력
		for(int i = 0; i < N; i++){
			if(allowed[i]){
				System.out.print(x1[i] + " " + x2[i] + " "+x3[i]+" ");
			}
		}

		br.close();
	}
}