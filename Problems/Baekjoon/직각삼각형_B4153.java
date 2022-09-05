import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

class 직각삼각형_B4153 {

    private static List<Integer[]> sidesList = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        init();
        String answer = solution();
        System.out.println(answer);
    }

    private static void init() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int side1 = Integer.parseInt(st.nextToken());
            int side2 = Integer.parseInt(st.nextToken());
            int side3 = Integer.parseInt(st.nextToken());

            if (side1 == 0) break;

            sidesList.add(new Integer[]{side1, side2, side3});
        }
    }

    private static String solution() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sidesList.size(); i++) {
            Integer[] sides = sidesList.get(i);
            Arrays.sort(sides);
            if (sides[0] * sides[0] + sides[1] * sides[1] == sides[2] * sides[2]) {
                sb.append("right");
            } else {
                sb.append("wrong");
            }
            if (i < sidesList.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}