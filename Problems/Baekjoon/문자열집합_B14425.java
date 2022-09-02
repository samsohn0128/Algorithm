import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

class 문자열집합_B14425 {

    private static int numberOfStrings1;
    private static int numberOfStrings2;
    private static Set<String> stringSet = new HashSet<>();
    private static List<String> stringList = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        init();
        int answer = solution();
        System.out.println(answer);
    }

    private static void init() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        numberOfStrings1 = Integer.parseInt(st.nextToken());
        numberOfStrings2 = Integer.parseInt(st.nextToken());

        for (int i = 0; i < numberOfStrings1; i++) {
            stringSet.add(br.readLine());
        }

        for (int i = 0; i < numberOfStrings2; i++) {
            stringList.add(br.readLine());
        }
    }

    private static int solution() {


        int count = 0;
        for (int i = 0; i < numberOfStrings2; i++) {
            if (stringSet.contains(stringList.get(i))) {
                count++;
            }
        }
        return count;
    }
}