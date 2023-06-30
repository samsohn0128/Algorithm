import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class 개인정보수집유효기간_2023KAKAO1 {
    private static LocalDate today;
    private static final int[] termsMonth = new int[26];
    private static Privacy[] privacies;

    public static int[] solution(String today, String[] terms, String[] privacies) {
        init(today, terms, privacies);
        List<Integer> answer = getAnswer();
        return answer.stream()
                .mapToInt(i -> i)
                .toArray();
    }

    private static void init(String todayStr, String[] termsStr, String[] privaciesStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        today = LocalDate.parse(todayStr, formatter);

        privacies = new Privacy[privaciesStr.length];
        for (int i = 0; i < termsStr.length; i++) {
            String[] splitTerms = termsStr[i].split(" ");
            int indexOfTerms = getIndexOfTerms(splitTerms[0]);
            termsMonth[indexOfTerms] = Integer.parseInt(splitTerms[1]);
        }
        for (int i = 0; i < privaciesStr.length; i++) {
            String[] splitPrivacies = privaciesStr[i].split(" ");
            privacies[i] = new Privacy(LocalDate.parse(splitPrivacies[0], formatter), splitPrivacies[1]);
        }
    }

    private static List<Integer> getAnswer() {
        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < privacies.length; i++) {
            LocalDate expiredDate = privacies[i].registerDate.plusMonths(termsMonth[getIndexOfTerms(privacies[i].type)]);
            if (expiredDate.isBefore(today) || expiredDate.isEqual(today)) {
                answer.add(i + 1);
            }
        }
        return answer;
    }

    private static int getIndexOfTerms(String s) {
        return s.charAt(0) - 'A';
    }

    private static class Privacy {
        LocalDate registerDate;
        String type;

        public Privacy(LocalDate registerDate, String type) {
            this.registerDate = registerDate;
            this.type = type;
        }
    }

    public static void main(String[] args) {
        String today = "2020.01.01";
        String[] terms = {"Z 3", "D 5"};
        String[] privacies = {"2019.01.01 D", "2019.11.15 Z", "2019.08.02 D", "2019.07.01 D", "2018.12.28 Z"};
        int[] answer = solution(today, terms, privacies);
        System.out.println("answer = " + Arrays.toString(answer));
    }
}