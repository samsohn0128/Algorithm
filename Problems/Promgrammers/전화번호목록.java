import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public boolean solution(String[] phoneBook) {
        Arrays.sort(phoneBook, (str1, str2) -> str2.length() - str1.length());
        Tree tree = new Tree();
        boolean answer = true;
        for (String phoneNumber : phoneBook) {
            boolean hasNewNumber = tree.add(phoneNumber);
            if (!hasNewNumber) {
                answer = false;
            }
        }
        return answer;
    }

    class Tree {
        Node root = new Node(-1);

        boolean add(String strNumber) {
            boolean hasNewNumber = false;
            Node tempNode = root;
            for (int i = 0; i < strNumber.length(); i++) {
                int number = strNumber.charAt(i) - '0';
                boolean containsNumber = false;
                for (Node child : tempNode.children) {
                    if (child.number == number) {
                        containsNumber = true;
                        tempNode = child;
                        break;
                    }
                }
                if (!containsNumber) {
                    hasNewNumber = true;
                    Node newNode = new Node(number);
                    tempNode.children.add(newNode);
                    tempNode = newNode;
                }
            }
            return hasNewNumber;
        }
    }

    class Node {
        int number;
        List<Node> children = new ArrayList<>();

        Node(int number) {
            this.number = number;
        }
    }
}