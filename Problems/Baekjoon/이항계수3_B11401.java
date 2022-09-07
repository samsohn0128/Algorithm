import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 이항계수3_B11401 {

    private static int N, K;
    private static final int MOD = 1000000007;

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
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
    }

    private static int solution() {
        long number = factorial(N);
        long denominator = (factorial(K) * factorial(N - K)) % MOD;
        int answer = (int) (number * pow(denominator, MOD - 2) % MOD);
        return answer;
    }

    private static long factorial(int n) {
        long number = 1L;
        while (n > 0) {
            number = (number * n) % MOD;
            n--;
        }
        return number;
    }

    private static long pow(long base, int exponent) {
        if (exponent == 1) {
            return base % MOD;
        }

        long temp = pow(base, exponent / 2);
        temp = (temp * temp) % MOD;

        if (exponent % 2 == 1) {
            temp = (temp * base) % MOD;
        }
        return temp;
    }
}