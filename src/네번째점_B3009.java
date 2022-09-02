import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

class 네번째점_B3009 {

    private static int[][] coordinates = new int[3][2];

    public static void main(String[] args) throws Exception {
        init();
        String answer = solution();
        System.out.println(answer);
    }

    private static void init() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            coordinates[i][0] = Integer.parseInt(st.nextToken());
            coordinates[i][1] = Integer.parseInt(st.nextToken());
        }
    }

    private static String solution() {
        StringBuilder sb = new StringBuilder();
        Set<Integer> setX = getSetX();
        Set<Integer> setY = getSetY();
        int x = (int) setX.toArray()[0];
        int y = (int) setY.toArray()[0];
        sb.append(x);
        sb.append(" ");
        sb.append(y);
        return sb.toString();
    }

    private static Set<Integer> getSetX() {
        Set<Integer> setX = new HashSet<>();
        for (int i = 0; i < 3; i++) {
            if (!setX.remove(coordinates[i][0])) {
                setX.add(coordinates[i][0]);
            }
        }
        return setX;
    }

    private static Set<Integer> getSetY() {
        Set<Integer> setY = new HashSet<>();
        for (int i = 0; i < 3; i++) {
            if (!setY.remove(coordinates[i][1])) {
                setY.add(coordinates[i][1]);
            }
        }
        return setY;
    }
}