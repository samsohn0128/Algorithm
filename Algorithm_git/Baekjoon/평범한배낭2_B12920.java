import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 평범한배낭2_B12920 {
    private static int N, M;
    private static List<Stuff> stuffList = new ArrayList<>();
    private static int[][] maxValue;

    public static void main(String[] args) throws Exception {
        init();
        for (int i = 1; i < N; i++) {
            for (int j = 0; j <= M; j++) {
                int weight = stuffList.get(i).weight;
                int value = stuffList.get(i).value;
                if (j < weight) {
                    maxValue[i][j] = maxValue[i - 1][j];
                } else {
                    maxValue[i][j] = Math.max(maxValue[i - 1][j], maxValue[i - 1][j - weight] + value);
                }
            }
        }
        System.out.println(maxValue[N - 1][M]);
    }

    private static void init() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        stuffList.add(new Stuff(0, 0));
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());
            while (num > 0) {
                for (int j = 0; 1 << j <= num; j++) {
                    stuffList.add(new Stuff(weight * (1 << j), value * (1 << j)));
                    num -= 1 << j;
                }
            }
        }
        N = stuffList.size();
        maxValue = new int[N][M + 1];
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