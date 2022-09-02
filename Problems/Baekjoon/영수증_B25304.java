import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class 영수증_B25304 {

    private static int totalPrice;
    private static int numberOfItems;
    private static int[][] items;


    public static void main(String[] args) throws Exception {
        init();
        String answer = solution();
        System.out.println(answer);
    }

    private static void init() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        totalPrice = Integer.parseInt(br.readLine());
        numberOfItems = Integer.parseInt(br.readLine());

        items = new int[numberOfItems][2];
        for (int i = 0; i < numberOfItems; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 2; j++) {
                items[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static String solution() {
        int calculatedPrice = 0;
        for (int i = 0; i < items.length; i++) {
            calculatedPrice += items[i][0] * items[i][1];
        }
        return calculatedPrice == totalPrice ? "Yes" : "No";
    }
}