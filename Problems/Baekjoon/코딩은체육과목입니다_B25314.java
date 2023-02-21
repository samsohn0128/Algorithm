import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class 코딩은체육과목입니다_B25314 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N / 4; i++) {
            sb.append("long ");
        }
        sb.append("int");
        System.out.println(sb);
    }
}