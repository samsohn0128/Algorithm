import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class 연산자끼워넣기_B14888 {

    private static int numberOfNumbers;
    private static int[] numbers;
    private static int[] operators = new int[4];
    private static int maxAnswer = Integer.MIN_VALUE;
    private static int minAnswer = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        init();
        getMaxAndMinAnswer(numbers[0], 1);
        System.out.println(maxAnswer);
        System.out.println(minAnswer);
    }

    private static void init() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        numberOfNumbers = Integer.parseInt(br.readLine());
        numbers = new int[numberOfNumbers];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < numberOfNumbers; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            operators[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static void getMaxAndMinAnswer(int result, int index) {
        if (index == numberOfNumbers) {
            if (maxAnswer < result) {
                maxAnswer = result;
            }
            if (minAnswer > result) {
                minAnswer = result;
            }
            return;
        }
        for (int i = 0; i < 4; i++) {
            if (operators[i] > 0) {
                int nextResult = calculate(result, i, numbers[index]);
                operators[i]--;
                getMaxAndMinAnswer(nextResult, index + 1);
                operators[i]++;
            }
        }
    }

    private static int calculate(int result, int i, int nextNumber) {
        switch (i) {
            case 0:
                result += nextNumber;
                break;
            case 1:
                result -= nextNumber;
                break;
            case 2:
                result *= nextNumber;
                break;
            case 3:
                result /= nextNumber;
                break;
        }
        return result;
    }
}