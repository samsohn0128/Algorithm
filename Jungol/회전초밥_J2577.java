package Jungol;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class 회전초밥_J2577 {
	static int N, D, K, C;
	static LinkedList<Integer> list = new LinkedList<>(), firstK = new LinkedList<>();
	static int[] cnt = new int[3001];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		int ans = 0;
		int ansCnt = 0;
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			if (firstK.size() < K) {
				firstK.add(num);
			}

			if (list.size() < K) {
				list.add(num);
				if (++cnt[num] == 1) {
					ansCnt++;
				}
			} else {
				int removeNum = list.poll();
				if (--cnt[removeNum] == 0) {
					ansCnt--;
				}

				list.add(num);
				if (++cnt[num] == 1) {
					ansCnt++;
				}
			}
			ans = cnt[C] == 0 ? Math.max(ans, ansCnt + 1) : Math.max(ans, ansCnt);
		}
		while (!firstK.isEmpty()) {
			int num = firstK.poll();
			int removeNum = list.poll();
			if (--cnt[removeNum] == 0) {
				ansCnt--;
			}

			list.add(num);
			if (++cnt[num] == 1) {
				ansCnt++;
			}
			ans = cnt[C] == 0 ? Math.max(ans, ansCnt + 1) : Math.max(ans, ansCnt);
		}
		System.out.println(ans);
	}
}