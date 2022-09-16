import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 부분합_B1806 {

    private static int numberOfNumbers;
    private static int targetSum;
    private static int[] numbers;

    public static void main(String[] args) throws Exception {
        BufferedReader br = setBufferedReader();
        init(br);
        int answer = solution();
        System.out.println(answer);
        br.close();
    }

    private static BufferedReader setBufferedReader() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        return new BufferedReader(new InputStreamReader(System.in));
    }

    private static void init(BufferedReader br) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        numberOfNumbers = Integer.parseInt(st.nextToken());
        targetSum = Integer.parseInt(st.nextToken());

        numbers = new int[numberOfNumbers];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < numberOfNumbers; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

    }

    private static int solution() {
        int first = 0;
        int second = 0;
        int minLength = Integer.MAX_VALUE;
        int sum = numbers[0];
        while (first < numberOfNumbers && second < numberOfNumbers) {
            if (sum >= targetSum) {
                minLength = Math.min(minLength, second - first + 1);
                sum -= numbers[first++];
            } else {
                if (second + 1 < numberOfNumbers)
                    sum += numbers[++second];
                else break;
            }
        }
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
}