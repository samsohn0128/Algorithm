package Baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 카드게임_B2621 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		char[] color = new char[5];
		int[] num = new int[5];

		for (int i = 0; i < 5; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			color[i] = st.nextToken().charAt(0);
			num[i] = Integer.parseInt(st.nextToken());
		}

		int[] numTemp = Arrays.copyOf(num, 5);
		Arrays.sort(numTemp);

		// 9
		int ans = numTemp[4] + 100;

		// 8, 7
		int[] cnt = new int[5];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (num[i] == num[j])
					cnt[i]++;
			}
		}

		int num1 = 0;
		int num2 = 0;
		int num3 = 0;
		int num4 = 0;
		for (int i = 0; i < 5; i++) {
			if (cnt[i] == 2) {
				if (num1 == 0) {
					num1 = num[i];
				} else if (num1 != num[i]) {
					num2 = num[i];
				}
			}
			if (cnt[i] == 3)
				num3 = num[i];
			if (cnt[i] == 4)
				num4 = num[i];
		}
		// 8
		if (num1 != 0)
			ans = num1 + 200;
		// 7
		if (num2 != 0)
			ans = num1 > num2 ? 10 * num1 + num2 + 300 : 10 * num2 + num1 + 300;

		// 6
		if (num3 != 0)
			ans = 400 + num3;

		// 5
		int firstNum = numTemp[0];
		boolean chkStraight = true;
		for (int i = 0; i < 5; i++) {
			if (numTemp[i] != firstNum++) {
				chkStraight = false;
				break;
			}
		}
		if (chkStraight)
			ans = 500 + numTemp[4];

		// 4
		boolean chkFlush = true;
		char firstColor = color[0];
		for (int i = 0; i < 5; i++) {
			if (color[i] != firstColor) {
				chkFlush = false;
				break;
			}
		}
		if (chkFlush)
			ans = 600 + numTemp[4];

		// 3
		if (num3 != 0) {
			if (num1 != 0 || num2 != 0) {
				if (num1 != num3) {
					ans = 10 * num3 + num1 + 700;
				} else if (num2 != num3) {
					ans = 10 * num3 + num2 + 700;
				}
			}
		}

		// 2
		if (num4 != 0) {
			ans = 800 + num4;
		}

		// 1
		if (chkStraight && chkFlush)
			ans = numTemp[4] + 900;
		System.out.println(ans);
		br.close();
	}
}