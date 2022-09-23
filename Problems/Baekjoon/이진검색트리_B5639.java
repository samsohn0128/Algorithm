import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class 이진검색트리_B5639 {

    private static List<Integer> numberList = new ArrayList<>();
    private static Tree tree;

    private static StringBuilder answerBuilder = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = setBufferedReader();
        init(br);
        String answer = solution();
        System.out.println(answer);
        br.close();
    }

    private static BufferedReader setBufferedReader() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        return new BufferedReader(new InputStreamReader(System.in));
    }

    private static void init(BufferedReader br) throws Exception {
        while (true) {
            String input = br.readLine();
            if (input == null) break;
            numberList.add(Integer.parseInt(input));
        }
    }

    private static String solution() {
        tree = new Tree(new Node(numberList.get(0)));
        for (int i = 1; i < numberList.size(); i++) {
            tree.add(new Node(numberList.get(i)));
        }
        postTraversal(tree.root);

        return answerBuilder.toString();
    }

    private static Node getParentNode(Node beforeNode, Node curNode) {
        while (beforeNode.parent != null && beforeNode.parent.number < curNode.number) {
            beforeNode = beforeNode.parent;
        }
        return beforeNode;
    }

    private static void postTraversal(Node node) {
        if (node.lchild != null) {
            postTraversal(node.lchild);
        }
        if (node.rchild != null) {
            postTraversal(node.rchild);
        }
        makeAnswer(node);
    }

    private static void makeAnswer(Node node) {
        answerBuilder.append(node.number);
        answerBuilder.append('\n');
    }

    private static class Node {

        private int number;
        private Node parent;
        private Node lchild;
        private Node rchild;

        public Node(int number) {
            this.number = number;
        }
    }

    private static class Tree {
        private Node root;

        public Tree(Node root) {
            this.root = root;
        }

        private void add(Node node) {
            Node parentNode = root;
            while (true) {
                if (parentNode.number < node.number) {
                    if (parentNode.rchild != null) {
                        parentNode = parentNode.rchild;
                    } else {
                        parentNode.rchild = node;
                        break;
                    }
                } else {
                    if (parentNode.lchild != null) {
                        parentNode = parentNode.lchild;
                    } else {
                        parentNode.lchild = node;
                        break;
                    }
                }
            }
        }
    }
}