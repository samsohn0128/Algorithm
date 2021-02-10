package Baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 순열_B9742 {

	static int COUNT;
	static char[] input;
	static int inputLength;
	static char[] tgt;
	static boolean[] selected;
	static int num;
	static char[] ans;
	static int[] fact = new int[11];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		fact[1] = 1;
		for (int i = 2; i < 11; i++)
			fact[i] = fact[i - 1] * i;

		String str = br.readLine();
		while (str != null) {
			StringTokenizer st = new StringTokenizer(str);
			input = st.nextToken().toCharArray();
			inputLength = input.length;
			num = Integer.parseInt(st.nextToken());
			selected = new boolean[inputLength];
			tgt = new char[inputLength];
			ans = new char[inputLength];
			COUNT = 0;

			if (fact[input.length] < num) {
				System.out.println(str + " = No permutation");
			} else {
				perm(0);
				System.out.print(str + " = ");
				for (int i = 0; i < ans.length; i++) {
					System.out.print(ans[i]);
				}
				System.out.println();
			}

			str = br.readLine();
		}

		br.close();
	}

	public static void perm(int tgtIdx) {
		if (tgtIdx == inputLength) {
			if (++COUNT == num)
				ans = Arrays.copyOf(tgt, inputLength);
			return;
		}

		for (int i = 0; i < inputLength; i++) {
			if (selected[i])
				continue;

			tgt[tgtIdx] = input[i];
			selected[i] = true;
			perm(tgtIdx + 1);
			selected[i] = false;
		}
	}
}