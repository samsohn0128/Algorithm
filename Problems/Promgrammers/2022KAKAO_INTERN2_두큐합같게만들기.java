import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    private static Queue<Long> q1 = new LinkedList<>();
    private static Queue<Long> q2 = new LinkedList<>();
    private static int answer = -1;

    public static int solution(int[] queue1, int[] queue2) {
        init(queue1, queue2);
        long sum1 = getQueueSum(queue1);
        long sum2 = getQueueSum(queue2);
        moveNumbers(sum1, sum2);
        return answer;
    }

    private static void init(int[] queue1, int[] queue2) {
        for (int i = 0; i < queue1.length; i++) {
            q1.offer((long) queue1[i]);
            q2.offer((long) queue2[i]);
        }
    }

    private static void moveNumbers(long sum1, long sum2) {
        int size = q1.size();
        int count = 0;
        while (sum1 != sum2 && count <= size * 3) {
            while (sum1 < sum2) {
                count++;
                long number = q2.poll();
                sum1 += number;
                sum2 -= number;
                q1.offer(number);
            }
            while (sum1 > sum2) {
                count++;
                long number = q1.poll();
                sum1 -= number;
                sum2 += number;
                q2.offer(number);
            }
        }
        if (count <= size * 3) {
            answer = count;
        }
    }

    private static long getQueueSum(int[] q) {
        long sum = 0;
        for (int i = 0; i < q.length; i++) {
            sum += q[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] queue1 = {3, 2, 7, 2};
        int[] queue2 = {4, 6, 5, 1};

        int answer = solution(queue1, queue2);
        System.out.println(answer);
    }
}