package Baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 교수님은기다리지않는다_B3830 {

	static int N, M;
	static int[] root = new int[100001];
	static long[] dist = new long[100001];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		while (true) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			if (N == 0 && M == 0)
				break;
			for (int i = 1; i <= N; i++) {
				root[i] = i;
				dist[i] = 0;
			}
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				char c = st.nextToken().charAt(0);
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				if (c == '!') {
					long w = Long.parseLong(st.nextToken());
					union(a, b, w);
				} else if (c == '?') {
					int r1 = find(a);
					int r2 = find(b);

					if (r1 != r2)
						System.out.println("UNKNOWN");
					else
						System.out.println(dist[a] - dist[b]);
				}
			}
		}

		br.close();
	}

	static void union(int n1, int n2, long w) {
		int r1 = find(n1);
		int r2 = find(n2);
		if (r1 != r2) {
			root[r1] = r2;
			long newDist = dist[n2] + w;
			long originDist = dist[n1];
			dist[r1] = newDist - originDist;
		}
	}

	static int find(int n) {
		if (root[n] == n)
			return n;

		int ret = find(root[n]);
		dist[n] += dist[root[n]];
		return root[n] = ret;
	}
}