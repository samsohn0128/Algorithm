public class Solution {
    public int solution(int[] stones, int k) {
        int answer = 0;
        for (int i = 0; i < stones.length; i++) {
            int maxNumber = 0;
            for (int j = 0; j < k && i + j < stones.length; j++) {
                maxNumber = Math.max(maxNumber, stones[i + j]);
                System.out.println("j = " + j);
            }
            if (maxNumber == 0) {
                break;
            }
            answer = Math.min(answer, maxNumber);
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] stones = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
        int k = 3;
        int answer = s.solution(stones, k);
        System.out.println("answer = " + answer);
    }
}