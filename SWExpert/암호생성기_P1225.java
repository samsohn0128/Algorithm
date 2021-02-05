package SWExpert;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 암호생성기_P1225 {

	static int T;
	static Queue<Integer> numQ = new LinkedList<>();

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt")); // input.txt로부터 입력을 받아옴
		System.setOut(new PrintStream("myOutput.txt")); // myOutput.txt로 정답을 출력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력을 받기위한 BufferedReader

//		T = Integer.parseInt(br.readLine());
		T = 10;
		for (int tc = 1; tc <= T; tc++) {
			br.readLine();
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 8; i++)
				numQ.offer(Integer.parseInt(st.nextToken()));
			int cnt = 1;
			while (true) {
				int temp = numQ.poll();
				temp -= cnt;
				if (cnt++ == 5)
					cnt = 1;
				if (temp <= 0) {
					temp = 0;
					numQ.offer(temp);
					break;
				}
				numQ.offer(temp);
			}
			System.out.print("#" + tc + " ");
			for (int i = 0; i < 8; i++)
				System.out.print(numQ.poll() + " ");
			System.out.println();
		}

		br.close();
	}
}