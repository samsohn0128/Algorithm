package SWExpert;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Stack;

public class 괄호짝짓기_P1218 {
	
	static int T;
	static int N;
	static char[] brackets;
	static Stack<Character> bracketStack = new Stack<>();
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt")); // input.txt로부터 입력을 받아옴
		System.setOut(new PrintStream("myOutput.txt")); // myOutput.txt로 정답을 출력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력을 받기위한 BufferedReader

		T = 10;
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			brackets = br.readLine().toCharArray();
			bracketStack.clear();
			boolean ans = true;
			for (int i = 0; i < N; i++) {
				if (brackets[i] == '(' || brackets[i] == '[' || brackets[i] == '{' || brackets[i] == '<') {
					bracketStack.push(brackets[i]);
				} else if (brackets[i] == ')') {
					if (bracketStack.peek() == '(')
						bracketStack.pop();
					else {
						ans = false;
						break;
					}
				} else if (brackets[i] == ']') {
					if (bracketStack.peek() == '[')
						bracketStack.pop();
					else {
						ans = false;
						break;
					}
				} else if (brackets[i] == '}') {
					if (bracketStack.peek() == '{')
						bracketStack.pop();
					else {
						ans = false;
						break;
					}
				} else if (brackets[i] == '>') {
					if (bracketStack.peek() == '<')
						bracketStack.pop();
					else {
						ans = false;
						break;
					}
				}
			}
			if (ans)
				System.out.println("#" + tc + " " + "1");
			else
				System.out.println("#" + tc + " " + "0");
		}

		br.close();
	}
}