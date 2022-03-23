public class SK3_1 {
    private int[][] grid;
    private int[][][] dp;
    private int answer;

    public int solution(int width, int height, int[][] diagonals) {

        int answer = 0;
        return answer;
    }


    private void dfs(int y, int x, int width, int height, boolean diagonalFlag) {
        if (y == width && x == height && diagonalFlag) {
            answer++;
            return;
        }
    }


    private void init(int width, int height, int[][] diagonals) {
        dp = new int[height + 1][width + 1][2];
        grid = new int[height + 1][width + 1];
        for (int i = 0; i < diagonals.length; i++) {
            grid[diagonals[i][0] - 1][diagonals[i][1] - 1] += 1;
            grid[diagonals[i][0]][diagonals[i][1]] += 2;
        }
    }

    public static void main(String[] args) {

    }
}