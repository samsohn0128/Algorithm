package SK;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SK2_3 {
    private Tree aTree, bTree;
    private int N;
    int answer = Integer.MAX_VALUE;

    public int solution(int[][] a, int[][] b, int m) {
        init(a, b);
        recur(m);
        return answer;
    }

    private void recur(int m) {
        int count = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (aTree.adjacent[i][j] != bTree.adjacent[i][j]) {
                    bTree.adjacent[i][j] = aTree.adjacent[i][j];
                    count++;
                }
            }
        }
        answer = Math.min(answer, count / 2);
        if (m > 0) {
            for (int i = 1; i < N; i++) {
                for (int j = i + 1; j <= N; j++) {
                    bTree.changeNode(i, j);
                    recur(m - 1);
                    bTree.changeNode(i, j);
                }
            }
        }
    }

    private void init(int[][] a, int[][] b) {
        N = a.length + 1;
        Node[] nodes = new Node[N + 1];
        for (int i = 1; i <= N; i++) {
            nodes[i] = new Node(i);
        }
        aTree = new Tree(1, nodes, a);
        bTree = new Tree(1, nodes, b);
    }

    private class Tree {
        private int root;
        private Node[] nodes;
        private boolean[][] adjacent;
        private boolean[] visited;
        private StringBuilder stringBuilder = new StringBuilder();

        public Tree(int root, Node[] nodes, int[][] edges) {
            this.root = root;
            this.nodes = nodes;
            adjacent = new boolean[N + 1][N + 1];
            visited = new boolean[N + 1];
            Arrays.stream(edges).forEach(edge -> {
                adjacent[edge[0]][edge[1]] = true;
                adjacent[edge[1]][edge[0]] = true;
            });
            makeTree(root);
        }

        private void makeTree(int num) {
            visited[num] = true;
            for (int i = 1; i <= N; i++) {
                if (adjacent[num][i] && !visited[i]) {
                    nodes[num].children.add(i);
                    makeTree(i);
                }
            }
        }

        private void changeNode(int num1, int num2) {
            boolean connected = adjacent[num1][num2];
            for (int i = 1; i <= N; i++) {
                if (adjacent[num1][i]) {
                    adjacent[i][num1] = false;
                }
                if (adjacent[num2][i]) {
                    adjacent[i][num2] = false;
                }
                boolean temp = adjacent[num1][i];
                adjacent[num1][i] = adjacent[num2][i];
                adjacent[num2][i] = temp;
                if (adjacent[num1][i]) {
                    adjacent[i][num1] = true;
                }
                if (adjacent[num2][i]) {
                    adjacent[i][num2] = true;
                }
            }
            if (connected) {
                adjacent[num1][num1] = false;
                adjacent[num2][num2] = false;
                adjacent[num1][num2] = true;
                adjacent[num2][num1] = true;
            }
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof Tree)) {
                return false;
            }
            Tree t = (Tree) o;
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (this.adjacent[i][j] != t.adjacent[i][j]) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    private class Node {
        private int num;
        private int parent;
        private List<Integer> children = new ArrayList<>();

        public Node(int num) {
            this.num = num;
        }
    }
}