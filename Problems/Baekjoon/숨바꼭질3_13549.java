import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 숨바꼭질3_13549 {

    private static int N;
    private static int K;
    private static final int MAX_NUMBER = 200000;
    private static boolean[] visited = new boolean[MAX_NUMBER + 1];

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
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
    }

    private static int solution() {
        PriorityQueue<Integer[]> pq = new PriorityQueue<>(Comparator.comparing(i -> i[1]));
        pq.offer(new Integer[]{N, 0});
        while (!pq.isEmpty()) {
            Integer[] temp = pq.poll();
            int curNumber = temp[0];
            int curTime = temp[1];
            visited[curNumber] = true;
            if (curNumber == K) {
                return curTime;
            }
            if (curNumber * 2 <= MAX_NUMBER && !visited[curNumber * 2]) {
                pq.offer(new Integer[]{curNumber * 2, curTime});
            }
            if (curNumber + 1 <= MAX_NUMBER && !visited[curNumber + 1]) {
                pq.offer(new Integer[]{curNumber + 1, curTime + 1});
            }
            if (curNumber - 1 >= 0 && !visited[curNumber - 1]) {
                pq.offer(new Integer[]{curNumber - 1, curTime + 1});
            }
        }
        return -1;
    }


}