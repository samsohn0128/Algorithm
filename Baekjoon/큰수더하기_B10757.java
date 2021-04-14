package Baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class 큰수더하기_B10757 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		String A = st.nextToken();
		String B = st.nextToken();
		int indexA = A.length() - 1;
		int indexB = B.length() - 1;

		Stack<Integer> s = new Stack<>();

		boolean flag = false;
		while (indexA >= 0 && indexB >= 0) {
			int a = A.charAt(indexA--) - '0';
			int b = B.charAt(indexB--) - '0';
			int sum = flag ? a + b + 1 : a + b;

			if (sum >= 10) {
				flag = true;
				sum -= 10;
			} else {
				flag = false;
			}
			s.add(sum);
		}
		while (indexA >= 0) {
			int a = A.charAt(indexA--) - '0';
			int sum = flag ? a + 1 : a;

			if (sum >= 10) {
				flag = true;
				sum -= 10;
			} else {
				flag = false;
			}
			s.add(sum);
		}
		while (indexB >= 0) {
			int b = B.charAt(indexB--) - '0';
			int sum = flag ? b + 1 : b;

			if (sum >= 10) {
				flag = true;
				sum -= 10;
			} else {
				flag = false;
			}
			s.add(sum);
		}
		if (flag)
			s.add(1);

		StringBuilder ans = new StringBuilder();

		while (!s.isEmpty()) {
			ans.append(s.pop());
		}

		System.out.println(ans);

		br.close();
	}
}
