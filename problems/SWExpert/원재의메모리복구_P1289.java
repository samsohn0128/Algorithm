package SWExpert;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

//SWExpert 1289번 원재의 메모리 복구
public class 원재의메모리복구_P1289 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			String str = br.readLine();
			int[] memory = new int[str.length()];
			int ans = 0;
			
			for (int i = 0; i < memory.length; i++) {
				if (memory[i] != str.charAt(i) - '0') {
					ans++;
					if (memory[i] == 1) {
						for (int j = i; j < memory.length; j++) {
							memory[j] = 0;
						}
					} else {
						for (int j = i; j < memory.length; j++) {
							memory[j] = 1;
						}
					}
				}
			}
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(tc).append(" ").append(ans).append('\n');
			bw.append(sb.toString());
		}
		br.close();
		bw.close();
	}
}
