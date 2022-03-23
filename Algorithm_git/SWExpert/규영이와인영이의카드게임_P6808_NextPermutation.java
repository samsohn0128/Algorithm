package SWExpert;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 규영이와인영이의카드게임_P6808_NextPermutation {
	static int T;
	static boolean[] card = new boolean[19];
	static int[] gyuCard = new int[9];
	static int[] inCard = new int[9];
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
			
			int inCardIdx = 0;
			for (int i = 1; i <= 18; i++)
				if (!card[i])
					inCard[inCardIdx++] = i;

			while (true) {
				int ret = chk();
				if (ret > 0)
					ansWin++;
				else if (ret < 0)
					ansLose++;
				
				if (!nextPerm())
					break;
			}

			System.out.println("#" + tc + " " + ansWin + " " + ansLose);
			ansWin = 0;
			ansLose = 0;
			Arrays.fill(card, false);
		}

		br.close();
	}

	static boolean nextPerm() {
		int i = inCard.length - 1;
		while (i > 0 && inCard[i - 1] >= inCard[i])
			--i;

		if (i == 0)
			return false;

		int j = inCard.length - 1;
		while (inCard[i - 1] >= inCard[j])
			--j;
		swap(inCard, i - 1, j);

		int k = inCard.length - 1;
		while (i < k)
			swap(inCard, i++, k--);

		return true;
	}

	static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	static int chk() {
		int cnt = 0;
		for (int i = 0; i < 9; i++) {
			if (gyuCard[i] > inCard[i])
				cnt += gyuCard[i] + inCard[i];
			else if (gyuCard[i] < inCard[i])
				cnt -= gyuCard[i] + inCard[i];
		}
		return cnt;
	}
}