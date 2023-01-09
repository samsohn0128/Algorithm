import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 검증수_B2475 {
    private static int[] numbers;

    public static void main(String[] args) throws Exception {
        init();
        String answer = solution();
        System.out.println(answer);
    }

    private static void init() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        numbers = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        br.close();
    }

    private static String solution() {
        StringBuilder sb = new StringBuilder();
        int answer = 0;
        for (int number : numbers) {
            answer += number * number;
        }
        sb.append(answer % 10);
        return sb.toString();
    }

}