package Study;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ExtendedEuclideanAlgorithm {

	static int T;
	static long K, C;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());
			K = Long.parseLong(st.nextToken());
			C = Long.parseLong(st.nextToken());

			if (C == 1) {
				if (K + 1 > 1000000000)
					System.out.println("IMPOSSIBLE");
				else
					System.out.println(K + 1);
			} else if (K == 1) {
				System.out.println(1);
			} else if (gcd(K, C) != 1) {
				System.out.println("IMPOSSIBLE");
			} else {
				long ret = extended_gcd(K, C);
				if (ret > 1000000000)
					System.out.println("IMPOSSIBLE");
				else
					System.out.println(ret);
			}

		}

		br.close();
	}

	static long gcd(long a, long b) {
		return b == 0 ? a : gcd(b, a % b);
	}

	static long extended_gcd(long a, long b) {
		long r, q, tmpA = a, t, t1 = 0, t2 = 1;
		while (b != 0) {
			q = a / b;
			r = a % b;
			t = t1 - q * t2;
			a = b;
			b = r;
			t1 = t2;
			t2 = t;
		}
		while (t1 < 0)
			t1 += tmpA;
		return t1;
	}
}