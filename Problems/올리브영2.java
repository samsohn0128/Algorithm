import java.util.*;
import java.util.stream.Collectors;

public class 올리브영2 {
    private static Set<String> korSet;
    private static Set<String> usaSet;
    private static List<List<String>> incLists;

    private static Map<Pair, Integer> pairCount = new HashMap<>();

    public static int solution(String[] kor, String[] usa, String[] incs) {
        init(kor, usa, incs);
        countPair();
        return pairCount.values().stream()
                .mapToInt(i -> i)
                .max()
                .orElse(0);
    }

    private static void init(String[] kor, String[] usa, String[] incs) {
        korSet = Arrays.stream(kor).collect(Collectors.toSet());
        usaSet = Arrays.stream(usa).collect(Collectors.toSet());
        incLists = Arrays.stream(incs)
                .map(inc -> Arrays.stream(inc.split(" ")).collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    private static Pair makePair(String stock1, String stock2) {
        if (korSet.contains(stock1) && usaSet.contains(stock2)) {
            return new Pair(stock1, stock2);
        } else if (usaSet.contains(stock1) && korSet.contains(stock2)) {
            return new Pair(stock2, stock1);
        } else {
            return null;
        }
    }

    private static void countPair() {
        incLists.forEach(incList -> {
            for (int i = 0; i < incList.size() - 1; i++) {
                for (int j = i + 1; j < incList.size(); j++) {
                    Pair pair = makePair(incList.get(i), incList.get(j));
                    if (pair != null) {
                        pairCount.computeIfPresent(pair, ((k, v) -> ++v));
                        pairCount.putIfAbsent(pair, 1);
                    }
                }
            }
        });
    }

    private static class Pair {
        private String kor;
        private String usa;

        public Pair(String kor, String usa) {
            this.kor = kor;
            this.usa = usa;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (!(o instanceof Pair))
                return false;
            Pair pair = (Pair) o;
            return usa.equals(pair.usa) && kor.equals(pair.kor);
        }

        @Override
        public int hashCode() {
            return Objects.hash(kor, usa);
        }
    }

    public static void main(String[] args) {
//        String[] kor = {"AAA", "BCD", "AAAAA", "ZY"};
//        String[] usa = {"AB", "AA", "TTTT"};
//        String[] incs = {"AB BCD AA AAA TTTT AAAAA", "BCD AAA", "AB AAA TTTT BCD", "AA AAAAA AB", "AAA AB BCD"};
        String[] kor = {"CCC", "BCDF"};
        String[] usa = {"XXXX"};
        String[] incs = {"BCDF CCC", "XXXX"};

        int answer = solution(kor, usa, incs);
        System.out.println("answer = " + answer);
    }
}