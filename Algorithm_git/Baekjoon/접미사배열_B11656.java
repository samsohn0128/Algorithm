import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class 접미사배열_B11656 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String strInput = br.readLine();
        IntStream.range(0, strInput.length()).mapToObj(i -> strInput.substring(i)).sorted().collect(Collectors.toList()).forEach(System.out::println);
    }
}