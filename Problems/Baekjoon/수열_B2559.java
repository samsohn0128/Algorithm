import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 수열_B2559 {

    private static int numberOfDays;
    private static int numberOfContinuousDays;
    private static int[] temperatures;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        init(br);
        int answer = solution();
        System.out.println(answer);
    }

    private static void init(BufferedReader br) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        numberOfDays = Integer.parseInt(st.nextToken());
        numberOfContinuousDays = Integer.parseInt(st.nextToken());

        temperatures = new int[numberOfDays];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < numberOfDays; i++) {
            temperatures[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static int solution() {
        int sum = 0;
        for (int i = 0; i < numberOfContinuousDays; i++) {
            sum += temperatures[i];
        }

        int maxSum = sum;
        for (int i = numberOfContinuousDays; i < numberOfDays; i++) {
            sum -= temperatures[i - numberOfContinuousDays];
            sum += temperatures[i];
            maxSum = Math.max(maxSum, sum);
        }
        return maxSum;
    }
}