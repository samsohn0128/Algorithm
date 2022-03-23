import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 짐챙기는숌_B1817 {
    private static int N, M;
    private static int[] books;


    public static void main(String[] args) throws Exception {
        init();
        int answer = 0;
        int curWeight = 0;
        for (int i = 0; i < N; i++) {
            curWeight += books[i];
            if (curWeight > M) {
                answer++;
                curWeight = books[i];
            }
        }
        if (N > 0)
            answer++;
        System.out.println(answer);
    }

    private static void init() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        if (N > 0) {
            books = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                books[i] = Integer.parseInt(st.nextToken());
            }
        }
    }
}