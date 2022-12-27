import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 최댓값_B2566 {
    private static BufferedReader br;
    private static final int LENGTH = 9;
    private static int[] max = new int[3];

    public static void main(String[] args) throws Exception {
        init();
        String answer = solution();
        System.out.println(answer);
    }

    private static void init() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for (int i = 0; i < LENGTH; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < LENGTH; j++) {
                int number = Integer.parseInt(st.nextToken());
                if (max[0] < number) {
                    max[0] = number;
                    max[1] = i;
                    max[2] = j;
                }
            }
        }
        br.close();
    }

    private static String solution() {
        StringBuilder sb = new StringBuilder();
        sb.append(max[0]);
        sb.append('\n');
        sb.append(max[1] + 1);
        sb.append(' ');
        sb.append(max[2] + 1);

        return sb.toString();
    }

}