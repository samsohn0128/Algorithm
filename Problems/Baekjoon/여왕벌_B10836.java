import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 여왕벌_B10836 {
    private static BufferedReader br;
    private static int M, N;
    private static int[] larvae;

    public static void main(String[] args) throws Exception {
        init();
        String answer = solution();
        System.out.println(answer);
    }

    private static void init() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        larvae = new int[2 * M - 1];
        Arrays.fill(larvae, 1);

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int count0 = Integer.parseInt(st.nextToken());
            int count1 = Integer.parseInt(st.nextToken());
            int count2 = Integer.parseInt(st.nextToken());
            for (int j = count0; j < count0 + count1; j++) {
                larvae[j]++;
            }
            for (int j = count0 + count1; j < count0 + count1 + count2; j++) {
                larvae[j] += 2;
            }
        }
        br.close();
    }

    private static String solution() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            sb.append(larvae[M - i - 1]).append(' ');
            for (int j = 1; j < M; j++) {
                sb.append(larvae[M + j - 1]).append(' ');
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}