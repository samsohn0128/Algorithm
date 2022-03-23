package Baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 도영이가만든맛있는음식_B2961 {

	static int N;
	static Node[] grocery;
	static Node[] food;
	static long ans = Long.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		grocery = new Node[N + 1];
		food = new Node[N + 1];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			grocery[i] = new Node(i, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		comb(0, 0);
		System.out.println(ans);

		br.close();
	}

	static void comb(int srcIdx, int tgtIdx) {
		if (tgtIdx != 0)
			ans = Math.min(ans, chk(tgtIdx));
		if (srcIdx == N)
			return;

		comb(srcIdx + 1, tgtIdx);
		food[tgtIdx] = grocery[srcIdx];
		comb(srcIdx + 1, tgtIdx + 1);
	}

	static void perm(int idx, int mask) {
		ans = Math.min(ans, chk(idx));

		if (idx == N)
			return;

		for (int i = 0; i < N; i++) {
			if ((mask & 1 << i) != 0)
				continue;
			food[idx] = grocery[i];
			perm(idx + 1, mask | 1 << i);
		}
	}

	static long chk(int n) {
		long sour = 1;
		long bitter = 0;
		for (int i = 0; i < n; i++) {
			sour *= food[i].sour;
			bitter += food[i].bitter;
		}
		return Math.abs(sour - bitter);
	}

	static class Node {
		int id;
		long sour;
		long bitter;

		public Node(int id, int sour, int bitter) {
			this.id = id;
			this.sour = sour;
			this.bitter = bitter;
		}

		@Override
		public String toString() {
			return "sour=" + sour + ", bitter=" + bitter;
		}

	}
}