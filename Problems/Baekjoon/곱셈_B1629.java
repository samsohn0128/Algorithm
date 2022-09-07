import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 곱셈_B1629 {

    private static long A, B, C;

    public static void main(String[] args) throws Exception {
        BufferedReader br = setBufferedReader();
        init(br);
        int answer = solution();
        System.out.println(answer);
        br.close();
    }

    private static BufferedReader setBufferedReader() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        return new BufferedReader(new InputStreamReader(System.in));
    }

    private static void init(BufferedReader br) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
    }

    private static int solution() {
        int answer = (int) calculate(A, B, C);
        return answer;
    }

    private static long calculate(long a, long b, long c) {
        if (b == 1) {
            return a % c;
        }
        long temp = calculate(a, b / 2, c);

        if (b % 2 == 1) {
            return (temp * temp % c) * a % c;
        } else {
            return temp * temp % c;
        }
    }
}