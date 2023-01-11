import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 특식배부_B27110 {

    private static int N;
    private static int A, B, C;

    public static void main(String[] args) throws Exception {
        init();
        String answer = solution();
        System.out.println(answer);
    }

    private static void init() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        br.close();
    }

    private static String solution() {
        StringBuilder sb = new StringBuilder();
        int answer = Math.min(N, A) + Math.min(N, B) + Math.min(N, C);
        sb.append(answer);
        return sb.toString();
    }

}