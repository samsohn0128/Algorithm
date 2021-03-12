package Baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 연길이의이상형_B20540 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] input = br.readLine().toCharArray();
		for (int i = 0; i < input.length; i++) {
			switch(input[i]) {
			case 'E':
				System.out.print('I');
				break;
			case 'I':
				System.out.print('E');
				break;
			case 'S':
				System.out.print('N');
				break;
			case 'N':
				System.out.print('S');
				break;
			case 'T':
				System.out.print('F');
				break;
			case 'F':
				System.out.print('T');
				break;
			case 'J':
				System.out.print('P');
				break;
			case 'P':
				System.out.print('J');
				break;
			}
		}
		br.close();
	}
}