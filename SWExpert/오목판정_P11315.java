package SWExpert;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;


//SWExpert 오목판정
public class 오목판정_P11315 {

	static int[] dy = { -1, -1, -1, 0, 1, 1, 1, 0 };
	static int[] dx = { -1, 0, 1, 1, 1, 0, -1, -1 };
	static int[][] grid;
	static int N;

	static boolean recur(int y, int x, int direction, int cnt) {
		if (cnt == 5) {
			return true;
		} else if (direction == 9) {
			for (int i = 0; i < 8; i++) {
				if (y + dy[i] >= 0 && y + dy[i] < N && x + dx[i] >= 0 && x + dx[i] < N) {
					if (grid[y + dy[i]][x + dx[i]] == 1) {
						if (recur(y + dy[i], x + dx[i], i, cnt + 1) == true)
							return true;
					}
				}
			}
		} else {
			if (y + dy[direction] >= 0 && y + dy[direction] < N && x + dx[direction] >= 0 && x + dx[direction] < N) {
				if (grid[y + dy[direction]][x + dx[direction]] == 1) {
					return recur(y + dy[direction], x + dx[direction], direction, cnt + 1);
				}
			}
		}
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new PrintWriter(System.out));

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			grid = new int[N][N];

			for (int i = 0; i < N; i++) {
				char[] temp = br.readLine().toCharArray();
				for (int j = 0; j < N; j++) {
					if (temp[j] == '.')
						grid[i][j] = 0;
					else
						grid[i][j] = 1;
				}
			}
			boolean ans = false;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (grid[i][j] == 1) {
						if (recur(i, j, 9, 1) == true) {
							ans = true;
							break;
						}
					}
				}
				if(ans)
					break;
			}

			StringBuilder sb = new StringBuilder();
			sb.append('#').append(tc).append(' ');
			if (ans)
				sb.append("YES");
			else
				sb.append("NO");
			sb.append('\n');
			bw.write(sb.toString());
		}
		br.close();
		bw.close();
	}
}
