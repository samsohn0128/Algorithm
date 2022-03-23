public class SK3 {

    static int num = 10000019;
    static int[][] dp = new int[251][251];

    static int solution(int width, int height, int[][] diagonals) {
        int answer = 0;

        for (int row = 1; row < 251; row++) {
            for (int col = 0; col < 251; col++) {
                if (row == col || col == 0) {
                    dp[row][col] = 1;
                    continue;
                }
                dp[row][col] = (dp[row - 1][col] + dp[row - 1][col - 1]) % num;
            }
        }

        for (int i = 0; i < diagonals.length; i++) {
            int topX = diagonals[i][0];
            int topY = diagonals[i][1] - 1;
            int leftX = diagonals[i][0] - 1;
            int leftY = diagonals[i][1];
            long topCnt1 = dp[leftX + leftY][leftX];
            long topCnt2 = dp[width + height - topX - topY][width - topX];
            long leftCnt1 = dp[topX + topY][topX];
            long leftCnt2 = dp[width + height - leftX - leftY][width - leftX];
            long ans = (topCnt1 * topCnt2) % num + (leftCnt1 * leftCnt2) % num;
            answer += ans % num;
        }
        return answer % num;
    }

    public static void main(String[] args) {
        int answer = solution(51, 37, new int[][]{{17, 19}});
        System.out.println("answer = " + answer);
    }
}