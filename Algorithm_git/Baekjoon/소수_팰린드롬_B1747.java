package Baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 소수_팰린드롬_B1747 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int num = Integer.parseInt(br.readLine());
		while (true) {
			if (isPrime(num) && chk(num)) {
				break;
			}
			num++;
		}

		System.out.println(num);
		br.close();
	}

	static boolean isPrime(int num) {
		if(num == 1) return false;
		for (int i = 2; i * i <= num; i++) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}

	static boolean chk(int num) {
		char[] input = Integer.toString(num).toCharArray();
		int idx1 = 0;
		int idx2 = input.length - 1;

		while (idx1 < idx2) {
			if (input[idx1++] != input[idx2--])
				return false;
		}
		return true;
	}
}