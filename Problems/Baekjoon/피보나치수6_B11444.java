import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class 피보나치수6_B11444 {

    private static long number;
    private static final int MOD = 1000000007;
    private static final long[][] originalMatrix = {{1, 1}, {1, 0}};

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
        number = Long.parseLong(br.readLine());
    }

    private static int solution() {
        long[][] A = {{1, 1}, {1, 0}};
        long[][] answerMatrix = pow(A, number - 1);
        return (int) answerMatrix[0][0];
    }

    private static long[][] pow(long[][] matrix, long exponent) {
        if (exponent == 0 || exponent == 1) {
            return matrix;
        }
        long[][] temp = pow(matrix, exponent / 2);
        temp = multiply(temp, temp);
        if (exponent % 2 == 1) {
            temp = multiply(temp, originalMatrix);
        }
        return temp;
    }

    private static long[][] multiply(long[][] matrix1, long[][] matrix2) {
        long[][] multipliedMatrix = new long[matrix1.length][matrix2[0].length];
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix2[0].length; j++) {
                long sum = 0;
                for (int k = 0; k < matrix2.length; k++) {
                    sum = (sum + matrix1[i][k] * matrix2[k][j]) % MOD;
                }
                multipliedMatrix[i][j] = sum;
            }
        }
        return multipliedMatrix;
    }
}