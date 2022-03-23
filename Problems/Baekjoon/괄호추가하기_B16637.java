package Baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 괄호추가하기_B16637 {

	static int N;
	static char[] input;
	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		input = br.readLine().toCharArray();
		recur(0, 0);

		System.out.println(max);
		br.close();
	}

	static void recur(int idx, int sum) {
		if (idx >= input.length) {
			max = Math.max(max, sum);
			return;
		}
		
		char op = idx == 0 ? '+' : input[idx - 1];
		
		recur(idx + 2, calculate(sum, input[idx] - '0', op));
		
		if (idx + 2 < input.length) {
			int temp = calculate(input[idx] - '0', input[idx + 2] - '0', input[idx + 1]);
			recur(idx + 4, calculate(sum, temp, op));
		}
	}

	static int calculate(int a, int b, char op) {
		switch (op) {
		case '+':
			return a + b;
		case '*':
			return a * b;
		case '-':
			return a - b;
		default:
			return 0;
		}
	}
}