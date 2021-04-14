package SWExpert;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 퍼펙트셔플_P3499 {

	static int T;
	static int N;
	static String[] input;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력을 받기위한 BufferedReader
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			input = new String[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++)
				input[i] = st.nextToken();

			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < N / 2; i++) {
				if (N % 2 == 0) {
					sb.append(input[i]).append(" ");
					sb.append(input[N / 2 + i]).append(" ");
				} else {
					sb.append(input[i]).append(" ");
					sb.append(input[N / 2 + i + 1]).append(" ");
				}
			}
			if (N % 2 == 1) {
				sb.append(input[N / 2]);
			}
			System.out.print("#" + tc + " ");
			System.out.println(sb);
		}

		br.close();
	}
}