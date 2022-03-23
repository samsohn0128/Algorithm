package SOCAR;

import java.util.ArrayList;
import java.util.List;

class SOCAR1 {
    private static int[][] adjacent;
    private static List<Integer> stationList = new ArrayList<>();

    public static int[] solution(int n, int k, int[][] roads) {
        init(n, roads);
        dfs(0, k);
        int[] answer = stationList.stream().mapToInt(i -> i).sorted().distinct().toArray();
        return answer;
    }

    private static void dfs(int num, int k) {
        if (k == 0) {
            stationList.add(num);
            return;
        } else if (k < 0) {
            return;
        }
        for (int i = 0; i < adjacent.length; i++) {
            if (0 < adjacent[num][i] && adjacent[num][i] <= k) {
                dfs(i, k - adjacent[num][i]);
            }
        }
    }

    private static void init(int n, int[][] roads) {
        adjacent = new int[n][n];
        for (int i = 0; i < roads.length; i++) {
            int a = roads[i][0];
            int b = roads[i][1];
            int t = roads[i][2];
            adjacent[a][b] = t;
            adjacent[b][a] = t;
        }
    }

    public static void main(String[] args) {
        int n = 6;
        int k = 17;
        int[][] roads = {{5, 4, 6}, {5, 2, 5}, {0, 4, 2}, {2, 3, 3}, {1, 2, 7}, {0, 1, 3}};
        int[] answer = solution(n, k, roads);
        for (int i = 0; i < answer.length; i++) {
            System.out.print(answer[i] + "\t");
        }
        System.out.println();
    }
}