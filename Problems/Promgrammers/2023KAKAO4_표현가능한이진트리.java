import java.util.Arrays;

class 표현가능한이진트리_2023KAKAO4 {
    public static int[] solution(long[] numbers) {
        return Arrays.stream(numbers)
                .mapToObj(표현가능한이진트리_2023KAKAO4::getBinaryNumberFilledWithZero)
                .mapToInt(binaryNumber -> isAbleToMakeBinaryTree(binaryNumber) ? 1 : 0)
                .toArray();
    }

    private static boolean isAbleToMakeBinaryTree(String binaryNumber) {
        if (binaryNumber.length() == 1) {
            return true;
        } else {
            char middle = getMiddle(binaryNumber);
            String leftSubtree = binaryNumber.substring(0, binaryNumber.length() / 2);
            String rightSubtree = binaryNumber.substring(binaryNumber.length() / 2 + 1);
            if (middle == '0' && (getMiddle(leftSubtree) == '1' || getMiddle(rightSubtree) == '1')) {
                return false;
            } else {
                return isAbleToMakeBinaryTree(leftSubtree) && isAbleToMakeBinaryTree(rightSubtree);
            }
        }
    }

    private static char getMiddle(String binaryNumber) {
        return binaryNumber.charAt(binaryNumber.length() / 2);
    }

    private static String getBinaryNumberFilledWithZero(long number) {
        StringBuilder binaryNumberBuilder = new StringBuilder(Long.toBinaryString(number));
        StringBuilder zeroBuilder = new StringBuilder();
        int minLength = getMinLengthGreaterThanOrEqualsTo(binaryNumberBuilder.length());
        for (int i = 0; i < minLength - binaryNumberBuilder.length(); i++) {
            zeroBuilder.append(0);
        }
        binaryNumberBuilder.insert(0, zeroBuilder);
        return binaryNumberBuilder.toString();
    }

    private static int getMinLengthGreaterThanOrEqualsTo(int length) {
        int minLength = 1;
        int plus = 2;
        while (minLength < length) {
            minLength += plus;
            plus *= 2;
        }
        return minLength;
    }

    public static void main(String[] args) {
//        long[] numbers = {7, 42, 5};
        long[] numbers = {63, 111, 95};
        int[] answer = solution(numbers);
        System.out.println(Arrays.toString(answer));
    }
}