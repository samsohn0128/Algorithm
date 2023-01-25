import java.util.*;

public class 신고결과받기_2022KAKAO1 {
    private static final Map<String, Integer> stringToIntId = new HashMap<>();
    private static final Map<Integer, Set<Integer>> idReportsMap = new HashMap<>();
    private static final Map<Integer, Integer> reportedCount = new HashMap<>();

    public static int[] solution(String[] idList, String[] reports, int k) {
        init(idList);
        setUpIdReportsMap(reports);
        return getAnswer(idList, k);
    }

    private static int[] getAnswer(String[] idList, int k) {
        List<Integer> answer = new ArrayList<>();
        for (String strId : idList) {
            int id = getIntId(strId);
            int mailCount = 0;
            for (Integer reportedId : idReportsMap.get(id)) {
                if (reportedCount.get(reportedId) >= k) {
                    mailCount++;
                }
            }
            answer.add(mailCount);
        }

        return answer.stream()
                .mapToInt(i -> i)
                .toArray();
    }

    private static void setUpIdReportsMap(String[] reports) {
        for (String report : reports) {
            String[] idReport = report.split(" ");
            int reporterId = getIntId(idReport[0]);
            int reportedId = getIntId(idReport[1]);

            reportedCount.computeIfPresent(reportedId, (reportedIdKey, reportedCountValue) -> {
                if (!idReportsMap.get(reporterId).contains(reportedIdKey)) {
                    reportedCountValue++;
                }
                return reportedCountValue;
            });
            idReportsMap.computeIfPresent(reporterId, (reporterIdKey, reportedIdSet) -> {
                reportedIdSet.add(reportedId);
                return reportedIdSet;
            });
        }
    }

    private static void init(String[] idList) {
        for (int i = 0; i < idList.length; i++) {
            stringToIntId.put(idList[i], i);
        }
        for (String id : idList) {
            idReportsMap.put(getIntId(id), new HashSet<>());
            reportedCount.put(getIntId(id), 0);
        }
    }

    private static int getIntId(String stringId) {
        return stringToIntId.get(stringId);
    }

    public static void main(String[] args) {
//        String[] idList = new String[]{"muzi", "frodo", "apeach", "neo"};
        String[] idList = new String[]{"con", "ryan"};
//        String[] report = new String[]{"muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"};
        String[] report = new String[]{"ryan con", "ryan con", "ryan con", "ryan con"};
        int k = 2;
        int[] answer = solution(idList, report, k);
        System.out.println(Arrays.toString(answer));
    }
}
