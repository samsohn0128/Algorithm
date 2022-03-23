import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class 단어뒤집기2_B17413 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Character> wordStack = new Stack<>();
        Queue<Character> wordQueue = new LinkedList<>();
        String words = br.readLine();

        String answer = "";
        boolean bracket = false;
        for (int i = 0; i < words.length(); i++) {
            if (words.charAt(i) == '<') {
                bracket = true;
                wordQueue.offer(words.charAt(i));
                while (!wordStack.isEmpty()) {
                    answer += wordStack.pop();
                }
            } else if (words.charAt(i) == '>') {
                bracket = false;
                wordQueue.offer(words.charAt(i));
                while (!wordQueue.isEmpty()) {
                    answer += wordQueue.poll();
                }
            } else if (words.charAt(i) == ' ') {
                while (!wordStack.isEmpty()) {
                    answer += wordStack.pop();
                }
                while (!wordQueue.isEmpty()) {
                    answer += wordQueue.poll();
                }
                answer += ' ';
            } else {
                if (bracket) {
                    wordQueue.offer(words.charAt(i));
                } else {
                    wordStack.push(words.charAt(i));
                }
            }
        }
        while (!wordStack.isEmpty()) {
            answer += wordStack.pop();
        }
        while (!wordQueue.isEmpty()) {
            answer += wordQueue.poll();
        }
        answer += " ";
        System.out.println(answer);
    }
}
