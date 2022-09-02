import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

class Main {

    public static void main(String[] args) throws Exception {
        init();
    }

    private static void init() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    }
}