import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class K번째수_B1300 {

    private static int sizeOfA;
    private static int K;

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
        sizeOfA = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
    }

    private static int solution() {
        int answer = binarySearch(K);
        return answer;
    }

    private static int binarySearch(int number) {
        int left = 1;
        int right = number;
        int answer = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            int count = countUnderMid(mid);
            if (K <= count) {
                right = mid - 1;
                answer = mid;
            } else if (count < K) {
                left = mid + 1;
            }
        }
        return answer;
    }

    private static int countUnderMid(int mid) {
        int count = 0;
        for (int i = 1; i <= sizeOfA; i++) {
            count += Math.min(mid / i, sizeOfA);
        }
        return count;
    }
}