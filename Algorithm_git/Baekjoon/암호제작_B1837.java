package Baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 암호제작_B1837 {

	static char[] P;
	static int K;
	static boolean[] isPrime;
	static boolean ans = true;
	static int ansNum;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		P = st.nextToken().toCharArray();
		K = Integer.parseInt(st.nextToken());
		isPrime = new boolean[K];

		eratosthenes();
		if (ans)
			System.out.println("GOOD");
		else
			System.out.println("BAD " + ansNum);

	}

	static boolean noRemain(int n) {
		int num = 0;
		for (char c : P)
			num = (num * 10 + (c - '0')) % n;

		if (num == 0)
			return true;
		else
			return false;
	}

	static void eratosthenes() {
		for (int i = 2; i < K; i++) {
			isPrime[i] = true;
		}

		for (int i = 2; i < K; i++) {
			if (isPrime[i]) {
				if (noRemain(i)) {
					ans = false;
					ansNum = i;
					break;
				}
				for (int j = i + i; j < K; j += i) {
					isPrime[j] = false;
				}
			}
		}
	}
}