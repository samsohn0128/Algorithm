import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br;
    private static int numberOfRoads, numberOfCases;
    private static Case[] cases;

    public static void main(String[] args) throws Exception {
        init();
        String answer = solution();
        System.out.println(answer);
    }

    private static void init() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        numberOfRoads = Integer.parseInt(br.readLine());
        numberOfCases = Integer.parseInt(br.readLine());
        cases = new Case[numberOfCases];
    }

    private static String solution() {
        StringBuilder sb = new StringBuilder();

        return sb.toString();
    }

    private static class Case {
        private int y;
        private int x;

        public Case(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}