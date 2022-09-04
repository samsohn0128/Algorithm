import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class 터렛_B1002 {

    private static int numberOfTestCases;
    private static int x1, y1, r1, x2, y2, r2;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        numberOfTestCases = Integer.parseInt(br.readLine());
        for (int testCase = 0; testCase < numberOfTestCases; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());
            r1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());
            r2 = Integer.parseInt(st.nextToken());
            int answer = solution(x1, y1, r1, x2, y2, r2);
            System.out.println(answer);
        }
    }

    private static int solution(int x1, int y1, int r1, int x2, int y2, int r2) {
        int answer = 0;
        int dx = x1 - x2;
        int dy = y1 - y2;
        int add = r1 + r2;
        int sub = Math.abs(r1 - r2);
        int distance = dx * dx + dy * dy;
        if (distance == 0 && sub == 0) {
            answer = -1;
        } else if (distance == add * add || distance == sub * sub) {
            answer = 1;
        } else if (sub * sub < distance && distance < add * add) {
            answer = 2;
        }
        return answer;
    }
}