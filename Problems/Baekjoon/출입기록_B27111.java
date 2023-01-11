import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class 출입기록_B27111 {

    private static int N;
    private static Record[] records;

    public static void main(String[] args) throws Exception {
        init();
        String answer = solution();
        System.out.println(answer);
    }

    private static void init() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        records = new Record[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int number = Integer.parseInt(st.nextToken());
            boolean isIn = "1".equals(st.nextToken());
            records[i] = new Record(number, isIn);
        }

        br.close();
    }

    private static String solution() {
        StringBuilder sb = new StringBuilder();
        int answer = 0;
        Set<Integer> numberSet = new HashSet<>();
        for (Record record : records) {
            if (record.isIn) {
                if (numberSet.contains(record.number)) {
                    answer++;
                } else {
                    numberSet.add(record.number);
                }
            } else if (!numberSet.remove(record.number)) {
                answer++;
            }
        }
        answer += numberSet.size();
        sb.append(answer);
        return sb.toString();
    }

    private static class Record {
        private int number;
        private boolean isIn;

        public Record(int number, boolean isIn) {
            this.number = number;
            this.isIn = isIn;
        }
    }

}