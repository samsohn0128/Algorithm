import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class 포도주시식_B2156 {
    private static int N;
    private static int[] wines;
    private static int[] maxWines;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        init(br);
        int answer = getMaxWine();
        System.out.println(answer);
        br.close();
    }

    private static int getMaxWine() {
        maxWines[1] = wines[1];
        maxWines[2] = maxWines[1] + wines[2];
        for (int i = 3; i <= N; i++) {
            if (i - 1 > 0) {
                maxWines[i] = Math.max(maxWines[i - 3] + wines[i - 1] + wines[i], Math.max(maxWines[i - 2] + wines[i], maxWines[i - 1]));
            }
        }
        return maxWines[N];
    }

    private static void init(BufferedReader br) throws Exception {
        N = Integer.parseInt(br.readLine());
        wines = new int[N + 2];
        maxWines = new int[N + 2];
        for (int i = 1; i <= N; i++) {
            wines[i] = Integer.parseInt(br.readLine());
        }
    }

}