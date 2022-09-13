import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class 알고리즘수업_너비우선탐색2_B24445 {

    private static int numberOfVertices;
    private static int numberOfEdges;
    private static int start;
    private static List<Integer>[] edgeList;
    private static int[] orderOfVisit;
    private static int order = 1;

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
        StringTokenizer st = new StringTokenizer(br.readLine());
        numberOfVertices = Integer.parseInt(st.nextToken());
        numberOfEdges = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(st.nextToken());

        edgeList = new List[numberOfVertices + 1];
        orderOfVisit = new int[numberOfVertices + 1];
        for (int i = 1; i <= numberOfVertices; i++) {
            edgeList[i] = new ArrayList<>();
        }
        for (int i = 0; i < numberOfEdges; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            edgeList[u].add(v);
            edgeList[v].add(u);
        }
        for (int i = 1; i <= numberOfVertices; i++) {
            Collections.sort(edgeList[i], Comparator.reverseOrder());
        }
    }

    private static String solution() {
        StringBuilder sb = new StringBuilder();
        bfs(start);
        for (int i = 1; i <= numberOfVertices; i++) {
            sb.append(orderOfVisit[i]);
            sb.append("\n");
        }
        return sb.toString();
    }

    private static void bfs(int number) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(number);
        orderOfVisit[number] = order++;
        while (!queue.isEmpty()) {
            int temp = queue.poll();
            for (int i = 0; i < edgeList[temp].size(); i++) {
                int nextNumber = edgeList[temp].get(i);
                if (orderOfVisit[nextNumber] == 0) {
                    orderOfVisit[nextNumber] = order++;
                    queue.add(nextNumber);
                }
            }
        }
    }
}