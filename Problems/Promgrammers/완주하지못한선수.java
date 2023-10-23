import java.util.HashMap;
import java.util.Map;

class Solution {
    public String solution(String[] participants, String[] completions) {
        Map<String, Integer> nameCount = new HashMap<>();
        for (String participant : participants) {
            nameCount.putIfAbsent(participant, 1);
        }
        String answer = "";
        return answer;
    }
}