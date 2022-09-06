import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;

public class 스택수열_B1874 {
    private static int numberOfNumbers;
    private static int[] arr;
    private static Stack<Integer> numStack = new Stack<>();
    private static int curNum = 1;

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
        numberOfNumbers = Integer.parseInt(br.readLine());
        arr = new int[numberOfNumbers];
        for (int i = 0; i < numberOfNumbers; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
    }

    private static String solution() {
        StringBuilder sb = new StringBuilder();
        boolean ableToMake = true;
        for (int i = 0; i < numberOfNumbers; i++) {
            if (arr[i] >= curNum) {
                sb.append(pushUntilNum(arr[i]));
            }
            if (!numStack.isEmpty() && numStack.peek() == arr[i]) {
                numStack.pop();
                sb.append("-\n");
            } else {
                ableToMake = false;
                break;
            }
        }

        return ableToMake ? sb.toString() : "NO";
    }

    private static StringBuilder pushUntilNum(int num) {
        StringBuilder sb = new StringBuilder();
        while (curNum <= num) {
            numStack.push(curNum++);
            sb.append("+\n");
        }
        return sb;
    }


}