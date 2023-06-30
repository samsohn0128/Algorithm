import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class K진수에서소수개수구하기_2022KAKAO2 {
    private static final List<Integer> indexesOfZero = new ArrayList<>();

    public static int solution(int n, int k) {
        String convertedNumber = convertDecimal(n, k);
        setIndexesOfZero(convertedNumber);
        int answer = 0;
        for (int i = 0; i < indexesOfZero.size() - 1; i++) {
            if (indexesOfZero.get(i) + 1 == indexesOfZero.get(i + 1)) {
                continue;
            }
            long number = Long.parseLong(convertedNumber.substring(indexesOfZero.get(i) + 1, indexesOfZero.get(i + 1)));
            if (isPrime(number)) {
                answer++;
            }
        }

        return answer;
    }

    private static boolean isPrime(long number) {
        if (number == 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    private static void setIndexesOfZero(String convertedNumber) {
        indexesOfZero.add(-1);
        for (int i = 0; i < convertedNumber.length(); i++) {
            if (convertedNumber.charAt(i) == '0') {
                indexesOfZero.add(i);
            }
        }
        if (convertedNumber.charAt(convertedNumber.length() - 1) != '0') {
            indexesOfZero.add(convertedNumber.length());
        }
    }

    private static String convertDecimal(int n, int k) {
        String convertedNumber;
        if (k == 10) {
            convertedNumber = String.valueOf(n);
        } else {
            Stack<Integer> convertedNumberStack = new Stack<>();
            while (n > 0) {
                convertedNumberStack.push(n % k);
                n /= k;
            }

            StringBuilder sb = new StringBuilder();
            while (!convertedNumberStack.isEmpty()) {
                sb.append(convertedNumberStack.pop());
            }

            convertedNumber = sb.toString();
        }
        return convertedNumber;
    }

    public static void main(String[] args) {
//        int n = 437674;
        int n = 2;
//        int k = 3;
        int k = 3;
        for (int i = 1; i <= 1000000; i++) {
            System.out.println("i = " + i);
            indexesOfZero.clear();
            int answer = solution(i, k);
            System.out.println("answer = " + answer);
        }
    }
}
