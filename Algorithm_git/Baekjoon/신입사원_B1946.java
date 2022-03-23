package Baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 신입사원_B1946{
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			int N = Integer.parseInt(br.readLine());
			Applier[] appliers = new Applier[N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				appliers[i] = new Applier(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			Arrays.sort(appliers, (a1, a2) -> a1.docRank - a2.docRank);

			int min = appliers[0].interviewRank;
			int ans = N;
			for (int i = 1; i < N; i++) {
				if (min < appliers[i].interviewRank) {
					ans--;
				} else {
					min = appliers[i].interviewRank;
				}
			}

			System.out.println(ans);
		}

		br.close();
	}

	static class Applier {
		int docRank;
		int interviewRank;

		public Applier(int docScore, int interviewScore) {
			this.docRank = docScore;
			this.interviewRank = interviewScore;
		}

		@Override
		public String toString() {
			return "[docRank=" + docRank + ", interviewRank=" + interviewRank + "]";
		}

	}
}
