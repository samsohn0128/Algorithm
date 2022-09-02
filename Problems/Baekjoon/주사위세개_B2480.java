import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class 주사위세개_B2480 {

    private static int[] dices = new int[7];

    public static void main(String[] args) throws Exception {
        init();
        int answer = solution();
        System.out.println(answer);
    }

    private static void init() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 3; i++) {
            dices[Integer.parseInt(st.nextToken())]++;
        }
    }

    private static int solution() {
        int answer = 0;
        int max = 0;
        for (int i = 1; i <= 6; i++) {
            if (dices[i] == 3) {
                answer = 10000 + i * 1000;
                break;
            } else if (dices[i] == 2) {
                answer = 1000 + i * 100;
                break;
            } else if (dices[i] == 1) {
                max = i;
            }
        }
        if (answer == 0) {
            answer = max * 100;
        }
        return answer;
    }
}