import java.util.HashMap;
import java.util.Map;

public class Solution {
    private static Map<String, Integer> scoreMap = new HashMap<>();

    public static String solution(String[] survey, int[] choices) {
        init();
        countScore(survey, choices);
        StringBuilder sb = new StringBuilder();
        scoreMap.forEach((k, v) -> {
            if (v > 0) {
                sb.append(k.charAt(1));
            } else {
                sb.append(k.charAt(0));
            }
        });
        return sb.toString();
    }

    private static void init() {
        scoreMap.put("RT", 0);
        scoreMap.put("CF", 0);
        scoreMap.put("JM", 0);
        scoreMap.put("AN", 0);
    }

    private static void countScore(String[] survey, int[] choices) {
        for (int i = 0; i < survey.length; i++) {
            int finalI = i;
            scoreMap.computeIfPresent(survey[i], (key, value) -> value += choices[finalI] - 4);
            scoreMap.computeIfPresent(getReversedStr(survey[i]), (key, value) -> value += -choices[finalI] + 4);
        }
    }

    private static String getReversedStr(String str) {
        return String.valueOf(str.charAt(1)) + str.charAt(0);
    }

    public static void main(String[] args) {
        String[] survey = {"AN", "CF", "MJ", "RT", "NA"};
        int[] choices = {5, 3, 2, 7, 5};
        String answer = solution(survey, choices);
        System.out.println(answer);
    }
}