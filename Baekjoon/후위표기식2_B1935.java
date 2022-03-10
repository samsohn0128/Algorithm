import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;

public class 후위표기식2_B1935 {
    static int N;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String input = br.readLine();
        double values[] = new double[N];
        for (int i = 0; i < N; i++) {
            values[i] = Integer.parseInt(br.readLine());
        }
        Stack<Double> numbers = new Stack<>();
        for (int i = 0; i < input.length(); i++) {
            switch (input.charAt(i)) {
                case '+':
                    double add1 = numbers.pop();
                    double add2 = numbers.pop();
                    numbers.push(add2 + add1);
                    break;
                case '-':
                    double sub1 = numbers.pop();
                    double sub2 = numbers.pop();
                    numbers.push(sub2 - sub1);
                    break;
                case '*':
                    double mul1 = numbers.pop();
                    double mul2 = numbers.pop();
                    numbers.push(mul2 * mul1);
                    break;
                case '/':
                    double div1 = numbers.pop();
                    double div2 = numbers.pop();
                    numbers.push(div2 / div1);
                    break;
                default:
                    numbers.push(values[input.charAt(i) - 'A']);
                    break;
            }
        }
        System.out.println(String.format("%.2f", numbers.pop()));
    }
}