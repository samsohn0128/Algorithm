import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

class 서로다른부분문자열의개수_B11478 {

    private static String inputString;

    public static void main(String[] args) throws Exception {
        init();
        int answer = solution();
        System.out.println(answer);
    }

    private static void init() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        inputString = br.readLine();
    }

    private static int solution() {
        Set<String> substringSet = new HashSet<>();
        for (int i = 0; i < inputString.length(); i++) {
            for (int j = i + 1; j <= inputString.length(); j++) {
                substringSet.add(inputString.substring(i, j));
            }
        }
        return substringSet.size();
    }
}