import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class 과제안내신분_B5597 {
    private static BufferedReader br;
    private static final int NUMBER_OF_STUDENTS = 30;
    private static boolean[] submitted = new boolean[NUMBER_OF_STUDENTS + 1];

    public static void main(String[] args) throws Exception {
        init();
        String answer = solution();
        System.out.println(answer);
    }

    private static void init() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < NUMBER_OF_STUDENTS - 2; i++) {
            int num = Integer.parseInt(br.readLine());
            submitted[num] = true;
        }

        br.close();
    }

    private static String solution() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= NUMBER_OF_STUDENTS; i++) {
            if (!submitted[i]) {
                sb.append(i);
                sb.append('\n');
            }
        }
        return sb.toString();
    }

}