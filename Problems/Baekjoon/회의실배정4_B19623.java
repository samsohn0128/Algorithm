import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int numberOfMeetings;
    private static List<Meeting> meetingList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        init();
        int answer = solution();
        System.out.println(answer);
    }

    private static void init() throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        numberOfMeetings = Integer.parseInt(br.readLine());
        for (int i = 0; i < numberOfMeetings; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int numberOfParticipants = Integer.parseInt(st.nextToken());
            meetingList.add(new Meeting(start, -1, -1));
            meetingList.add(new Meeting(end, start, numberOfParticipants));
        }
    }

    private static int solution() {
        meetingList.sort(Comparator.comparingInt(meeting -> meeting.time));
        HashMap<Integer, Integer> timeToIndex = mappingTimeToIndex();

        int[] dp = new int[meetingList.size()];
        for (int i = 1; i < meetingList.size(); i++) {
            Meeting meeting = meetingList.get(i);
            if (meeting.start == -1) {
                dp[i] = dp[i - 1];
            } else {
                dp[i] = Math.max(dp[i - 1], dp[timeToIndex.get(meeting.start)] + meeting.numberOfParticipants);
            }
        }
        return dp[dp.length - 1];
    }

    private static HashMap<Integer, Integer> mappingTimeToIndex() {
        HashMap<Integer, Integer> timeToIndex = new HashMap<>();
        for (int i = 0; i < meetingList.size(); i++) {
            timeToIndex.put(meetingList.get(i).time, i);
        }
        return timeToIndex;
    }

    private static class Meeting {
        private int time;
        private int start;
        private int numberOfParticipants;

        public Meeting(int time, int start, int numberOfParticipants) {
            this.time = time;
            this.start = start;
            this.numberOfParticipants = numberOfParticipants;
        }
    }
}