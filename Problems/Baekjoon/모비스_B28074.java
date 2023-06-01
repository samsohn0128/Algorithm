import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class 모비스_B28074 {
    private static String input;

    public static void main(String[] args) throws Exception {
        init();
        String answer = solution();
        System.out.println(answer);
    }

    private static void init() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine();
    }

    private static String solution() {
        String mobis = "MOBIS";
        boolean[] visited = new boolean[26];
        for (int i = 0; i < input.length(); i++) {
            visited[input.charAt(i) - 'A'] = true;
        }
        boolean ableToMake = true;
        for (int i = 0; i < mobis.length(); i++) {
            ableToMake &= visited[mobis.charAt(i) - 'A'];
        }
        return ableToMake ? "YES" : "NO";
    }
}