import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 주유소_B13305 {

    private static int numberOfCities;
    private static int[] distances;
    private static int[] fuelPrices;

    public static void main(String[] args) throws Exception {
        BufferedReader br = setBufferedReader();
        init(br);
        long answer = solution();
        System.out.println(answer);
        br.close();
    }

    private static BufferedReader setBufferedReader() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        return new BufferedReader(new InputStreamReader(System.in));
    }

    private static void init(BufferedReader br) throws Exception {
        numberOfCities = Integer.parseInt(br.readLine());

        distances = new int[numberOfCities - 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < numberOfCities - 1; i++) {
            distances[i] = Integer.parseInt(st.nextToken());
        }

        fuelPrices = new int[numberOfCities];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < numberOfCities; i++) {
            fuelPrices[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static long solution() {
        long cost = 0;
        int curFuelPrice = fuelPrices[0];
        int curDistance = 0;
        for (int i = 0; i < numberOfCities - 1; i++) {
            curDistance += distances[i];
            if (curFuelPrice > fuelPrices[i + 1]) {
                cost += (long) curFuelPrice * curDistance;
                curFuelPrice = fuelPrices[i + 1];
                curDistance = 0;
            }
        }
        cost += (long) curFuelPrice * curDistance;
        return cost;
    }
}