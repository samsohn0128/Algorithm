import java.io.*;
import java.util.Stack;

public class 문자열폭발_B9935 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String strInput = br.readLine();
        String bomb = br.readLine();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < strInput.length(); i++) {
            stack.push(strInput.charAt(i));
            if (stack.size() >= bomb.length()) {
                if (stack.peek() == bomb.charAt(bomb.length() - 1)) {
                    boolean same = true;
                    for (int j = 0; j < bomb.length(); j++) {
                        if (stack.get(stack.size() - 1 - j) != bomb.charAt(bomb.length() - 1 - j)) {
                            same = false;
                            break;
                        }
                    }
                    if (same) {
                        for (int j = 0; j < bomb.length(); j++) {
                            stack.pop();
                        }
                    }
                }
            }
        }
        if (stack.isEmpty())
            System.out.println("FRULA");
        else {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            for (int i = 0; i < stack.size(); i++) {
                bw.write(stack.get(i));
            }
            bw.flush();
            bw.close();
        }
    }
}