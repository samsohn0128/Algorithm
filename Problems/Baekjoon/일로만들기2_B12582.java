import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class 일로만들기2_B12582 {

    private static int N;
    private static List<Integer>[] leastOperatorCounts;

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
        N = Integer.parseInt(br.readLine());

        leastOperatorCounts = new List[N + 1];
        for (int i = 0; i < leastOperatorCounts.length; i++) {
            leastOperatorCounts[i] = new ArrayList<>();
        }
        leastOperatorCounts[1].add(1);
        if (leastOperatorCounts.length > 2) {
            leastOperatorCounts[2].add(1);
            leastOperatorCounts[2].add(2);
        }
        if (leastOperatorCounts.length > 3) {
            leastOperatorCounts[3].add(1);
            leastOperatorCounts[3].add(3);
        }
    }

    private static String solution() {
        StringBuilder sb = new StringBuilder();
        countOperators(N);
        sb.append(leastOperatorCounts[N].size() - 1);
        sb.append("\n");
        for (int i = leastOperatorCounts[N].size() - 1; i >= 0; i--) {
            sb.append(leastOperatorCounts[N].get(i));
            sb.append(" ");
        }
        return sb.toString();
    }

    private static List<Integer> countOperators(int number) {
        if (!leastOperatorCounts[number].isEmpty()) {
            return leastOperatorCounts[number];
        }
        if (number % 3 == 0) {
            List<Integer> tempList = countOperators(number / 3);
            setLeastOperatorCounts(number, tempList);
        }
        if (number % 2 == 0) {
            List<Integer> tempList = countOperators(number / 2);
            setLeastOperatorCounts(number, tempList);
        }
        List<Integer> tempList = countOperators(number - 1);
        setLeastOperatorCounts(number, tempList);
        return leastOperatorCounts[number];
    }

    private static void setLeastOperatorCounts(int number, List<Integer> tempList) {
        if (leastOperatorCounts[number].isEmpty() || leastOperatorCounts[number].size() > tempList.size() + 1) {
            leastOperatorCounts[number].clear();
            leastOperatorCounts[number].addAll(tempList);
            leastOperatorCounts[number].add(number);
        }
    }
}