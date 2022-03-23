package SWExpert;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 규영이와인영이의카드게임_P6808_BasicPermutation {
	static int T;
	static boolean[] card = new boolean[19];
	static int[] gyuCard = new int[9];
	static int[] inCard = new int[9];
	static int[] tgt = new int[9];
	static boolean[] visited = new boolean[9];
	static int ansWin, ansLose;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력을 받기위한 BufferedReader
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 9; i++) {
				gyuCard[i] = Integer.parseInt(st.nextToken());
				card[gyuCard[i]] = true;
			}
			int srcIdx = 0;
			for (int i = 1; i <= 18; i++) {
				if (!card[i])
					inCard[srcIdx++] = i;
			}

			perm(0);
			System.out.println("#" + tc + " " + ansWin + " " + ansLose);
			ansWin = 0;
			ansLose = 0;
			Arrays.fill(card, false);
		}

		br.close();
	}

	static void perm(int idx) {
		if (idx == 9) {
			int ret = chk();
			if (ret > 0)
				ansWin++;
			else if (ret < 0)
				ansLose++;
			return;
		}

		for (int i = 0; i < 9; i++) {
			if (visited[i])
				continue;

			tgt[idx] = inCard[i];
			visited[i] = true;
			perm(idx + 1);
			visited[i] = false;
		}
	}

	static int chk() {
		int cnt = 0;
		for (int i = 0; i < 9; i++) {
			if (gyuCard[i] > tgt[i])
				cnt += gyuCard[i] + tgt[i];
			else if (gyuCard[i] < tgt[i])
				cnt -= gyuCard[i] + tgt[i];
		}
		return cnt;
	}
}