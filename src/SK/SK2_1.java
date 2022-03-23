package SK;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class SK2_1 {
    private List<String> subStrList = new LinkedList<>();
    private int maxLength;

    public String[] solution(String[] goods) {
        String[] answer = new String[goods.length];
        maxLength = Arrays.stream(goods).max(Comparator.comparingInt(String::length)).orElseThrow().length();
        for (int i = 1; i <= maxLength; i++) {
            for (int j = 0; j < goods.length; j++) {
                for (int k = 0; k < goods[j].length(); k++) {
                    if (k + i <= goods[j].length()) {
                        String subStr = goods[j].substring(k, k + i);
                        subStrList.add(subStr);
                    }
                }
            }
            subStrList = subStrList.stream().distinct().sorted().collect(Collectors.toList());
//            subStrList.forEach(System.out::println);
            subStrList.forEach(subStr -> {
                int count = 0;
                int countIdx = -1;
                for (int j = 0; j < goods.length; j++) {
                    if (goods[j].contains(subStr)) {
                        count++;
                        countIdx = j;
                    }
                }
                if (count == 1) {
                    if (answer[countIdx] == null || answer[countIdx].isEmpty()) {
                        answer[countIdx] = subStr;
                    } else if (answer[countIdx].split(" ")[0].length() == subStr.length()) {
                        answer[countIdx] += " " + subStr;
                    }
                }
            });
            subStrList.clear();
        }
        for (int i = 0; i < answer.length; i++) {
            if (answer[i] == null) {
                answer[i] = "None";
            }
        }
        return answer;
    }
}