import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class 알고리즘수업_피보나치수1_B24416 {
    private static int number;

    private static int recursionCount, dpCount;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        init(br);
        String answer = solution();
        System.out.println(answer);
    }

    private static void init(BufferedReader br) throws Exception {
        number = Integer.parseInt(br.readLine());
    }

    private static String solution() {
        countRecursion(number);
        countDp(number);
        StringBuilder sb = new StringBuilder();
        sb.append(recursionCount);
        sb.append(" ");
        sb.append(dpCount);
        return sb.toString();
    }

    private static int countRecursion(int number) {
        if (number == 1 || number == 2) {
            recursionCount++;
            return 1;
        } else {
            return countRecursion(number - 1) + countRecursion(number - 2);
        }
    }

    private static int countDp(int number) {
        int[] fibonacci = new int[number + 1];
        fibonacci[1] = 1;
        fibonacci[2] = 1;
        for (int i = 3; i <= number; i++) {
            fibonacci[i] = fibonacci[i - 1] + fibonacci[i - 2];
            dpCount++;
        }
        return fibonacci[number];
    }
}