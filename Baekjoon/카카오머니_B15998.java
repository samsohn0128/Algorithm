package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 카카오머니_B15998 {

	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		long curBalance = 0;
		long ans = 0;
		long minBalance = Long.MAX_VALUE;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			long money = Long.parseLong(st.nextToken());
			long balance = Long.parseLong(st.nextToken());
			if (money > 0) {
				if (curBalance + money != balance) {
					ans = -1;
					break;
				}
			} else {
				long recharge = -money + balance - curBalance;

				if (curBalance + money >= 0) {
					if (recharge != 0) {
						ans = -1;
						break;
					}
				} else {
					if (recharge <= 0) {
						ans = -1;
						break;
					}

					ans = gcd(ans, recharge);
//					System.out.println("ans : " + ans + " balance : " + balance);
					if (balance != 0 && balance < minBalance) {
						minBalance = balance;
					}

					if (minBalance != Long.MAX_VALUE && ans <= minBalance) {
						ans = -1;
						break;
					}

					if (ans == 1 && balance != 0) {
						ans = -1;
						break;
					}
				}
			}
			curBalance = balance;
		}
		if (ans == 0)
			ans = 1;
		System.out.println(ans);
	}

	static long gcd(long a, long b) {
		return b != 0 ? gcd(b, a % b) : a;
	}
}