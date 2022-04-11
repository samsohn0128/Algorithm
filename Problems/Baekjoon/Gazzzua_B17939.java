import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Gazzzua_B17939 {

    private static int N;
    private static int[] coin;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        init(br);

        int answer = solution();
        System.out.println(answer);
        br.close();
    }

    private static int solution() {
        PriorityQueue<Node> maxCoinPQ = new PriorityQueue<>((n1, n2) -> {
            if (n2.value != n1.value)
                return n2.value - n1.value;
            else
                return n1.index - n2.index;
        });
        for (int i = 0; i < N; i++) {
            maxCoinPQ.offer(new Node(coin[i], i));
        }

        int answer = 0;
        Queue<Integer> coinQueue = new LinkedList<>();
        int maxCoin = maxCoinPQ.poll().value;
        for (int i = 0; i < N; i++) {
            if (coin[i] < maxCoin) {
                coinQueue.offer(coin[i]);
            } else {
                answer += calculateCoinQueue(coinQueue, maxCoin);
                maxCoin = nextMax(maxCoinPQ, i);
            }
        }

        return answer;
    }

    private static int nextMax(PriorityQueue<Node> maxCoinPQ, int curIndex) {
        while (!maxCoinPQ.isEmpty()) {
            Node temp = maxCoinPQ.poll();
            if (temp.index > curIndex) {
                return temp.value;
            }
        }
        return 0;
    }

    private static int calculateCoinQueue(Queue<Integer> coinQueue, int maxCoin) {
        int revenue = 0;
        while (!coinQueue.isEmpty()) {
            revenue += maxCoin - coinQueue.poll();
        }
        return revenue;
    }

    private static void init(BufferedReader br) throws Exception {
        N = Integer.parseInt(br.readLine());
        coin = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            coin[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static class Node {
        int value;
        int index;

        public Node(int value, int index) {
            this.value = value;
            this.index = index;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", index=" + index +
                    '}';
        }
    }
}