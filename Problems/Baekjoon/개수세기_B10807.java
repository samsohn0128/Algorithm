import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 개수세기_B10807 {
    private static BufferedReader br;
    private static int N;
    private static int[] numbers;
    private static int V;

    public static void main(String[] args) throws Exception {
        init();
        String answer = solution();
        System.out.println(answer);
    }

    private static void init() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        numbers = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        V = Integer.parseInt(br.readLine());

        br.close();
    }

    private static String solution() {
        StringBuilder sb = new StringBuilder();
        long count = Arrays.stream(numbers).filter(num -> num == V).count();
        sb.append(count);
        return sb.toString();
    }

}