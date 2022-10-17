import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 숨바꼭질4_B13913 {
    private static int N, K;
    private static Queue<Integer> queue = new LinkedList<>();
    private static int[] visited = new int[200001];

    public static void main(String[] args) throws Exception {
        init();
        String answer = solution();
        System.out.println(answer);
    }

    private static void init() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        for (int i = 0; i < visited.length; i++) {
            visited[i] = -1;
        }
    }

    private static String solution() {
        StringBuilder sb = new StringBuilder();
        int spentTime = bfs();
        sb.append(spentTime);
        sb.append("\n");
        printPath(K, sb);
        return sb.toString();
    }

    private static void printPath(int num, StringBuilder sb) {
        if (visited[num] == num) {
            sb.append(num);
            sb.append(" ");
            return;
        }
        printPath(visited[num], sb);
        sb.append(num);
        sb.append(" ");
    }

    private static int bfs() {
        queue.offer(N);
        visited[N] = N;
        int size = 1;
        int spentTime = 0;
        while (!queue.isEmpty()) {
            int temp = queue.poll();
            if (temp * 2 <= 200000) {
                if (visited[temp * 2] == -1) {
                    visited[temp * 2] = temp;
                    queue.offer(temp * 2);
                    if (K == temp * 2) {
                        return spentTime + 1;
                    }
                }
            }
            if (temp - 1 >= 0) {
                if (visited[temp - 1] == -1) {
                    visited[temp - 1] = temp;
                    queue.offer(temp - 1);
                    if (K == temp - 1) {
                        return spentTime + 1;
                    }
                }
            }
            if (temp + 1 <= 100000) {
                if (visited[temp + 1] == -1) {
                    visited[temp + 1] = temp;
                    queue.offer(temp + 1);
                    if (K == temp + 1) {
                        return spentTime + 1;
                    }
                }
            }
            if (--size == 0) {
                spentTime++;
                size = queue.size();
            }
        }
        return 0;
    }
}