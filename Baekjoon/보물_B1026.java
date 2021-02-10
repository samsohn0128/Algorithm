package Baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 보물_B1026 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N];
		int[] B = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			A[i] = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			B[i] = Integer.parseInt(st.nextToken());

		int ans = 0;
		for (int i = 0; i < N; i++) {
			int max = -1;
			int maxIdx = -1;
			int min = 101;
			int minIdx = -1;
			for (int j = 0; j < N; j++) {
				if (max < B[j]) {
					maxIdx = j;
					max = B[j];
				}
				if (min > A[j]) {
					minIdx = j;
					min = A[j];
				}
			}
			B[maxIdx] = -1;
			A[minIdx] = 101;
			ans += max * min;
		}
		System.out.println(ans);
		br.close();
	}
}
