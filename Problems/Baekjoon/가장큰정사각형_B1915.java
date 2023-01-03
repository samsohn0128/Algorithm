import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 가장큰정사각형_B1915 {
    private static int N, M;
    private static int[][] grid;
    private static int[][] lengthOfSquare;

    public static void main(String[] args) throws Exception {
        init();
        String answer = solution();
        System.out.println(answer);
    }

    private static void init() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        grid = new int[N][M];
        lengthOfSquare = new int[N][M];
        for (int i = 0; i < N; i++) {
            String temp = br.readLine();
            for (int j = 0; j < M; j++) {
                grid[i][j] = temp.charAt(j) - '0';
            }
        }
        br.close();
    }

    private static String solution() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            lengthOfSquare[i][0] = grid[i][0];
        }
        for (int j = 0; j < M; j++) {
            lengthOfSquare[0][j] = grid[0][j];
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                if (grid[i][j] == 1) {
                    lengthOfSquare[i][j] = Math.min(lengthOfSquare[i - 1][j], Math.min(lengthOfSquare[i - 1][j - 1], lengthOfSquare[i][j - 1])) + 1;
                }
            }
        }
        int maxLength = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                maxLength = Math.max(maxLength, lengthOfSquare[i][j]);
            }
        }
        sb.append(maxLength * maxLength);
        return sb.toString();
    }

}