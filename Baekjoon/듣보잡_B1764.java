package Baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class 듣보잡_B1764 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		Set<String> s = new HashSet<>();
		ArrayList<String> ans = new ArrayList<>();

		String input;
		for (int i = 0; i < N; i++) {
			input = br.readLine();
			s.add(input);
		}
		for (int i = 0; i < M; i++) {
			input = br.readLine();
			if (s.contains(input)) {
				ans.add(input);
			}
		}
		Collections.sort(ans);

		System.out.println(ans.size());
		for (String str : ans) {
			System.out.println(str);
		}
		
		br.close();
	}
}
