import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 트리_B4803 {

    private static int numberOfVertices;
    private static int numberOfEdges;
    private static boolean[][] adjacent;
    private static boolean[] visited;
    private static boolean cycle;

    public static void main(String[] args) throws Exception {
        BufferedReader br = setBufferedReader();
        String answer = solution(br);
        System.out.println(answer);
        br.close();
    }

    private static BufferedReader setBufferedReader() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        return new BufferedReader(new InputStreamReader(System.in));
    }

    private static String solution(BufferedReader br) throws Exception {
        StringBuilder sb = new StringBuilder();
        int caseNumber = 1;
        while (true) {
            init(br);
            if (numberOfVertices == 0 && numberOfEdges == 0) break;
            int count = 0;
            for (int i = 1; i <= numberOfVertices; i++) {
                if (!visited[i]) {
                    cycle = false;
                    dfs(i, -1);
                    if (!cycle) {
                        count++;
                    }
                }
            }
            sb.append("Case " + caseNumber++ + ": ");
            if (count == 0) {
                sb.append("No trees.");
            } else if (count == 1) {
                sb.append("There is one tree.");
            } else {
                sb.append("A forest of " + count + " trees.");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private static void init(BufferedReader br) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        numberOfVertices = Integer.parseInt(st.nextToken());
        numberOfEdges = Integer.parseInt(st.nextToken());
        adjacent = new boolean[numberOfVertices + 1][numberOfVertices + 1];
        visited = new boolean[numberOfVertices + 1];
        for (int i = 0; i < numberOfEdges; i++) {
            st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            adjacent[num1][num2] = true;
            adjacent[num2][num1] = true;
        }
    }

    private static void dfs(int curNumber, int beforeNumber) {
//        System.out.print("curNumber = " + curNumber);
//        System.out.println(", beforeNumber = " + beforeNumber);
        visited[curNumber] = true;
        for (int i = 1; i <= numberOfVertices; i++) {
            if (adjacent[curNumber][i] && i != beforeNumber) {
                if (!visited[i]) {
                    dfs(i, curNumber);
                } else {
                    cycle = true;
                }

                if (cycle) {
                    return;
                }
            }
        }
    }
}