import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamTest {
    public static void main(String[] args) {
        Stream.iterate(2, n -> n * 2).limit(20).forEach(System.out::println);
        System.out.println("=====================================");
        IntStream.range(0, 5).forEach(System.out::println);
        System.out.println("=====================================");
        Stream.of("Stream", "zxcv", "Ab sqwer").map(String::toLowerCase).forEach(System.out::println);
    }
}
