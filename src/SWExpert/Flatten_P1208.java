package SWExpert;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

//SWExpert 1208번 덤핑 밸런스 맞추기
public class Flatten_P1208 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new PrintWriter(System.out));
		for (int tc = 1; tc <= 10; tc++) {
			int dumps = Integer.parseInt(br.readLine());
			int[] boxes = new int[100];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 100; i++) {
				boxes[i] = Integer.parseInt(st.nextToken());
			}
			while (dumps > 0) {
				int minBox = 101;
				int minIndex = -1;
				int maxBox = 0;
				int maxIndex = -1;
				for (int i = 0; i < 100; i++) {
					if (minBox > boxes[i]) {
						minBox = boxes[i];
						minIndex = i;
					}
					if (maxBox < boxes[i]) {
						maxBox = boxes[i];
						maxIndex = i;
					}
				}
				if (maxBox - minBox <= 1) {
					break;
				} else {
					dumps--;
					boxes[maxIndex]--;
					boxes[minIndex]++;
				}
			}
			int maxAns = 0;
			int minAns = 101;
			for (int i = 0; i < 100; i++) {
				if (maxAns < boxes[i])
					maxAns = boxes[i];
				if (minAns > boxes[i])
					minAns = boxes[i];
			}
			int ans = maxAns - minAns;

			StringBuilder sb = new StringBuilder();
			sb.append('#').append(tc).append(' ').append(ans).append('\n');
			bw.write(sb.toString());
		}
		br.close();
		bw.close();
	}
}
