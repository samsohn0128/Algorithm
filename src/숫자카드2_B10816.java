import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

class 숫자카드2_B10816 {
    private static int numberOfCards;
    private static int numberOfIntegers;
    private static Map<Integer, Integer> countMap = new HashMap<>();
    private static List<Integer> integerList = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        init();
        String answer = solution();
        System.out.println(answer);
    }

    private static void init() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        numberOfCards = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < numberOfCards; i++) {
            int integer = Integer.parseInt(st.nextToken());
            countMap.computeIfPresent(integer, (key, count) -> ++count);
            countMap.putIfAbsent(integer, 1);
        }
        numberOfIntegers = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < numberOfIntegers; i++) {
            integerList.add(Integer.parseInt(st.nextToken()));
        }
    }

    private static String solution() {
        StringBuilder sb = new StringBuilder();
        for (Integer integer : integerList) {
            if (countMap.containsKey(integer)) {
                sb.append(countMap.get(integer) + " ");
            } else {
                sb.append(0 + " ");
            }
        }
        return sb.toString();
    }
}