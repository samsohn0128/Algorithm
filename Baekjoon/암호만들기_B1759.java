package Baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 암호만들기_B1759 {

	static int L, C;
	static char[] pwd;
	static char[] ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		ans = new char[L];
		pwd = new char[C];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			pwd[i] = st.nextToken().charAt(0);
		}
		Arrays.sort(pwd);
		recur(0, 0);
		br.close();
	}

	static void recur(int idxAns, int idxPwd) {
		if (idxAns == L) {
			boolean chk = false;
			int cnt = 0;
			for (int i = 0; i < L; i++) {
				if (ans[i] == 'a' || ans[i] == 'e' || ans[i] == 'i' || ans[i] == 'o' || ans[i] == 'u') {
					chk = true;
				} else
					cnt++;
			}
			if (chk && cnt > 1) {
				for (int i = 0; i < L; i++) {
					System.out.print(ans[i]);
				}
				System.out.println();
			}
			return;
		} else if (idxPwd == C) {
			return;
		}

		ans[idxAns] = pwd[idxPwd];
		recur(idxAns + 1, idxPwd + 1);
		recur(idxAns, idxPwd + 1);
	}

}
