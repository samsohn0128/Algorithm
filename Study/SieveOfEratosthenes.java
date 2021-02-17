package Study;

public class SieveOfEratosthenes {

	static final int MAX = 1001;
	static int[] minFactor = new int[MAX];
	static int N, K;

	public static void main(String[] args) throws Exception {
		N = 100;
		eratosthenes();
		for (int i = 2; i <= N; i++) {
			if (minFactor[i] == i)
				System.out.println(i);
		}
	}

	static void eratosthenes() {
		minFactor[0] = -1;
		minFactor[1] = -1;
		for (int i = 2; i <= N; i++)
			minFactor[i] = i;

		for (int i = 2; i <= N; i++) {
			if (minFactor[i] == i) {
				for (int j = i * i; j <= N; j += i) {
					if (minFactor[j] == j) {
						minFactor[j] = i;
					}
				}
			}
		}
	}
}