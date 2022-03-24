import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class LCA2_B11438 {

    private static int N, M;
    private static List<Integer>[] edges;
    private static boolean[] visited;
    private static Node[] nodes;

    private static final int MAX = 20;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        init(br);
        makeTree(1, 0);
        setParents();
        solution(br);
        br.close();
    }

    private static void solution(BufferedReader br) throws Exception {
        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            int[] sameDepthNums = adjustDepth(num1, num2);
            int answer = findLCA(sameDepthNums[0], sameDepthNums[1]);
            System.out.println(answer);
        }
    }

    private static int[] adjustDepth(int num1, int num2) {
        if (nodes[num1].depth > nodes[num2].depth) {
            int temp = num1;
            num1 = num2;
            num2 = temp;
        }
        while (true) {
            if (nodes[num1].depth == nodes[num2].depth) {
                return new int[]{num1, num2};
            }
            for (int i = 19; i >= 0; i--) {
                int parent = nodes[num2].parent[i];
                if (parent != 0) {
                    if (nodes[num1].depth <= nodes[parent].depth) {
                        num2 = parent;
                        break;
                    }
                }
            }
        }
    }

    private static int findLCA(int num1, int num2) {
        if (num1 == num2) return num1;
        while (true) {
            for (int i = 19; i >= 0; i--) {
                int parent1 = nodes[num1].parent[i];
                int parent2 = nodes[num2].parent[i];
                if (parent1 != parent2) {
                    num1 = parent1;
                    num2 = parent2;
                    break;
                }
                if (i == 0) {
                    return parent1;
                }
            }
        }
    }

    private static void setParents() {
        for (int j = 0; j < MAX; j++) {
            for (int i = 1; i <= N; i++) {
                int parent = nodes[i].parent[j];
                if (parent != 0) {
                    nodes[i].parent[j + 1] = nodes[parent].parent[j];
                }
            }
        }
    }

    private static void init(BufferedReader br) throws Exception {
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        nodes = new Node[N + 1];
        edges = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        for (int i = 0; i <= N; i++) {
            nodes[i] = new Node(i);
            edges[i] = new ArrayList<>();
        }
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            edges[num1].add(num2);
            edges[num2].add(num1);
        }
    }

    private static void makeTree(int curNode, int depth) {
        visited[curNode] = true;
        nodes[curNode].depth = depth;
        edges[curNode].forEach(nextNode -> {
            if (!visited[nextNode]) {
                nodes[nextNode].parent[0] = curNode;
                nodes[curNode].children.add(nextNode);
                makeTree(nextNode, depth + 1);
            }
        });
    }

    private static class Node {
        private int num = 0;
        private int[] parent = new int[MAX];
        private List<Integer> children = new ArrayList<>();
        int depth;

        public Node(int num) {
            this.num = num;
        }
    }
}