import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 가장가까운공통조상_B3584 {
    private static int T;
    private static int N;
    private static Node[] nodes;
    private static Tree tree;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            init(br);
            StringTokenizer st = new StringTokenizer(br.readLine());
            int inputNode1 = Integer.parseInt(st.nextToken());
            int inputNode2 = Integer.parseInt(st.nextToken());
            while (nodes[inputNode1].depth != nodes[inputNode2].depth) {
                if (nodes[inputNode1].depth > nodes[inputNode2].depth) {
                    inputNode1 = nodes[inputNode1].parent;
                } else if (nodes[inputNode1].depth < nodes[inputNode2].depth) {
                    inputNode2 = nodes[inputNode2].parent;
                }
            }
            while (inputNode1 != inputNode2) {
                inputNode1 = nodes[inputNode1].parent;
                inputNode2 = nodes[inputNode2].parent;
            }
            System.out.println(inputNode1);
        }
    }

    private static void init(BufferedReader br) throws Exception {
        N = Integer.parseInt(br.readLine());
        nodes = new Node[N + 1];
        for (int i = 1; i <= N; i++) {
            nodes[i] = new Node(i);
        }
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            nodes[B].parent = A;
            nodes[A].children.add(B);
        }
        for (int i = 1; i <= N; i++) {
            if (nodes[i].parent == -1) {
                tree = new Tree(nodes[i]);
                break;
            }
        }
        setDepth(tree.root.num, 0);
    }

    private static void setDepth(int num, int depth) {
        nodes[num].depth = depth;
        for (int child : nodes[num].children) {
            setDepth(child, depth + 1);
        }
    }

    private static class Tree {
        private Node root;

        public Tree(Node root) {
            this.root = root;
        }
    }

    private static class Node {
        private int num;
        private int parent = -1;
        private List<Integer> children = new ArrayList<>();
        private int depth;

        public Node(int num) {
            this.num = num;
        }
    }
}