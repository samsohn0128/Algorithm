import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Router_B15828 {
    private static BufferedReader br;
    private static int N;
    private static Queue<Integer> buffer = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        init();
        String answer = solution();
        System.out.println(answer);
        br.close();
    }

    private static void init() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
    }

    private static String solution() throws IOException {
        StringBuilder sb = new StringBuilder();
        int number;
        while ((number = Integer.parseInt(br.readLine())) != -1) {
            if (number == 0) {
                buffer.poll();
            } else if (buffer.size() < N) {
                buffer.offer(number);
            }
        }
        if (buffer.isEmpty()) {
            sb.append("empty");
        }
        while (!buffer.isEmpty()) {
            sb.append(buffer.poll());
            sb.append(' ');
        }
        return sb.toString();
    }
}