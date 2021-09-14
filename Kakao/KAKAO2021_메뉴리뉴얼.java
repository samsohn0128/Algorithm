import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class KAKAO2021_메뉴리뉴얼 {
	public String[] solution(String[] orders, int[] course) {
		Map<String, Integer> candidateMap = new HashMap<>();
		for (int orderNum = 0; orderNum < orders.length; orderNum++) {
			String order = orders[orderNum];

			for (int i = 0; i < (1 << order.length()); i++) {
				String candidate = "";
				for (int j = 0; j < order.length(); j++) {
					if ((i & (1 << j)) != 0) {
						candidate += order.charAt(j);
					}
				}
				boolean isCandidateLength = false;
				for (int j = 0; j < course.length; j++) {
					if (candidate.length() == course[j]) {
						isCandidateLength = true;
						break;
					}
				}
				if (isCandidateLength) {
					char[] sortString = candidate.toCharArray();
					Arrays.sort(sortString);
					candidate = new String(sortString);
					candidateMap.computeIfPresent(candidate, (k, v) -> ++v);
					candidateMap.putIfAbsent(candidate, 1);
				}
			}
		}
		List<String> answerList = new ArrayList<>();
		for (int i = 0; i < course.length; i++) {
			List<String> answerListTemp = new ArrayList<>();
			int maxOrder = 0;
			for (String candidate : candidateMap.keySet()) {
				if (candidate.length() == course[i] && candidateMap.get(candidate) >= 2) {
					if (candidateMap.get(candidate) >= maxOrder) {
						if (candidateMap.get(candidate) > maxOrder) {
							answerListTemp.clear();
							maxOrder = candidateMap.get(candidate);
						}
						answerListTemp.add(candidate);
					}
				}
			}
			for (String candidate : answerListTemp) {
				answerList.add(candidate);
			}
		}
		String[] answer = new String[answerList.size()];
		for (int i = 0; i < answerList.size(); i++) {
			answer[i] = answerList.get(i);
		}
		Arrays.sort(answer);
		return answer;
	}
}