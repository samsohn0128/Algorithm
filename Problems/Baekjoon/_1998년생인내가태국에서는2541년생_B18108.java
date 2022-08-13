import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class _1998년생인내가태국에서는2541년생_B18108 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int boolgiAge = Integer.parseInt(br.readLine());
        System.out.println(boolgiAge - 543);
        br.close();
    }
}