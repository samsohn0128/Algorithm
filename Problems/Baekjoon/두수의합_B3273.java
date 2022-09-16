import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 두수의합_B3273 {

    private static int numberOfNumbers;
    private static int[] numbers;
    private static int targetNumber;

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
        numberOfNumbers = Integer.parseInt(br.readLine());

        numbers = new int[numberOfNumbers];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < numberOfNumbers; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        targetNumber = Integer.parseInt(br.readLine());
    }

    private static int solution() {
        Arrays.sort(numbers);
        int left = 0;
        int right = numberOfNumbers - 1;
        int count = 0;
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == targetNumber) {
                count++;
                right--;
            } else if (sum < targetNumber) {
                left++;
            } else {
                right--;
            }
        }
        return count;
    }
}