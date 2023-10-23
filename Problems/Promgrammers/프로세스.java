import java.util.*;

class Solution {
    Queue<Job> jobQueue = new LinkedList<>();
    int[] priorityCount = new int[10];

    public int solution(int[] priorities, int location) {
        init(priorities);
        int answer = 0;
        int curPriority = 9;
        while (!jobQueue.isEmpty()) {
            while (priorityCount[curPriority] == 0) {
                curPriority--;
            }
            Job job = jobQueue.poll();
            if (job.priority == curPriority) {
                answer++;
                priorityCount[curPriority]--;
                if (job.id == location) {
                    break;
                }
            } else {
                jobQueue.offer(job);
            }
        }
        return answer;
    }

    void init(int[] priorities) {
        for (int i = 0; i < priorities.length; i++) {
            jobQueue.offer(new Job(i, priorities[i]));
            priorityCount[priorities[i]]++;
        }
    }

    class Job {
        int id;
        int priority;

        Job(int id, int priority) {
            this.id = id;
            this.priority = priority;
        }
    }
}