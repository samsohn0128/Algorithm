import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 공넣기_B10810 {

    private static int N, M;
    private static int[][] ballBaskets;
    private static int[] answer;

    public static void main(String[] args) throws Exception {
        init();
        String answer = solution();
        System.out.println(answer);
    }

    private static void init() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ballBaskets = new int[M][3];
        answer = new int[N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                ballBaskets[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        br.close();
    }

    private static String solution() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            for (int j = ballBaskets[i][0]; j <= ballBaskets[i][1]; j++) {
                answer[j - 1] = ballBaskets[i][2];
            }
        }
        Arrays.stream(answer).forEach(num -> sb.append(num + " "));
        return sb.toString();
    }
}