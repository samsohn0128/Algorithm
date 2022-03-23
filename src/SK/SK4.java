public class SK4 {
    static long[][] dist;

    public static long solution(int n, int[][] edges) {
        init(n, edges);
        System.out.println("===========");
        printDist();
        System.out.println("===========");
        floydWarshall(n);
        System.out.println("===========");
        printDist();
        System.out.println("===========");
        long answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dist[i][j] != 300001) {
                    answer += dist[i][j];
                }
            }
        }
        answer -= (long) n * (n - 1);
        System.out.println("answer = " + answer);

        return answer;
    }

    private static void printDist() {
        for (int i = 0; i < dist.length; i++) {
            for (int j = 0; j < dist.length; j++) {
                System.out.print(dist[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void floydWarshall(int n) {
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
    }

    private static void init(int n, int[][] edges) {
        dist = new long[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j)
                    dist[i][j] = 0;
                else
                    dist[i][j] = 300001;
            }
        }
        for (int i = 0; i < edges.length; i++) {
            dist[edges[i][0]][edges[i][1]] = 1;
            dist[edges[i][1]][edges[i][0]] = 1;
        }
    }


    public static void main(String[] args) {
        int[][] edges = {{0, 1}, {0, 2}, {1, 3}, {1, 4}};
        solution(5, edges);
    }
}