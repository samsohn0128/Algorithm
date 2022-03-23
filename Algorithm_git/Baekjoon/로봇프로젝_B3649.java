package Baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 로봇프로젝_B3649 {

	static int W, N;
	static int[] legos;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while (true) {
			String str = br.readLine();
			if(str == null)
				break;
			W = Integer.parseInt(str);
			N = Integer.parseInt(br.readLine());
			W *= 10000000;
			legos = new int[N];

			for (int i = 0; i < N; i++)
				legos[i] = Integer.parseInt(br.readLine());
			Arrays.sort(legos);

			int idx1 = 0;
			int idx2 = N - 1;
			while (idx1 < idx2) {
				if (legos[idx1] + legos[idx2] < W) {
					idx1++;
				} else if (legos[idx1] + legos[idx2] > W) {
					idx2--;
				} else {
					break;
				}
			}
			if (idx1 < idx2) {
				System.out.println("yes " + legos[idx1] + " " + legos[idx2]);
			} else {
				System.out.println("danger");
			}

		}
		br.close();
	}
}