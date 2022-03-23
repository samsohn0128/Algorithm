import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 평범한배낭_B12865 {

    private static int N, K;
    private static Stuff[] stuffs;
    private static int[][] maxValue;

    public static void main(String[] args) throws Exception {
        init();
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= K; j++) {
                if (j - stuffs[i].weight >= 0) {
                    maxValue[i][j] = Math.max(maxValue[i - 1][j], maxValue[i - 1][j - stuffs[i].weight] + stuffs[i].value);
                } else {
                    maxValue[i][j] = maxValue[i - 1][j];
                }
            }
        }
        int answer = 0;
        for (int i = 1; i <= N; i++) {
            answer = Math.max(answer, maxValue[i][K]);
        }
        System.out.println(answer);
    }

    private static void init() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        stuffs = new Stuff[N + 1];
        maxValue = new int[N + 1][K + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int W = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            stuffs[i] = new Stuff(W, V);
        }
    }

    private static class Stuff {
        private int weight;
        private int value;

        public Stuff(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }
}