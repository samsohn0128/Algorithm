import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class 명령프롬프트_B1032 {

    static int N;
    static String[] files;
    static Boolean[] sameChar;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        files = new String[N];
        for (int i = 0; i < files.length; i++) {
            files[i] = br.readLine();
        }
        int length = files[0].length();
        sameChar = Stream.generate(() -> true).limit(length).toArray(Boolean[]::new);

        for (int i = 0; i < length; i++) {
            char firstChar = files[0].charAt(i);
            for (int j = 1; j < files.length; j++) {
                if (firstChar != files[j].charAt(i)) {
                    sameChar[i] = false;
                }
            }
        }
        for (int i = 0; i < sameChar.length; i++) {
            if (sameChar[i]) {
                System.out.print(files[0].charAt(i));
            } else {
                System.out.print("?");
            }
        }

    }
}