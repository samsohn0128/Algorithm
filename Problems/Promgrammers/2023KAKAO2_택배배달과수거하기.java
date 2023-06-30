public class 택배배달과수거하기_2023KAKAO2 {
    public long solution(int capacity, int numberOfHouses, int[] deliveries, int[] pickups) {
        long answer = 0;

        int deliveryLastIndexGreaterThanZero = getLastIndexGreaterThanZero(deliveries, numberOfHouses - 1);
        int pickupLastIndexGreaterThanZero = getLastIndexGreaterThanZero(pickups, numberOfHouses - 1);

        while (hasMoreDeliveriesOrPickups(deliveryLastIndexGreaterThanZero, pickupLastIndexGreaterThanZero)) {
            answer += 2L * Math.max(deliveryLastIndexGreaterThanZero + 1, pickupLastIndexGreaterThanZero + 1);
            if (deliveryLastIndexGreaterThanZero >= 0) {
                deliveryLastIndexGreaterThanZero = reduceArr(deliveries, deliveryLastIndexGreaterThanZero, capacity);
            }
            if (pickupLastIndexGreaterThanZero >= 0) {
                pickupLastIndexGreaterThanZero = reduceArr(pickups, pickupLastIndexGreaterThanZero, capacity);
            }
        }


        return answer;
    }

    private int reduceArr(int[] arr, int lastIndexGreaterThanZero, int capacity) {
        while (capacity > 0 && lastIndexGreaterThanZero >= 0) {
            int boxCount = Math.min(capacity, arr[lastIndexGreaterThanZero]);
            arr[lastIndexGreaterThanZero] -= boxCount;
            capacity -= boxCount;
            lastIndexGreaterThanZero = getLastIndexGreaterThanZero(arr, lastIndexGreaterThanZero);
        }
        return lastIndexGreaterThanZero;
    }

    private int getLastIndexGreaterThanZero(int[] arr, int from) {
        int i = from;
        while (i >= 0) {
            if (arr[i] > 0) {
                return i;
            }
            i--;
        }
        return i;
    }

    private boolean hasMoreDeliveriesOrPickups(int deliveryLastIndexGreaterThanZero, int pickupLastIndexGreaterThanZero) {
        return deliveryLastIndexGreaterThanZero >= 0 || pickupLastIndexGreaterThanZero >= 0;
    }
}