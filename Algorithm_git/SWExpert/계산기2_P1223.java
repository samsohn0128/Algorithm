package SWExpert;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class 계산기2_P1223 {

	static int T;
	static int N;
	static char[] input;

	static Stack<Integer> num = new Stack<>();
	static Stack<Character> op = new Stack<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = 10;
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			input = br.readLine().toCharArray();

			for (int i = 0; i < N; i++) {
				if (input[i] == '*') {
					num.push(num.pop() * (input[++i] - '0'));
				} else if (input[i] != '+')
					num.push(input[i] - '0');
			}

			int ans = 0;
			while (!num.isEmpty())
				ans += num.pop();

			System.out.println("#" + tc + " " + ans);
		}

	}
}
