import java.util.PriorityQueue;


public class SK1 {
    static int[] coin = {1, 5, 10, 50, 100, 500};


    public static int solution(int money, int[] costs) {
        int answer = 0;
        PriorityQueue<Triple> pq = new PriorityQueue<>((n1, n2) -> n1.first - n2.first);
        for (int i = 0; i < costs.length; i++) {
            pq.offer(new Triple(costs[i] * coin[5 - i], -coin[i], costs[i]));
        }
        int temp = money;
        while (!pq.isEmpty()) {
            Triple tempTriple = pq.poll();
            int coin = -tempTriple.second;
            int cost = tempTriple.third;
            answer += (temp / coin) * cost;
            temp %= coin;
        }
        return answer;
    }

    private static class Triple {
        int first;
        int second;
        int third;

        public Triple(int first, int second, int third) {
            this.first = first;
            this.second = second;
            this.third = third;
        }
    }

    public static void main(String[] args) {
//        int money = 4578;
//        int[] costs = {1, 4, 99, 35, 50, 1000};
        int money = 1999;
        int[] costs = {2, 11, 20, 100, 200, 600};
        int answer = solution(money, costs);
        System.out.println("answer = " + answer);
    }
}