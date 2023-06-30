public class Solution {
    private static final int[] dy = {1, 0, 0, -1};
    private static final int[] dx = {0, -1, 1, 0};
    private static final char[] directions = {'d', 'l', 'r', 'u'};
    private static char[][] grid;
    private static int N, M, K;
    private static Coordinate start;
    private static Coordinate end;

    private static String answer = "";

    public static String solution(int n, int m, int x, int y, int r, int c, int k) {
        init(n, m, x, y, r, c, k);
        dfs(start, new StringBuilder());
        return answer.isBlank() ? "impossible" : answer;
    }

    private static void init(int n, int m, int x, int y, int r, int c, int k) {
        N = n;
        M = m;
        K = k;
        grid = new char[N + 1][M + 1];
        start = new Coordinate(x, y);
        end = new Coordinate(r, c);
    }

    private static void dfs(Coordinate currentCoordinate, StringBuilder sb) {
        int leftMoveCount = K - sb.length();
        if (currentCoordinate.getDistanceToEnd() > leftMoveCount) {
            return;
        } else if ((leftMoveCount - currentCoordinate.getDistanceToEnd()) % 2 == 1) {
            return;
        }
        if (!answer.isBlank()) {
            return;
        }
        if (sb.length() == K) {
            if (currentCoordinate.isEnd()) {
                answer = sb.toString();
            }
            return;
        }
        for (int i = 0; i < 4; i++) {
            Coordinate nextCoordinate = currentCoordinate.getNextCoordinate(i);
            if (nextCoordinate.isInGrid()) {
                dfs(nextCoordinate, new StringBuilder(sb).append(directions[i]));
            }
        }
    }

    private static class Coordinate {
        private int y;
        private int x;

        public Coordinate(int y, int x) {
            this.y = y;
            this.x = x;
        }

        public Coordinate getNextCoordinate(int i) {
            return new Coordinate(y + dy[i], x + dx[i]);
        }

        public boolean isInGrid() {
            return y > 0 && y <= N && x > 0 && x <= M;
        }

        public boolean isEnd() {
            return y == end.y && x == end.x;
        }

        public int getDistanceToEnd() {
            return Math.abs(end.y - y) + Math.abs(end.x - x);
        }
    }

    public static void main(String[] args) {
        int n = 3;
        int m = 4;
        int x = 2;
        int y = 3;
        int r = 3;
        int c = 1;
        int k = 5;
        String answer = solution(n, m, x, y, r, c, k);
        System.out.println(answer);
    }
}