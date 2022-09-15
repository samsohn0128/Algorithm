import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class 뱀과사다리게임_B16928 {

    private static int numberOfLadders;
    private static int numberOfSnakes;
    private static Map<Integer, Integer> ladders = new HashMap<>();
    private static Map<Integer, Integer> snakes = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = setBufferedReader();
        init(br);
        int answer = bfs();
        System.out.println(answer);
        br.close();
    }

    private static BufferedReader setBufferedReader() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        return new BufferedReader(new InputStreamReader(System.in));
    }

    private static void init(BufferedReader br) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        numberOfLadders = Integer.parseInt(st.nextToken());
        numberOfSnakes = Integer.parseInt(st.nextToken());

        for (int i = 0; i < numberOfLadders; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            ladders.put(start, end);
        }
        for (int i = 0; i < numberOfSnakes; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            snakes.put(start, end);
        }
    }

    private static int bfs() {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[101];
        queue.offer(1);
        visited[1] = true;

        int size = 1;
        int count = 0;
        while (!queue.isEmpty()) {
            int curNumber = queue.poll();
            boolean arrive = false;
            for (int i = 1; i <= 6; i++) {
                int nextNumber = curNumber + i;
                if (!visited[nextNumber]) {
                    visited[curNumber + i] = true;
                    nextNumber = checkLadderAndSnake(nextNumber);
                    if (nextNumber == 100) {
                        arrive = true;
                        break;
                    }
                    visited[nextNumber] = true;
                    queue.offer(nextNumber);
                }
            }
            if (arrive) {
                count++;
                break;
            }
            if (--size == 0) {
                size = queue.size();
                count++;
            }
        }
        return count;
    }

    private static int checkLadderAndSnake(int number) {
        if (ladders.containsKey(number)) {
            return ladders.get(number);
        } else if (snakes.containsKey(number)) {
            return snakes.get(number);
        } else {
            return number;
        }
    }
}