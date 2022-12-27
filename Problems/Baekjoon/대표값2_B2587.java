import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 대표값2_B2587 {
    private static BufferedReader br;
    private static final int CAPACITY = 5;
    private static int[] numbers = new int[CAPACITY];
    private static int sum = 0;

    public static void main(String[] args) throws Exception {
        init();
        String answer = solution();
        System.out.println(answer);
    }

    private static void init() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < CAPACITY; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
            sum += numbers[i];
        }
        br.close();
    }

    private static String solution() {
        StringBuilder sb = new StringBuilder();
        Arrays.sort(numbers);
        sb.append(sum / CAPACITY);
        sb.append("\n");
        sb.append(numbers[CAPACITY / 2]);
        return sb.toString();
    }

}