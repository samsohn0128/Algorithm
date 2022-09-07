import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 행렬제곱_B10830 {

    private static int sizeOfMatrix;
    private static long exponent;
    private static int[][] matrix;

    private static final int MOD = 1000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = setBufferedReader();
        init(br);
        String answer = solution();
        System.out.println(answer);
        br.close();
    }

    private static BufferedReader setBufferedReader() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        return new BufferedReader(new InputStreamReader(System.in));
    }

    private static void init(BufferedReader br) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        sizeOfMatrix = Integer.parseInt(st.nextToken());
        exponent = Long.parseLong(st.nextToken());

        matrix = new int[sizeOfMatrix][sizeOfMatrix];
        for (int i = 0; i < sizeOfMatrix; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < sizeOfMatrix; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static String solution() {
        int[][] answerMatrix = pow(matrix, exponent);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sizeOfMatrix; i++) {
            for (int j = 0; j < sizeOfMatrix; j++) {
                sb.append(answerMatrix[i][j]);
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private static int[][] pow(int[][] matrix, long exponent) {
        if (exponent == 1) {
            return moduloMatrix(matrix);
        }
        int[][] temp = pow(matrix, exponent / 2);

        if (exponent % 2 == 1) {
            return multipleMatrix(multipleMatrix(temp, temp), matrix);
        } else {
            return multipleMatrix(temp, temp);
        }
    }

    private static int[][] multipleMatrix(int[][] matrix1, int[][] matrix2) {
        int[][] powMatrix = new int[sizeOfMatrix][sizeOfMatrix];
        for (int i = 0; i < sizeOfMatrix; i++) {
            for (int j = 0; j < sizeOfMatrix; j++) {
                int sum = 0;
                for (int k = 0; k < sizeOfMatrix; k++) {
                    sum = (sum + (matrix1[i][k] * matrix2[k][j]) % MOD) % MOD;
                }
                powMatrix[i][j] = sum;
            }
        }
        return powMatrix;
    }

    private static int[][] moduloMatrix(int[][] matrix) {
        for (int i = 0; i < sizeOfMatrix; i++) {
            for (int j = 0; j < sizeOfMatrix; j++) {
                matrix[i][j] %= MOD;
            }
        }
        return matrix;
    }
}