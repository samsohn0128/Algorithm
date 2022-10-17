import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class DSLR_B9019 {
    private static BufferedReader br;
    private static int T;
    private static String srcNumber, tgtNumber;
    private static boolean[] visited;

    public static void main(String[] args) throws Exception {
        init();
        String answer = solution();
        System.out.println(answer);
    }

    private static void init() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
    }

    private static String solution() throws Exception {
        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < T; tc++) {
            initTestCase();
            String answer = bfs();
            sb.append(answer);
            sb.append("\n");
        }
        return sb.toString();
    }

    private static void initTestCase() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        srcNumber = makeFourDigit(st.nextToken());
        tgtNumber = makeFourDigit(st.nextToken());
        visited = new boolean[10000];
    }

    private static String bfs() {
        Queue<Node> queue = new LinkedList<>();
        String answer = null;
        queue.offer(new Node(srcNumber, ""));
        visited[Integer.parseInt(srcNumber)] = true;
        while (!queue.isEmpty()) {
            Node temp = queue.poll();

            String nextNumber = operatorD(temp.number);
            if (nextNumber.equals(tgtNumber)) {
                answer = temp.operators + "D";
                break;
            }
            if (!visited[Integer.parseInt(nextNumber)]) {
                visited[Integer.parseInt(nextNumber)] = true;
                queue.offer(new Node(nextNumber, temp.operators + "D"));
            }

            nextNumber = operatorS(temp.number);
            if (nextNumber.equals(tgtNumber)) {
                answer = temp.operators + "S";
                break;
            }
            if (!visited[Integer.parseInt(nextNumber)]) {
                visited[Integer.parseInt(nextNumber)] = true;
                queue.offer(new Node(nextNumber, temp.operators + "S"));
            }

            nextNumber = operatorL(temp.number);
            if (nextNumber.equals(tgtNumber)) {
                answer = temp.operators + "L";
                break;
            }
            if (!visited[Integer.parseInt(nextNumber)]) {
                visited[Integer.parseInt(nextNumber)] = true;
                queue.offer(new Node(nextNumber, temp.operators + "L"));
            }

            nextNumber = operatorR(temp.number);
            if (nextNumber.equals(tgtNumber)) {
                answer = temp.operators + "R";
                break;
            }
            if (!visited[Integer.parseInt(nextNumber)]) {
                visited[Integer.parseInt(nextNumber)] = true;
                queue.offer(new Node(nextNumber, temp.operators + "R"));
            }
        }
        return answer;
    }

    private static String operatorD(String str) {
        return makeFourDigit(String.valueOf((Integer.parseInt(str) * 2) % 10000));
    }

    private static String operatorS(String str) {
        int registerNum = Integer.parseInt(str) - 1;
        return makeFourDigit(String.valueOf(registerNum == -1 ? 9999 : registerNum));
    }

    private static String operatorL(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            sb.append(str.charAt((i + 1) % str.length()));
        }
        return makeFourDigit(sb.toString());
    }

    private static String operatorR(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str.charAt(str.length() - 1));
        for (int i = 0; i < str.length() - 1; i++) {
            sb.append(str.charAt(i));
        }
        return makeFourDigit(sb.toString());
    }

    private static String makeFourDigit(String registerNum) {
        String result = "";
        for (int i = 0; i < 4 - registerNum.length(); i++) {
            result += '0';
        }
        result += registerNum;
        return result;
    }

    private static class Node {
        String number;
        String operators;

        public Node(String number, String operators) {
            this.number = number;
            this.operators = operators;
        }
    }
}