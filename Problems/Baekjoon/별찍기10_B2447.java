import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

class 별찍기10_B2447 {

    private static int N;
    private static char[][] stars;

    public static void main(String[] args) throws Exception {
        init();
        String answer = solution();
        System.out.println(answer);
    }

    private static void init() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        stars = new char[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                stars[i][j] = ' ';
            }
        }
    }

    private static String solution() {
        StringBuilder sb = new StringBuilder();
        recur(0, 0, N);
        for (int i = 0; i < N; i++) {
            sb.append(stars[i]);
            sb.append("\n");
        }
        return sb.toString();
    }

    private static void recur(int startY, int startX, int size) {
        if (size == 3) {
            for (int i = 0; i < 3; i++) {
                stars[startY][startX + i] = '*';
                if (i != 1) {
                    stars[startY + 1][startX + i] = '*';
                }
                stars[startY + 2][startX + i] = '*';
            }
        } else {
            for (int i = 0; i < 3; i++) {
                recur(startY, startX + size / 3 * i, size / 3);
                if (i != 1) {
                    recur(startY + size / 3, startX + size / 3 * i, size / 3);
                }
                recur(startY + size / 3 * 2, startX + size / 3 * i, size / 3);
            }
        }
    }
}