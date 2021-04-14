import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int T;
	static int ans;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("sample_input.txt"));
//		System.out(new PrintStream("sample_output.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			System.out.println("#" + tc + " " + ans);
		}
	}
}