import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 다리놓기_B1010 {

    private static int numberOfTestCases;
    private static int N, M;

    private static int[][] combinations = new int[31][31];

    public static void main(String[] args) throws Exception {
        BufferedReader br = setBufferedReader();
        numberOfTestCases = Integer.parseInt(br.readLine());

        setCombinations();
        for (int i = 0; i < numberOfTestCases; i++) {
            init(br);
            System.out.println(combinations[M][N]);
        }

        br.close();
    }

    private static BufferedReader setBufferedReader() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        return new BufferedReader(new InputStreamReader(System.in));
    }

    private static void init(BufferedReader br) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
    }

    private static void setCombinations() {
        for (int i = 1; i <= 30; i++) {
            combinations[i][0] = 1;
            combinations[i][i] = 1;
        }
        for (int i = 2; i <= 30; i++) {
            for (int j = 1; j < i; j++) {
                combinations[i][j] = combinations[i - 1][j] + combinations[i - 1][j - 1];
            }
        }
    }
}