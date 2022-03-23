package SOCAR;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class SOCAR1_BFS {
    private static List<Node>[] adjacent;
    private static List<Integer> stationList = new ArrayList<>();

    public static int[] solution(int n, int k, int[][] roads) {
        init(n, roads);
//        for (List<Node> nodeList : adjacent) {
//            nodeList.forEach(node -> System.out.print(node.num + "\t"));
//            System.out.println();
//        }
        bfs(k);
        int[] answer = stationList.stream().mapToInt(i -> i).sorted().distinct().toArray();
        return answer;
    }

    private static void bfs(int k) {
        Queue<Node> nodeQueue = new LinkedList<>();
        nodeQueue.offer(new Node(0, 0));
        while (!nodeQueue.isEmpty()) {
            Node temp = nodeQueue.poll();
            adjacent[temp.num].forEach(node -> {
                if (temp.weight + node.weight < k) {
                    nodeQueue.offer(new Node(node.num, temp.weight + node.weight));
                } else if (temp.weight + node.weight == k) {
                    stationList.add(node.num);
                }
            });
        }
    }

    private static void init(int n, int[][] roads) {
        adjacent = new List[n];
        for (int i = 0; i < n; i++) {
            adjacent[i] = new ArrayList<>();
        }
        for (int i = 0; i < roads.length; i++) {
            int a = roads[i][0];
            int b = roads[i][1];
            int t = roads[i][2];
            adjacent[a].add(new Node(b, t));
            adjacent[b].add(new Node(a, t));
        }
    }

    private static class Node {
        int num;
        int weight;

        public Node(int num, int weight) {
            this.num = num;
            this.weight = weight;
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