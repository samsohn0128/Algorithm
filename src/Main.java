import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {
        init();
        String answer = solution();
        System.out.println(answer);
    }

    private static void init() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        br.close();
    }

    private static String solution() {
        StringBuilder sb = new StringBuilder();

        return sb.toString();
    }

}