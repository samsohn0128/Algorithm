import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        init(br);
        br.close();
    }

    private static void init(BufferedReader br) throws Exception {
        
    }
}