import java.util.Arrays;

public class 양궁대회_2022KAKAO3 {
    private static final int NUMBER_OF_SCORES = 11;

    private static int numberOfArrows;
    private static int[] apeachScores;
    private static int maxScoreDiff = 0;
    private static int[] ryanScores = new int[NUMBER_OF_SCORES];
    private static int[] answerScores = {-1};

    public static int[] solution(int n, int[] info) {
        init(n, info);
        setRyanScores(numberOfArrows, 0);
        return answerScores;
    }

    private static void setRyanScores(int remainingNumberOfArrows, int scoreIndex) {
        if (remainingNumberOfArrows == 0 || scoreIndex == NUMBER_OF_SCORES) {
            if (remainingNumberOfArrows > 0) {
                ryanScores[NUMBER_OF_SCORES - 1] += remainingNumberOfArrows;
            }
            int ryanScoreDiff = calculateScoreDiff(ryanScores);
            if (ryanScoreDiff > maxScoreDiff) {
                answerScores = Arrays.copyOf(ryanScores, NUMBER_OF_SCORES);
                maxScoreDiff = ryanScoreDiff;
            } else if (ryanScoreDiff == maxScoreDiff && hasMoreLowScores()) {
                answerScores = Arrays.copyOf(ryanScores, NUMBER_OF_SCORES);
            }
            ryanScores[NUMBER_OF_SCORES - 1] = 0;
            return;
        }
        int winNumberOfArrows = apeachScores[scoreIndex] + 1;
        if (winNumberOfArrows <= remainingNumberOfArrows) {
            ryanScores[scoreIndex] = winNumberOfArrows;
            setRyanScores(remainingNumberOfArrows - winNumberOfArrows, scoreIndex + 1);
            ryanScores[scoreIndex] = 0;
        }
        setRyanScores(remainingNumberOfArrows, scoreIndex + 1);
    }

    private static boolean hasMoreLowScores() {
        int index = NUMBER_OF_SCORES - 1;
        while (index >= 0) {
            if (ryanScores[index] > answerScores[index]) {
                return true;
            } else if (ryanScores[index] < answerScores[index]) {
                return false;
            }
            index--;
        }
        return false;
    }

    private static int calculateScoreDiff(int[] scores) {
        int apeachScore = 0;
        int ryanScore = 0;
        for (int i = 0; i < NUMBER_OF_SCORES; i++) {
            if (apeachScores[i] < scores[i]) {
                ryanScore += 10 - i;
            } else if (apeachScores[i] != 0) {
                apeachScore += 10 - i;
            }
        }
        return ryanScore > apeachScore ? ryanScore - apeachScore : -1;
    }

    private static void init(int n, int[] info) {
        numberOfArrows = n;
        apeachScores = info;
    }

    public static void main(String[] args) {
        int n = 5;
        int[] info = {2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0};
//        int n = 1;
//        int[] info = {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
//        int n = 9;
//        int[] info = {0, 0, 1, 2, 0, 1, 1, 1, 1, 1, 1};
//        int n = 10;
//        int[] info = {0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 3};
        int[] answer = solution(n, info);
        System.out.println("answer = " + Arrays.toString(answer));
    }
}