import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 체스판다시칠하기2_B25682 {
    private static BufferedReader br;
    private static int N, M, K;
    private static char[][] chessBoardStartWithBlack, chessBoardStartWithWhite;
    private static char[][] board;

    public static void main(String[] args) throws Exception {
        init();
        String answer = solution();
        System.out.println(answer);
    }

    private static void init() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
        }
        br.close();
        chessBoardStartWithBlack = makeChessBoard(true);
        chessBoardStartWithWhite = makeChessBoard(false);
    }

    private static String solution() {
        StringBuilder sb = new StringBuilder();
        int[][][] countDiff = countDiff();
        int minCount = Integer.MAX_VALUE;
        for (int k = 0; k < 2; k++) {
            for (int i = K - 1; i < N; i++) {
                for (int j = K - 1; j < M; j++) {
                    int count = countDiff[i][j][k];
                    if (i - K >= 0) {
                        count -= countDiff[i - K][j][k];
                    }
                    if (j - K >= 0) {
                        count -= countDiff[i][j - K][k];
                    }
                    if (i - K >= 0 && j - K >= 0) {
                        count += countDiff[i - K][j - K][k];
                    }
                    minCount = Math.min(minCount, count);
                }
            }
        }
        sb.append(minCount);
        return sb.toString();
    }

    private static char[][] makeChessBoard(boolean startWithBlack) {
        char[][] chessBoard = new char[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (i % 2 == 0) {
                    if (j % 2 == 0) {
                        chessBoard[i][j] = startWithBlack ? 'B' : 'W';
                    } else {
                        chessBoard[i][j] = startWithBlack ? 'W' : 'B';
                    }
                } else {
                    if (j % 2 == 0) {
                        chessBoard[i][j] = startWithBlack ? 'W' : 'B';
                    } else {
                        chessBoard[i][j] = startWithBlack ? 'B' : 'W';
                    }
                }
            }
        }
        return chessBoard;
    }

    private static int[][][] countDiff() {
        int[][][] countDiff = new int[N][M][2];
        countDiff[0][0][0] = board[0][0] == chessBoardStartWithBlack[0][0] ? 0 : 1;
        countDiff[0][0][1] = board[0][0] == chessBoardStartWithWhite[0][0] ? 0 : 1;
        for (int i = 1; i < N; i++) {
            countDiff[i][0][0] = countDiff[i - 1][0][0];
            countDiff[i][0][1] = countDiff[i - 1][0][1];
            countDiff[i][0][0] += board[i][0] == chessBoardStartWithBlack[i][0] ? 0 : 1;
            countDiff[i][0][1] += board[i][0] == chessBoardStartWithWhite[i][0] ? 0 : 1;
        }
        for (int i = 1; i < M; i++) {
            countDiff[0][i][0] = countDiff[0][i - 1][0];
            countDiff[0][i][1] = countDiff[0][i - 1][1];
            countDiff[0][i][0] += board[0][i] == chessBoardStartWithBlack[0][i] ? 0 : 1;
            countDiff[0][i][1] += board[0][i] == chessBoardStartWithWhite[0][i] ? 0 : 1;
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                countDiff[i][j][0] = countDiff[i - 1][j][0] + countDiff[i][j - 1][0] - countDiff[i - 1][j - 1][0];
                countDiff[i][j][0] += board[i][j] == chessBoardStartWithBlack[i][j] ? 0 : 1;

                countDiff[i][j][1] = countDiff[i - 1][j][1] + countDiff[i][j - 1][1] - countDiff[i - 1][j - 1][1];
                countDiff[i][j][1] += board[i][j] == chessBoardStartWithWhite[i][j] ? 0 : 1;
            }
        }
        return countDiff;
    }
}