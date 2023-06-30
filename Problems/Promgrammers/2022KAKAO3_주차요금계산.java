import java.util.*;
import java.util.stream.Collectors;

public class 주차요금계산_2022KAKAO3 {
    private static int BASIC_TIME;
    private static int BASIC_FEE;
    private static int UNIT_TIME;
    private static int UNIT_FEE;
    private static final int END_TIME = 23 * 60 + 59;
    private static List<Record> recordList;
    private static final Map<Integer, Integer> numberInTimeMap = new HashMap<>();
    private static final Map<Integer, Integer> numberUsageTimeMap = new HashMap<>();

    public static int[] solution(int[] fees, String[] records) {
        init(fees, records);
        return calculateFees();
    }

    private static int[] calculateFees() {
        List<Integer> answer = new ArrayList<>();
        for (Record record : recordList) {
            if (record.isIn) {
                numberInTimeMap.put(record.number, record.time);
            } else {
                int inTime = numberInTimeMap.remove(record.number);
                computeOrPutUsageTime(record.number, inTime, record.time);
            }
        }
        numberInTimeMap.forEach((number, inTime) -> computeOrPutUsageTime(number, inTime, END_TIME));

        numberUsageTimeMap.keySet().stream()
                .sorted()
                .forEach((number) -> answer.add(calculateFee(numberUsageTimeMap.get(number))));

        return answer.stream()
                .mapToInt(i -> i)
                .toArray();
    }

    private static void computeOrPutUsageTime(int number, int inTime, int outTime) {
        if (numberUsageTimeMap.containsKey(number)) {
            numberUsageTimeMap.compute(number, (numberKey, usageTime) -> {
                usageTime += outTime - inTime;
                return usageTime;
            });
        } else {
            numberUsageTimeMap.put(number, outTime - inTime);
        }
    }

    private static int calculateFee(int usageTime) {
        if (usageTime <= BASIC_TIME) {
            return BASIC_FEE;
        } else {
            return (int) (BASIC_FEE + Math.ceil((double) (usageTime - BASIC_TIME) / UNIT_TIME) * UNIT_FEE);
        }
    }


    private static void init(int[] fees, String[] records) {
        BASIC_TIME = fees[0];
        BASIC_FEE = fees[1];
        UNIT_TIME = fees[2];
        UNIT_FEE = fees[3];

        recordList = Arrays.stream(records)
                .map(Record::new)
                .collect(Collectors.toList());
    }

    private static class Record {
        private int time;
        private int number;
        private boolean isIn;

        public Record(String record) {
            String[] records = record.split(" ");
            String[] time = records[0].split(":");
            int hour = Integer.parseInt(time[0]);
            this.time = hour * 60 + Integer.parseInt(time[1]);
            this.number = Integer.parseInt(records[1]);
            this.isIn = "IN".equals(records[2]);
        }
    }

    public static void main(String[] args) {
        int[] fees = {180, 5000, 10, 600};
        String[] records = {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};
        int[] answer = solution(fees, records);
        System.out.println("answer = " + Arrays.toString(answer));
    }
}