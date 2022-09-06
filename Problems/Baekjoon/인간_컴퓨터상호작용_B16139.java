import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 인간_컴퓨터상호작용_B16139 {
    private static String inputString;
    private static int numberOfQuestions;
    private static Question[] questions;

    private static int[][] alphabetCounts;

    static class Question {
        private char alphabet;
        private int start;
        private int end;

        public Question(char alphabet, int start, int end) {
            this.alphabet = alphabet;
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = setBufferedReader();
        init(br);
        String answer = solution();
        System.out.println(answer);
        br.close();
    }

    private static BufferedReader setBufferedReader() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        return new BufferedReader(new InputStreamReader(System.in));
    }

    private static void init(BufferedReader br) throws Exception {
        inputString = br.readLine();

        numberOfQuestions = Integer.parseInt(br.readLine());
        questions = new Question[numberOfQuestions];
        for (int i = 0; i < numberOfQuestions; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char alphabet = st.nextToken().charAt(0);
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            questions[i] = new Question(alphabet, start, end);
        }
    }

    private static String solution() {
        setAlphabetCount();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numberOfQuestions; i++) {
            int alphabetCount = getAlphabetCount(questions[i].alphabet, questions[i].start, questions[i].end);
            sb.append(alphabetCount);
            sb.append("\n");
        }
        return sb.toString();
    }

    private static void setAlphabetCount() {
        alphabetCounts = new int[26][inputString.length()];
        for (int i = 0; i < 26; i++) {
            int alphabetCount = 0;
            for (int j = 0; j < inputString.length(); j++) {
                if (inputString.charAt(j) == (char) (i + 'a')) {
                    alphabetCount++;
                }
                alphabetCounts[i][j] = alphabetCount;
            }
        }
    }

    private static int getAlphabetCount(char alphabet, int start, int end) {
        int alphabetIndex = alphabet - 'a';
        if (start == 0) {
            return alphabetCounts[alphabetIndex][end];
        } else {
            return alphabetCounts[alphabetIndex][end] - alphabetCounts[alphabetIndex][start - 1];
        }
    }
}