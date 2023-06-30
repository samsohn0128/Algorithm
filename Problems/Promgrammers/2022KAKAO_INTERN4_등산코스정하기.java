import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {
    private static Node[] nodes;
    private static boolean[] visited;

    public static int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        init(n, paths, gates, summits);
        int[] answer = {Integer.MAX_VALUE, Integer.MAX_VALUE};
        for (int gate : gates) {
            initVisited();
            int[] tempAnswer = dijkstra(nodes[gate]);
            if (tempAnswer != null) {
                if (answer[1] > tempAnswer[1]) {
                    answer = tempAnswer;
                } else if (answer[1] == tempAnswer[1] && answer[0] > tempAnswer[0]) {
                    answer = tempAnswer;
                }
            }
        }
        return answer;
    }

    private static void init(int n, int[][] paths, int[] gates, int[] summits) {
        nodes = new Node[n + 1];
        visited = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            nodes[i] = new Node(i);
        }
        for (int[] path : paths) {
            nodes[path[0]].addPath(new Edge(path[0], path[1], path[2]));
            nodes[path[1]].addPath(new Edge(path[1], path[0], path[2]));
        }
        for (int gate : gates) {
            nodes[gate].setIsGateTrue();
        }
        for (int summit : summits) {
            nodes[summit].setIsSummitTrue();
        }
    }

    private static void initVisited() {
        Arrays.fill(visited, false);
    }

    private static int[] dijkstra(Node start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> {
            if (e1.weight != e2.weight) {
                return e1.weight - e2.weight;
            } else {
                return e1.to - e2.to;
            }
        });

        start.pathList.forEach(pq::offer);
        int[] answer = null;
        while (!pq.isEmpty()) {
            Edge temp = pq.poll();
            if (nodes[temp.to].isSummit) {
                answer = new int[]{temp.to, temp.weight};
                break;
            }
            visited[temp.from] = true;
            nodes[temp.to].pathList.forEach(edge -> {
                if (!nodes[edge.to].isGate && !visited[edge.to]) {
                    pq.offer(new Edge(edge.from, edge.to, Math.max(temp.weight, edge.weight)));
                }
            });
        }
        return answer;
    }

    private static class Node {
        private int num;
        private List<Edge> pathList = new ArrayList<>();

        private boolean isSummit = false;
        private boolean isGate = false;

        public Node(int num) {
            this.num = num;
        }

        public void addPath(Edge path) {
            pathList.add(path);
        }

        public void setIsGateTrue() {
            isGate = true;
        }

        public void setIsSummitTrue() {
            isSummit = true;
        }
    }

    private static class Edge {
        private int from;
        private int to;
        private int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
//        int n = 6;
//        int[][] paths = {{1, 2, 3}, {2, 3, 5}, {2, 4, 2}, {2, 5, 4}, {3, 4, 4}, {4, 5, 3}, {4, 6, 1}, {5, 6, 1}};
//        int[] gates = {1, 3};
//        int[] summits = {5};
        int n = 7;
        int[][] paths = {{1, 4, 4}, {1, 6, 1}, {1, 7, 3}, {2, 5, 2}, {3, 7, 4}, {5, 6, 6}};
        int[] gates = {1};
        int[] summits = {2, 3, 4};
        int[] answer = solution(n, paths, gates, summits);
        System.out.println(Arrays.toString(answer));
    }
}