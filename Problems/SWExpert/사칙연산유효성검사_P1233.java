package SWExpert;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 사칙연산유효성검사_P1233 {
	static int N;
	static char[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력을 받기위한 BufferedReader

		for (int tc = 1; tc <= 10; tc++) {
			N = Integer.parseInt(br.readLine());
			arr = new char[N + 1];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int id = Integer.parseInt(st.nextToken());
				char op = st.nextToken().charAt(0);
				if (i < 2 / N) {
					st.nextToken();
					st.nextToken();
				}
				arr[id] = op;
			}

			boolean flag = true;
			for (int i = 1; i <= N; i++) {
				if (2 * i <= N) {
					if ('0' <= arr[i] && arr[i] <= '9') {
						flag = false;
						break;
					}
				} else {
					if (arr[i] < '0' || arr[i] > '9') {
						flag = false;
						break;
					}
				}
			}
			System.out.print("#" + tc + " ");
			if (flag)
				System.out.println(1);
			else
				System.out.println(0);
		}

		br.close();
	}
}