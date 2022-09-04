import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

class 대칭차집합_B1269 {

    private static int numberOfA;
    private static int numberOfB;
    private static int[] numbersA;
    private static int[] numbersB;

    public static void main(String[] args) throws Exception {
        init();
        int answer = solution();
        System.out.println(answer);
    }

    private static void init() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        numberOfA = Integer.parseInt(st.nextToken());
        numberOfB = Integer.parseInt(st.nextToken());

        numbersA = new int[numberOfA];
        numbersB = new int[numberOfB];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < numberOfA; i++) {
            numbersA[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < numberOfB; i++) {
            numbersB[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static int solution() {
        int A_B = getA_B();
        int B_A = getB_A();
        return A_B + B_A;
    }

    private static int getA_B() {
        Set<Integer> setA = getSetA();
        for (int number : numbersB) {
            setA.remove(number);
        }
        return setA.size();
    }

    private static int getB_A() {
        Set<Integer> setB = getSetB();
        for (int number : numbersA) {
            setB.remove(number);
        }
        return setB.size();
    }

    private static Set<Integer> getSetA() {
        Set<Integer> setA = new HashSet<>();
        for (int number : numbersA) {
            setA.add(number);
        }
        return setA;
    }

    private static Set<Integer> getSetB() {
        Set<Integer> setB = new HashSet<>();
        for (int number : numbersB) {
            setB.add(number);
        }
        return setB;
    }
}