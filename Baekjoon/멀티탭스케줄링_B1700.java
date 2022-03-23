package Baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class 멀티탭스케줄링_B1700 {

	static int N, K;
	static int[] electrics;
	static int[][] pos;
	static int[] posIdx;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		electrics = new int[K];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++)
			electrics[i] = Integer.parseInt(st.nextToken());

		initPos();
		getAllPos();
		
		int ans = 0;
		Set<Integer> s = new HashSet<>();

		for (int i = 0; i < K; i++) {
			if (s.contains(electrics[i])) {
				posIdx[electrics[i]]++;
				continue;
			}

			if (s.size() < N) {
				s.add(electrics[i]);
				posIdx[electrics[i]]++;
			} else {
				int maxPos = 0;
				int maxNum = 0;
				for (Integer integer : s) {
					if (maxPos < pos[integer][posIdx[integer]]) {
						maxPos = pos[integer][posIdx[integer]];
						maxNum = integer;
					}
				}
				ans++;
				s.remove(maxNum);
				s.add(electrics[i]);
				posIdx[electrics[i]]++;
			}
		}

		System.out.println(ans);
		br.close();
	}

	static void initPos() {
		pos = new int[K + 1][K + 1];
		for (int i = 1; i <= K; i++)
			Arrays.fill(pos[i], 101);
		posIdx = new int[K + 1];
	}

	static void getAllPos() {
		for (int i = 1; i <= K; i++) {
			int idx = 0;
			int j = 0;

			while (idx < K) {
				while (idx < K && electrics[idx] != i) {
					idx++;
				}
				if (idx < K)
					pos[i][j++] = idx++;
			}

		}
	}

}