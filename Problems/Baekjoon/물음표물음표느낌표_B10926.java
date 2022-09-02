import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class 물음표물음표느낌표_B10926 {

    private static String input;

    public static void main(String[] args) throws Exception {
        init();
        StringBuilder sb = new StringBuilder(input);
        sb.append("??!");
        System.out.println(sb);
    }

    private static void init() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine();
    }
}