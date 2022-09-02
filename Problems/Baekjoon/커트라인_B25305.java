import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class 커트라인_B25305 {

    private static int numberOfCandidate;
    private static int numberOfPrized;
    private static int[] scores;

    public static void main(String[] args) throws Exception {
        init();
        int answer = solution();
        System.out.println(answer);
    }

    private static void init() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        numberOfCandidate = Integer.parseInt(st.nextToken());
        numberOfPrized = Integer.parseInt(st.nextToken());

        scores = new int[numberOfCandidate];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < numberOfCandidate; i++) {
            scores[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static int solution() {
        Arrays.sort(scores);
        return scores[scores.length - numberOfPrized];
    }
}