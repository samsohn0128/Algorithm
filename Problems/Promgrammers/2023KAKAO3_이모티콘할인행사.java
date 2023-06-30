import java.util.Arrays;

public class 이모티콘할인행사_2023KAKAO3 {
    private static final int[] answer = new int[2];
    private static int[] discounts;
    private static int[][] users;
    private static int[] emoticons;

    public static int[] solution(int[][] users, int[] emoticons) {
        init(users, emoticons);
        setDiscountsRecursively(0);
        return answer;
    }

    private static void init(int[][] usersTemp, int[] emoticonsTemp) {
        users = usersTemp;
        emoticons = emoticonsTemp;
        discounts = new int[emoticons.length];
    }

    public static void setDiscountsRecursively(int index) {
        if (index == discounts.length) {
            calculateAnswer();
            return;
        }
        while (discounts[index] < 40) {
            discounts[index] += 10;
            setDiscountsRecursively(index + 1);
        }
        discounts[index] = 0;
    }

    private static void calculateAnswer() {
        int[] subscribersAndProfits = calculateSubscribersAndProfits();
        if (answer[0] < subscribersAndProfits[0]) {
            answer[0] = subscribersAndProfits[0];
            answer[1] = subscribersAndProfits[1];
        } else if (answer[0] == subscribersAndProfits[0] && answer[1] < subscribersAndProfits[1]) {
            answer[1] = subscribersAndProfits[1];
        }
    }

    private static int[] calculateSubscribersAndProfits() {
        int subscribers = 0;
        int profits = 0;
        for (int[] user : users) {
            int cost = calculateCost(user);

            if (cost >= user[1]) {
                subscribers++;
            } else {
                profits += cost;
            }
        }
        return new int[]{subscribers, profits};
    }

    private static int calculateCost(int[] user) {
        int cost = 0;
        for (int j = 0; j < discounts.length; j++) {
            if (discounts[j] >= user[0]) {
                cost += getDiscountedEmoticon(j);
            }
        }
        return cost;
    }

    private static int getDiscountedEmoticon(int i) {
        return emoticons[i] * (100 - discounts[i]) / 100;
    }

    public static void main(String[] args) {
//        int[][] users = {{40, 10000}, {25, 10000}};
//        int[] emoticons = {7000, 9000};
        int[][] users = {{40, 2900}, {23, 10000}, {11, 5200}, {5, 5900}, {40, 3100}, {27, 9200}, {32, 6900}};
        int[] emoticons = {1300, 1500, 1600, 4900};

        int[] answer = solution(users, emoticons);
        System.out.println("answer = " + Arrays.toString(answer));
    }
}