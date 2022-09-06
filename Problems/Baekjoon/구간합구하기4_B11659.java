import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 구간합구하기4_B11659 {

    private static int numberOfNumbers;
    private static int numberOfSums;
    private static int[] numbers;
    private static int[] accumulateSums;
    private static int[][] sections;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        init(br);
        String answer = solution();
        System.out.println(answer);
    }

    private static void init(BufferedReader br) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        numberOfNumbers = Integer.parseInt(st.nextToken());
        numberOfSums = Integer.parseInt(st.nextToken());

        numbers = new int[numberOfNumbers + 1];
        accumulateSums = new int[numberOfNumbers + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= numberOfNumbers; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        sections = new int[numberOfSums][2];
        for (int i = 0; i < numberOfSums; i++) {
            st = new StringTokenizer(br.readLine());
            sections[i][0] = Integer.parseInt(st.nextToken());
            sections[i][1] = Integer.parseInt(st.nextToken());
        }
    }

    private static String solution() {
        setAccumulateSum();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numberOfSums; i++) {
            int sectionSum = getSectionSum(sections[i][0], sections[i][1]);
            sb.append(sectionSum);
            sb.append("\n");
        }
        return sb.toString();
    }

    private static void setAccumulateSum() {
        accumulateSums[1] = numbers[1];
        for (int i = 2; i <= numberOfNumbers; i++) {
            accumulateSums[i] = accumulateSums[i - 1] + numbers[i];
        }
    }

    private static int getSectionSum(int start, int end) {
        return accumulateSums[end] - accumulateSums[start - 1];
    }
}