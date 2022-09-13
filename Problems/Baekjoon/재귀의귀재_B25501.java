import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class 재귀의귀재_B25501 {

    private static int numberOftestCases;
    private static String[] inputStrings;

    private static int count;

    public static void main(String[] args) throws Exception {
        BufferedReader br = setBufferedReader();
        init(br);
        String answer = solution();
        System.out.println(answer);
        br.close();
    }

    private static BufferedReader setBufferedReader() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        return new BufferedReader(new InputStreamReader(System.in));
    }

    private static void init(BufferedReader br) throws Exception {
        numberOftestCases = Integer.parseInt(br.readLine());
        inputStrings = new String[numberOftestCases];
        for (int i = 0; i < numberOftestCases; i++) {
            inputStrings[i] = br.readLine();
        }
    }

    private static String solution() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numberOftestCases; i++) {
            count = 0;
            int palindrome = isPalindrome(inputStrings[i]);
            sb.append(palindrome);
            sb.append(" ");
            sb.append(count);
            sb.append("\n");
        }
        return sb.toString();
    }

    private static int isPalindrome(String str) {
        return recursion(str, 0, str.length() - 1);
    }

    private static int recursion(String str, int left, int right) {
        count++;
        if (left >= right) return 1;
        else if (str.charAt(left) != str.charAt(right)) return 0;
        else return recursion(str, left + 1, right - 1);
    }
}