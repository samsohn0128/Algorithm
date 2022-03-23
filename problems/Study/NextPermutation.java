package Study;
import java.util.Arrays;

public class NextPermutation {

	static int[] arr = { 3, 2, 5, 4, 1 };

	public static void main(String[] args) throws Exception {
		while (true) {
			System.out.println(Arrays.toString(arr));
			if (!np())
				break;
		}
	}

	static boolean np() {
		int i = arr.length - 1;
		while (i > 0 && arr[i - 1] >= arr[i])
			--i;

		if (i == 0)
			return false;

		int j = arr.length - 1;
		while (arr[i - 1] >= arr[j])
			--j;

		swap( i - 1, j);

		int k = arr.length - 1;
		while (i < k) {
			swap( i++, k--);
		}

		return true;
	}

	static void swap(int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
