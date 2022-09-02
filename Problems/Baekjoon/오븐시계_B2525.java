import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class 오븐시계_B2525 {

    private static int hour;
    private static int minute;
    private static int elapsedTime;


    public static void main(String[] args) throws Exception {
        init();
        String answer = solution();
        System.out.println(answer);
    }

    private static void init() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        hour = Integer.parseInt(st.nextToken());
        minute = Integer.parseInt(st.nextToken());
        elapsedTime = Integer.parseInt(br.readLine());
    }

    private static String solution() {
        StringBuilder sb = new StringBuilder();
        hour = (hour + (minute + elapsedTime) / 60) % 24;
        minute = (minute + elapsedTime) % 60;
        sb.append(hour);
        sb.append(" ");
        sb.append(minute);
        return sb.toString();
    }
}