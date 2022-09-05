import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 검문_B2981 {

    private static int numberOfNumbers;
    private static int[] numbers;

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
        numberOfNumbers = Integer.parseInt(br.readLine());

        numbers = new int[numberOfNumbers];
        for (int i = 0; i < numberOfNumbers; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
        }
    }

    private static String solution() {
        Arrays.sort(numbers);
        int gcdValue = setGcdValue();
        int[] divisors = getDivisors(gcdValue);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < divisors.length; i++) {
            sb.append(divisors[i]);
            if (i < divisors.length - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    private static int setGcdValue() {
        int gcdValue = numbers[1] - numbers[0];
        for (int i = 2; i < numberOfNumbers; i++) {
            gcdValue = gcd(gcdValue, numbers[i] - numbers[i - 1]);
        }
        return gcdValue;
    }

    private static int gcd(int a, int b) {
        return a % b != 0 ? gcd(b, a % b) : b;
    }

    private static int[] getDivisors(int gcdValue) {
        List<Integer> divisors = new ArrayList<>();
        for (int i = 2; i <= gcdValue; i++) {
            if (gcdValue % i == 0) {
                divisors.add(i);
            }
        }
        return divisors.stream().mapToInt(i -> i).toArray();
    }
}