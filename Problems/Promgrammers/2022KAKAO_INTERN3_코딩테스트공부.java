import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {
    private static final int MAX_COST = 100 * 100 + 1;
    private static Power start;
    private static Power end;
    private static Problem[] problems;
    private static int[][] costs = new int[501][501];

    public static int solution(int alp, int cop, int[][] problems) {
        init(alp, cop, problems);
        int answer = getAnswer();
        return answer;
    }

    private static void init(int alp, int cop, int[][] problemArr) {
        start = new Power(alp, cop);
        end = new Power();
        problems = Arrays.stream(problemArr)
                .map(Problem::new)
                .toArray(Problem[]::new);
        for (Problem problem : problems) {
            if (end.algoPower < problem.algoPowerRequired) {
                end.algoPower = problem.algoPowerRequired;
            }
            if (end.codingPower < problem.codingPowerRequired) {
                end.codingPower = problem.codingPowerRequired;
            }
        }
        for (int[] cost : costs) {
            Arrays.fill(cost, MAX_COST);
        }
    }

    private static int getAnswer() {
        int answer = 0;
        PriorityQueue<Power> powerPq = new PriorityQueue<>(Comparator.comparingInt(p -> p.cost));
        powerPq.offer(start);
        while (!powerPq.isEmpty()) {
            Power temp = powerPq.poll();
            if (temp.isAllSolvable()) {
                answer = temp.cost;
                break;
            }
            if (costs[temp.algoPower][temp.codingPower] <= temp.cost) {
                continue;
            } else {
                costs[temp.algoPower][temp.codingPower] = temp.cost;
            }
            Power newPower1 = new Power(temp.algoPower + 1, temp.codingPower, temp.cost + 1);
            Power newPower2 = new Power(temp.algoPower, temp.codingPower + 1, temp.cost + 1);
            powerPq.offer(newPower1);
            powerPq.offer(newPower2);
            for (Problem problem : problems) {
                if (temp.isSolvable(problem)) {
                    Power newPower3 = new Power(temp.algoPower + problem.algoPowerReward,
                            temp.codingPower + problem.codingPowerReward, temp.cost + problem.cost);
                    powerPq.offer(newPower3);
                }
            }
        }
        return answer;
    }

    private static class Power {
        private int algoPower;
        private int codingPower;
        private int cost;

        public Power() {
        }

        public Power(int algoPower, int codingPower) {
            this.algoPower = algoPower;
            this.codingPower = codingPower;
        }

        public Power(int algoPower, int codingPower, int cost) {
            this.algoPower = algoPower;
            this.codingPower = codingPower;
            this.cost = cost;
        }

        public boolean isSolvable(Problem problem) {
            return algoPower >= problem.algoPowerRequired && codingPower >= problem.codingPowerRequired;
        }

        public boolean isAllSolvable() {
            return algoPower >= end.algoPower && codingPower >= end.codingPower;
        }
    }

    private static class Problem {
        private int algoPowerRequired;
        private int codingPowerRequired;
        private int algoPowerReward;
        private int codingPowerReward;
        private int cost;

        public Problem(int[] problem) {
            this.algoPowerRequired = problem[0];
            this.codingPowerRequired = problem[1];
            this.algoPowerReward = problem[2];
            this.codingPowerReward = problem[3];
            this.cost = problem[4];
        }
    }

    public static void main(String[] args) {
        int alp = 10;
        int cop = 10;
        int[][] problems = {{10, 15, 2, 1, 2}, {20, 20, 3, 3, 4}};
        int answer = solution(alp, cop, problems);
        System.out.println(answer);
    }
}