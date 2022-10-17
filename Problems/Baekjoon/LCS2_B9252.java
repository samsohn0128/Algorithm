import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class LCS2_B9252 {

    private static String inputStr1, inputStr2;
    private static int[][] dp;

    public static void main(String[] args) throws Exception {
        init();
        String answer = solution();
        System.out.println(answer);
    }

    private static void init() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        inputStr1 = br.readLine();
        inputStr2 = br.readLine();
        dp = new int[inputStr1.length()][inputStr2.length()];
        initDp();
    }

    private static void initDp() {
        int same = 0;
        for (int i = 0; i < inputStr2.length(); i++) {
            if (inputStr1.charAt(0) == inputStr2.charAt(i)) {
                same = 1;
            }
            dp[0][i] = same;
        }

        same = 0;
        for (int i = 0; i < inputStr1.length(); i++) {
            if (inputStr1.charAt(i) == inputStr2.charAt(0)) {
                same = 1;
            }
            dp[i][0] = same;
        }
    }

    private static String solution() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < inputStr1.length(); i++) {
            for (int j = 1; j < inputStr2.length(); j++) {
                if (inputStr1.charAt(i) == inputStr2.charAt(j)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    if (dp[i - 1][j] >= dp[i][j - 1]) {
                        dp[i][j] = dp[i - 1][j];
                    } else {
                        dp[i][j] = dp[i][j - 1];
                    }
                }
            }
        }
        int answerLength = dp[inputStr1.length() - 1][inputStr2.length() - 1];
        sb.append(answerLength);
        if (answerLength > 0) {
            sb.append("\n");
            appendAnswerString(sb, inputStr1.length() - 1, inputStr2.length() - 1);
        }
        return sb.toString();
    }

    private static void appendAnswerString(StringBuilder sb, int i, int j) {
        if (dp[i][j] == 0) {
            return;
        } else if (i == 0) {
            sb.append(inputStr1.charAt(i));
            return;
        } else if (j == 0) {
            sb.append(inputStr2.charAt(j));
            return;
        }
        if (dp[i][j] == dp[i - 1][j - 1]) {
            appendAnswerString(sb, i - 1, j - 1);
        } else if (dp[i][j] == dp[i][j - 1]) {
            appendAnswerString(sb, i, j - 1);
        } else if (dp[i][j] == dp[i - 1][j]) {
            appendAnswerString(sb, i - 1, j);
        } else {
            appendAnswerString(sb, i - 1, j - 1);
            sb.append(inputStr1.charAt(i));
        }
    }
}