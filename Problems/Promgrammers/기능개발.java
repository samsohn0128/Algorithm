import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] days = calculateDays(progresses, speeds);
        int[] answer = getAnswer(days);
        return answer;
    }

    int[] calculateDays(int[] progresses, int[] speeds) {
        int[] days = new int[progresses.length];
        for (int i = 0; i < progresses.length; i++) {
            days[i] = ((100 - progresses[i]) + (speeds[i] - 1)) / speeds[i];
        }
        return days;
    }

    int[] getAnswer(int[] days) {
        List<Integer> deployCount = new ArrayList<>();
        int currentDay = 0;
        int count = 0;
        for (int day: days) {
            if(currentDay < day) {
                if (count > 0) {
                    deployCount.add(count);
                }
                currentDay = day;
                count = 1;
            } else {
                count++;
            }
        }
        deployCount.add(count);
        return deployCount.stream().mapToInt(i->i).toArray();
    }
}