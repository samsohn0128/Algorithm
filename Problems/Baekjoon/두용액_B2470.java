import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 두용액_B2470 {

    private static int numberOfSolution;

    private static int[] solutions;

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
        numberOfSolution = Integer.parseInt(br.readLine());

        solutions = new int[numberOfSolution];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < numberOfSolution; i++) {
            solutions[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static String solution() {
        int[] answerSolutions = new int[2];
        int minSum = Integer.MAX_VALUE;
        Arrays.sort(solutions);
        int left = 0;
        int right = numberOfSolution - 1;
        while (left < right) {
            int sum = solutions[left] + solutions[right];
            if (Math.abs(sum) < minSum) {
                minSum = Math.abs(sum);
                answerSolutions[0] = solutions[left];
                answerSolutions[1] = solutions[right];
            } else if (sum < 0) {
                left++;
            } else {
                right--;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(answerSolutions[0]);
        sb.append(" ");
        sb.append(answerSolutions[1]);
        return sb.toString();
    }
}