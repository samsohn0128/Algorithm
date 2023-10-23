import java.util.HashMap;
import java.util.Map;

class Solution {

    Map<String, Integer> clothesCount = new HashMap<>();

    public int solution(String[][] clothes) {
        countClothes(clothes);
        int answer = 1;
        for (int count : clothesCount.values()) {
            answer *= count + 1;
        }
        return answer - 1;
    }

    void countClothes(String[][] clothes) {
        for (String[] cloth : clothes) {
            clothesCount.computeIfPresent(cloth[1], (k, v) -> ++v);
            clothesCount.putIfAbsent(cloth[1], 1);
        }
    }
}