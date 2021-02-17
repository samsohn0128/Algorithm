package Baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Z_B1074 {

	static int N, r, c;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		recur(0, (int) Math.pow(2, N), 0, (int) Math.pow(2, N), 0);
		System.out.println(ans);
		br.close();
	}

	static int ans;

	static void recur(int rs, int re, int cs, int ce, int sum) {
		if (re - rs == 2 && ce - cs == 2) {
			if (r == rs) {
				if (c == cs) {
					ans = sum;
				} else {
					ans = sum + 1;
				}
			} else {
				if (c == cs) {
					ans = sum + 2;
				} else {
					ans = sum + 3;
				}
			}
			return;
		}

		int rmid = (rs + re) / 2;
		int cmid = (cs + ce) / 2;
		if (r < rmid) {
			if (c < cmid) {
				recur(rs, rmid, cs, cmid, sum);
			} else if (c >= cmid) {
				recur(rs, rmid, cmid, ce, sum + (rmid - rs) * (cmid - cs));
			}
		} else if (r >= rmid) {
			if (c < cmid) {
				recur(rmid, re, cs, cmid, sum + 2 * (rmid - rs) * (cmid - cs));
			} else if (c >= cmid) {
				recur(rmid, re, cmid, ce, sum + 3 * (rmid - rs) * (cmid - cs));
			}
		}
	}

}