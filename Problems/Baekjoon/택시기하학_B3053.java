import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

class 택시기하학_B3053 {

    private static int R;

    public static void main(String[] args) throws Exception {
        init();
        String answer = solution();
        System.out.println(answer);
    }

    private static void init() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        R = Integer.parseInt(br.readLine());
    }

    private static String solution() {
        StringBuilder sb = new StringBuilder();
        double euclidArea = getEuclidArea();
        sb.append(String.format("%.6f", euclidArea));
        sb.append("\n");
        double taxiArea = getTaxiArea();
        sb.append(String.format("%.6f", taxiArea));
        return sb.toString();
    }

    private static double getEuclidArea() {
        return R * R * Math.PI;
    }

    private static double getTaxiArea() {
        return R * R * 2;
    }
}