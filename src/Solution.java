import java.util.Arrays;

public class Solution {


    public static int[] solution(long[] numbers) {
        int[] answer = {};
        return answer;
    }

    private static String[] convertToBinaryString(long[] numbers) {
        return Arrays.stream(numbers)
                .mapToObj(Long::toBinaryString)
                .toArray(String[]::new);
    }

    public static void main(String[] args) {
        long[] numbers = {7, 42, 5};
//        long[] numbers = {63, 111, 95};
        int[] answer = solution(numbers);
        System.out.println("answer = " + Arrays.toString(answer));
    }
}