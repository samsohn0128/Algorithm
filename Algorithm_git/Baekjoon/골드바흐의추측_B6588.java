package Baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class 골드바흐의추측_B6588 {

	static final int MAX = 1000000;
	static int[] minFactor = new int[MAX];
	static ArrayList<Integer> prime = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		eratosthenes();
		while (true) {
			int n = Integer.parseInt(br.readLine());
			if (n == 0)
				break;

			for (Integer i : prime) {
				if (minFactor[n - i] == n - i) {
					System.out.println(n + " = " + i + " + " + (n - i));
					break;
				}
			}
		}

		br.close();
	}

	static void eratosthenes() {
		minFactor[0] = minFactor[1] = -1;
		for (int i = 2; i < MAX; i++)
			minFactor[i] = i;

		for (int i = 2; i * i < MAX; i++)
			if (minFactor[i] == i)
				for (int j = i * i; j < MAX; j += i)
					if (minFactor[j] == j)
						minFactor[j] = i;

		for (int i = 3; i < MAX; i++)
			if (minFactor[i] == i)
				prime.add(i);
	}
}