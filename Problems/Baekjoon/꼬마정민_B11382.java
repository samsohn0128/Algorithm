import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 꼬마정민_B11382 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long answer = Arrays.stream(br.readLine().split(" "))
                .mapToLong(Long::parseLong)
                .sum();
        System.out.println(answer);
    }
}