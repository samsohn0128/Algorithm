import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class 이분그래프_B1707 {

    private static int numberOfTestcases;
    private static int numberOfVertices;
    private static int numberOfEdges;
    private static Node[] nodes;
    private static boolean[] visited;
    private static Set<Integer> firstGroup, secondGroup;


    public static void main(String[] args) throws Exception {
        BufferedReader br = setBufferedReader();
        init(br);
        String answer = solution(br);
        System.out.println(answer);
        br.close();
    }

    private static BufferedReader setBufferedReader() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        return new BufferedReader(new InputStreamReader(System.in));
    }

    private static void init(BufferedReader br) throws Exception {
        numberOfTestcases = Integer.parseInt(br.readLine());
    }

    private static String solution(BufferedReader br) throws Exception {
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < numberOfTestcases; t++) {
            initForSingleTestcase(br);
            setGroups();
            boolean dividable = checkGroups();

            if (dividable) {
                sb.append("YES");
            } else {
                sb.append("NO");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private static void initForSingleTestcase(BufferedReader br) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        numberOfVertices = Integer.parseInt(st.nextToken());
        numberOfEdges = Integer.parseInt(st.nextToken());

        nodes = new Node[numberOfVertices + 1];
        visited = new boolean[numberOfVertices + 1];
        for (int i = 1; i <= numberOfVertices; i++) {
            nodes[i] = new Node(i);
        }

        for (int i = 0; i < numberOfEdges; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            nodes[v1].adjacent.add(v2);
            nodes[v2].adjacent.add(v1);
        }

        firstGroup = new HashSet<>();
        secondGroup = new HashSet<>();
    }

    private static void setGroups() {
        for (int i = 1; i <= numberOfVertices; i++) {
            if (!visited[i]) {
                dfs(i, true);
            }
        }
    }

    private static boolean checkGroups() {
        for (int number : firstGroup) {
            if (secondGroup.contains(number)) {
                return false;
            }
        }
        for (int number : secondGroup) {
            if (firstGroup.contains(number)) {
                return false;
            }
        }
        return true;
    }

    private static void dfs(int number, boolean isFirstGroup) {
        if (isFirstGroup) {
            addFirstGroup(number);
        } else {
            addSecondGroup(number);
        }

        for (int i = 0; i < nodes[number].adjacent.size(); i++) {
            int nextNumber = nodes[number].adjacent.get(i);
            if (!visited[nextNumber]) {
                visited[nextNumber] = true;
                dfs(nextNumber, !isFirstGroup);
            }
        }
    }

    private static void addFirstGroup(int number) {
        firstGroup.add(number);
        for (int i = 0; i < nodes[number].adjacent.size(); i++) {
            secondGroup.add(nodes[number].adjacent.get(i));
        }
    }

    private static void addSecondGroup(int number) {
        secondGroup.add(number);
        for (int i = 0; i < nodes[number].adjacent.size(); i++) {
            firstGroup.add(nodes[number].adjacent.get(i));
        }
    }

    private static class Node {


        int number;
        List<Integer> adjacent = new ArrayList<>();

        public Node(int number) {
            this.number = number;
        }
    }
}