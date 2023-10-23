import java.util.*;

public class Solution {
    List<Integer> numberList = new LinkedList<>();

    public int[] solution(int[] numbers) {
        for (int number : numbers) {
            if (numberList.isEmpty() || numberList.get(numberList.size() - 1) != number) {
                numberList.add(number);
            }
        }
        return numberList.stream().mapToInt(i -> i).toArray();
    }
}