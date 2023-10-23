import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static int solution(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }
        return Math.min(numSet.size(), nums.length / 2);
    }

    public static void main(String[] args) {
        int[] nums = {3, 1, 2, 3};
        int answer = solution(nums);
        System.out.println("answer = " + answer);
    }
}