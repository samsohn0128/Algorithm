import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 랜선자르기_B1654 {
    private static int numberOfLanCable;
    private static int requireNumberOfLanCable;
    private static int[] lanCables;

    public static void main(String[] args) throws Exception {
        BufferedReader br = setBufferedReader();
        init(br);
        int answer = solution();
        System.out.println(answer);
        br.close();
    }

    private static BufferedReader setBufferedReader() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        return new BufferedReader(new InputStreamReader(System.in));
    }

    private static void init(BufferedReader br) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        numberOfLanCable = Integer.parseInt(st.nextToken());
        requireNumberOfLanCable = Integer.parseInt(st.nextToken());

        lanCables = new int[numberOfLanCable];
        for (int i = 0; i < numberOfLanCable; i++) {
            lanCables[i] = Integer.parseInt(br.readLine());
        }
    }

    private static int solution() {
        int maxLength = getMaxLengthOfCables();
        int answer = binarySearch(maxLength);
        return answer;
    }

    private static int getMaxLengthOfCables() {
        int maxLength = 0;
        for (int i = 0; i < numberOfLanCable; i++) {
            if (maxLength < lanCables[i]) {
                maxLength = lanCables[i];
            }
        }
        return maxLength;
    }

    private static int binarySearch(int maxLength) {
        long left = 1;
        long right = maxLength;
        long answer = 0;
        while (left <= right) {
            long middle = (left + right) / 2;
            System.out.print("left = " + left);
            System.out.print(", middle = " + middle);
            System.out.println(", right = " + right);
            if (sufficientLanCables(middle)) {
                answer = middle;
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return (int) answer;
    }

    private static boolean sufficientLanCables(long cableLength) {
        int cableCount = 0;
        for (int i = 0; i < numberOfLanCable; i++) {
            cableCount += lanCables[i] / cableLength;
        }
        return cableCount >= requireNumberOfLanCable;
    }
}