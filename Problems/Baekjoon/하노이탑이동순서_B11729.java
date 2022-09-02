import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;

class 하노이탑이동순서_B11729 {

    private static int N;
    private static Stack<Integer>[] towers = new Stack[4];
    private static int countOfMove;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        init();
        hanoi(N, 1, 2, 3);
        sb.insert(0, countOfMove + "\n");
        System.out.println(sb);
    }

    private static void init() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 1; i < 4; i++) {
            towers[i] = new Stack<>();
        }
        for (int i = N; i > 0; i--) {
            towers[1].push(i);
        }
    }

    private static void hanoi(int n, int from, int via, int to) {
        if (n == 1) {
            move(from, to);
        } else {
            hanoi(n - 1, from, to, via);
            move(from, to);
            hanoi(n - 1, via, from, to);
        }
    }

    private static void move(int from, int to) {
        towers[to].push(towers[from].pop());
        appendStringBuilder(from, to);
        countOfMove++;
    }

    private static void appendStringBuilder(int from, int to) {
        sb.append(from);
        sb.append(' ');
        sb.append(to);
        sb.append('\n');
    }
}