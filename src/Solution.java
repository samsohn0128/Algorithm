import java.util.Arrays;

public class Solution {
    private static String[] binaryNumbers;

    public static int[] solution(long[] numbers) {
        init(numbers);
        int[] answer = {};
        return answer;
    }

    private static void init(long[] numbers) {
        binaryNumbers = Arrays.stream(numbers)
                .mapToObj(Long::toBinaryString)
                .toArray(String[]::new);
    }

    public static void main(String[] args) {
        long[] numbers = {7, 42, 5};
//        long[] numbers = {63, 111, 95};
        int[] answer = solution(numbers);
        System.out.println("answer = " + answer);
    }
}