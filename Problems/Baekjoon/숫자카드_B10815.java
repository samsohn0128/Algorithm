import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

class 숫자카드_B10815 {

    private static int numberOfCards;
    private static int numberOfIntegers;

    private static Set<Integer> cardSet = new HashSet<>();
    private static int[] integers;

    public static void main(String[] args) throws Exception {
        init();
        String answer = solution();
        System.out.println(answer);
    }

    private static void init() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        numberOfCards = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < numberOfCards; i++) {
            cardSet.add(Integer.parseInt(st.nextToken()));
        }

        numberOfIntegers = Integer.parseInt(br.readLine());
        integers = new int[numberOfIntegers];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < numberOfIntegers; i++) {
            integers[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static String solution() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numberOfIntegers; i++) {
            if (i == numberOfIntegers - 1) {
                if (cardSet.contains(integers[i])) {
                    sb.append(1);
                } else {
                    sb.append(0);
                }
            } else {
                if (cardSet.contains(integers[i])) {
                    sb.append(1 + " ");
                } else {
                    sb.append(0 + " ");
                }
            }
        }
        return sb.toString();
    }
}