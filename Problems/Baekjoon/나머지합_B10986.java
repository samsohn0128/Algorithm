import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 나머지합_B10986 {

    private static int numberOfNumbers;
    private static int modNumber;
    private static int[] numbers;
    private static int[] accumulateMods;
    private static int[] countOfMods;

    public static void main(String[] args) throws Exception {
        BufferedReader br = setBufferedReader();
        init(br);
        long answer = solution();
        System.out.println(answer);
        br.close();
    }

    private static BufferedReader setBufferedReader() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        return new BufferedReader(new InputStreamReader(System.in));
    }

    private static void init(BufferedReader br) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        numberOfNumbers = Integer.parseInt(st.nextToken());
        modNumber = Integer.parseInt(st.nextToken());

        numbers = new int[numberOfNumbers];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < numberOfNumbers; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static long solution() {
        setAccumulateMods();
        setCountOfMods();
        long answer = getAnswer();
        return answer;
    }

    private static void setAccumulateMods() {
        accumulateMods = new int[numberOfNumbers];
        accumulateMods[0] = numbers[0] % modNumber;
        for (int i = 1; i < numberOfNumbers; i++) {
            accumulateMods[i] = (accumulateMods[i - 1] + numbers[i]) % modNumber;
        }
    }

    private static void setCountOfMods() {
        countOfMods = new int[modNumber];
        for (int i = 0; i < modNumber; i++) {
            int countOfMod = 0;
            for (int j = 0; j < numberOfNumbers; j++) {
                if (accumulateMods[j] == i) {
                    countOfMod++;
                }
            }
            countOfMods[i] = countOfMod;
        }
    }

    private static long getAnswer() {
        long answer = (long) countOfMods[0] * (countOfMods[0] + 1) / 2;
        for (int i = 1; i < modNumber; i++) {
            answer += (long) countOfMods[i] * (countOfMods[i] + 1) / 2 - countOfMods[i];
        }
        return answer;
    }
}