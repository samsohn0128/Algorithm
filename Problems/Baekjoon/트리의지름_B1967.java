import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class 트리의지름_B1967 {

    private static int numberOfNodes;
    private static List<Integer[]>[] edges;

    private static List<Integer>[] distances;
    private static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = setBufferedReader();
        init(br);
        int answer = solution();
        System.out.println(answer);
        br.close();
    }

    private static BufferedReader setBufferedReader() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        return new BufferedReader(new InputStreamReader(System.in));
    }

    private static void init(BufferedReader br) throws Exception {
        numberOfNodes = Integer.parseInt(br.readLine());
        edges = new List[numberOfNodes + 1];
        for (int i = 1; i <= numberOfNodes; i++) {
            edges[i] = new ArrayList<>();
        }
        for (int i = 0; i < numberOfNodes - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edges[parent].add(new Integer[]{child, weight});
            edges[child].add(new Integer[]{parent, weight});
        }
        distances = new List[numberOfNodes + 1];
        for (int i = 1; i <= numberOfNodes; i++) {
            distances[i] = new ArrayList<>();
        }
        visited = new boolean[numberOfNodes + 1];
    }

    private static int solution() {
        dfs(1);
        int answer = getAnswer();
        return answer;
    }

    private static void dfs(int number) {
        visited[number] = true;
        for (int i = 0; i < edges[number].size(); i++) {
            int nextNumber = edges[number].get(i)[0];
            int weight = edges[number].get(i)[1];
            if (!visited[nextNumber]) {
                dfs(nextNumber);
                int maxWeight = 0;
                for (int j = 0; j < distances[nextNumber].size(); j++) {
                    maxWeight = Math.max(maxWeight, distances[nextNumber].get(j));
                }
                distances[number].add(maxWeight + weight);
            }
        }
    }

    private static int getAnswer() {
        int maxSum = 0;
        for (int i = 1; i <= numberOfNodes; i++) {
            if (distances[i].size() >= 2) {
                Collections.sort(distances[i], Comparator.reverseOrder());
                maxSum = Math.max(maxSum, distances[i].get(0) + distances[i].get(1));
            } else if (distances[i].size() == 1) {
                maxSum = Math.max(maxSum, distances[i].get(0));
            }
        }
        return maxSum;
    }
}