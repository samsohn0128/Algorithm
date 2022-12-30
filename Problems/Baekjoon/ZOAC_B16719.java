import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class ZOAC_B16719 {
    private static Node[] alphabetArr;

    public static void main(String[] args) throws Exception {
        init();
        String answer = solution();
        System.out.println(answer);
    }

    private static void init() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        alphabetArr = new Node[input.length()];
        for (int i = 0; i < input.length(); i++) {
            alphabetArr[i] = new Node(input.charAt(i), i);
        }

        br.close();
    }

    private static String solution() {
        StringBuilder sb = new StringBuilder();
        recur(0, sb);
        return sb.toString();
    }


    private static void recur(int index, StringBuilder sb) {
        while (true) {
            int minIndex = findMinNode(index);
            if (minIndex == -1) {
                break;
            }
            alphabetArr[minIndex].visible = true;
            for (int i = 0; i < alphabetArr.length; i++) {
                if (alphabetArr[i].visible) {
                    sb.append(alphabetArr[i].alphabet);
                }
            }
            sb.append('\n');
            recur(minIndex, sb);
        }
    }

    private static int findMinNode(int from) {
        char minChar = 'Z' + 1;
        int minIndex = -1;
        for (int i = from; i < alphabetArr.length; i++) {
            if (!alphabetArr[i].visible) {
                if (minChar > alphabetArr[i].alphabet) {
                    minChar = alphabetArr[i].alphabet;
                    minIndex = i;
                }
            }
        }
        return minIndex;
    }


    private static class Node implements Comparable<Node> {
        private final char alphabet;
        private final int index;
        private boolean visible = false;

        public Node(char alphabet, int index) {
            this.alphabet = alphabet;
            this.index = index;
        }

        @Override
        public int compareTo(Node node) {
            if (this.alphabet != node.alphabet) {
                return this.alphabet - node.alphabet;
            } else {
                return this.index - node.index;
            }
        }
    }
}