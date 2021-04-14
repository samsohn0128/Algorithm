package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 숫자놀이_B1755 {
	static int M, N; // 입력받을 숫자를 저장할 변수 1<= M, N <= 99

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력을 위한 BufferedReader
		StringTokenizer st = new StringTokenizer(br.readLine()); // 입력을 처리하기 위한 StringTokenizer
		M = Integer.parseInt(st.nextToken()); // 정수 N 입력
		N = Integer.parseInt(st.nextToken()); // 정수 M 입력
		PriorityQueue<String> pq = new PriorityQueue<>(); // 사전순으로 정렬을 위한 PriorityQueue

		for (int i = M; i <= N; i++) { // M부터 N까지의 숫자에 대해
			String str = Integer.toString(i); // 입력받은 정수를 한 자리씩 보기위해 String으로 변환
			StringBuilder sb = new StringBuilder(); // 한 자리씩 영어로 바꿔 String을 만들기 위한 StringBuilder
			for (int j = 0; j < str.length(); j++) { // 변환된 입력받은 정수의 모든 자리에 대해
				if (j != 0) // 첫 번째 숫자가 아닌 경우
					sb.append(" "); // 띄어쓰기
				switch (str.charAt(j)) { // 각각의 숫자에 대해 숫자를 영어로 번역하여 StringBuilder에 추가
				case '0':
					sb.append("zero");
					break;
				case '1':
					sb.append("one");
					break;
				case '2':
					sb.append("two");
					break;
				case '3':
					sb.append("three");
					break;
				case '4':
					sb.append("four");
					break;
				case '5':
					sb.append("five");
					break;
				case '6':
					sb.append("six");
					break;
				case '7':
					sb.append("seven");
					break;
				case '8':
					sb.append("eight");
					break;
				case '9':
					sb.append("nine");
					break;
				}
			}
			pq.offer(sb.toString()); // 입력받은 한 숫자가 완료된 경우 PriorityQueue에 추가
		}

		int cnt = 0; // 10개씩 출력하고 줄 바꿈을 위한 카운트 변수
		while (!pq.isEmpty()) { // PriorityQueue에 아이템이 남아있는 동안
			String temp = pq.poll(); // PriorityQueue에서 사전순으로 가장 빠른 숫자 poll
			StringTokenizer st2 = new StringTokenizer(temp); // 띄어쓰기를 기준으로 나누기 위한 StringTokenizer
			while (st2.hasMoreTokens()) { // StringTokenizer의 Token이 남아있는 동안
				String num = st2.nextToken(); // 한자리씩 영어로 번역되어있는 숫자를 저장하기위한 변수
				switch (num) { // 각각의 영어로 번역되어있는 숫자를 숫자로 다시 번역
				case "zero":
					System.out.print(0);
					break;
				case "one":
					System.out.print(1);
					break;
				case "two":
					System.out.print(2);
					break;
				case "three":
					System.out.print(3);
					break;
				case "four":
					System.out.print(4);
					break;
				case "five":
					System.out.print(5);
					break;
				case "six":
					System.out.print(6);
					break;
				case "seven":
					System.out.print(7);
					break;
				case "eight":
					System.out.print(8);
					break;
				case "nine":
					System.out.print(9);
					break;
				}
			}
			System.out.print(" "); // 한 숫자에 대해 출력 후 띄어쓰기
			if (++cnt % 10 == 0) // 10개를 출력하면
				System.out.println(); // 줄 바꿈
		}
	}
}