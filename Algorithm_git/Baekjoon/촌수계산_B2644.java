import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class 촌수계산_B2644 {

    private static int N, M;
    private static int input1, input2;
    private static Node[] people;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        init(br);
        int answer = bfs(input1);
        System.out.println(answer);
    }

    private static int bfs(int num) {
        boolean[] visited = new boolean[N + 1];
        Queue<Integer> nodeQueue = new LinkedList<>();
        nodeQueue.offer(num);
        visited[num] = true;
        int count = 0;
        int size = 1;
        boolean found = false;
        while (!nodeQueue.isEmpty()) {
            int temp = nodeQueue.poll();
            for (int adjacentNode : people[temp].adjacentNodes) {
                if (!visited[adjacentNode]) {
                    visited[adjacentNode] = true;
                    if (adjacentNode == input2) {
                        found = true;
                        break;
                    }
                    nodeQueue.offer(adjacentNode);
                }
            }
            if (found) {
                count++;
                break;
            }
            if (--size == 0) {
                size = nodeQueue.size();
                count++;
            }
        }
        return found ? count : -1;
    }

    private static void init(BufferedReader br) throws Exception {
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        input1 = Integer.parseInt(st.nextToken());
        input2 = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(br.readLine());
        people = new Node[N + 1];
        for (int i = 1; i <= N; i++) {
            people[i] = new Node(i);
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            people[y].adjacentNodes.add(x);
            people[x].adjacentNodes.add(y);
        }
    }

    private static class Node {
        int num;
        List<Integer> adjacentNodes = new ArrayList<>();

        public Node(int num) {
            this.num = num;
        }
    }
}