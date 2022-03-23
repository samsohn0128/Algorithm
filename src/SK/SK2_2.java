package SK;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class SK2_2 {
    private Queue<Job> readQueue = new LinkedList<>();
    private Queue<WriteJob> writeQueue = new LinkedList<>();
    private int curTime;

    public String[] solution(String[] arr, String[] processes) {
        List<String> readResult = new ArrayList<>();
        init(processes);
        int totalSpendTime = 0;
        while (!readQueue.isEmpty() || !writeQueue.isEmpty()) {
            boolean firstRead = true;
            int readStartTime = 0;
            while (!readQueue.isEmpty()) {
                if (writeQueue.isEmpty() || readQueue.peek().startTime < writeQueue.peek().startTime) {
                    Job read = readQueue.poll();
                    String readStr = "";
                    for (int i = read.from; i <= read.to; i++) {
                        readStr += arr[i];
                    }
                    readResult.add(readStr);
                    if (firstRead) {
                        readStartTime = Math.max(curTime, read.startTime);
                        curTime = readStartTime + read.spendTime;
                        firstRead = false;
                    } else {
                        curTime = Math.max(curTime, Math.max(readStartTime + read.spendTime, read.startTime + read.spendTime));
                    }
                    if (!readQueue.isEmpty() && curTime < readQueue.peek().startTime) {
                        break;
                    }
                } else {
                    break;
                }
            }
            totalSpendTime += curTime - readStartTime;
            while (!writeQueue.isEmpty()) {
                if (writeQueue.peek().startTime <= curTime || readQueue.isEmpty() || writeQueue.peek().startTime <= readQueue.peek().startTime) {
                    WriteJob write = writeQueue.poll();
                    for (int i = write.from; i <= write.to; i++) {
                        arr[i] = Integer.toString(write.value);
                    }
                    curTime = Math.max(curTime, write.startTime) + write.spendTime;
                    totalSpendTime += write.spendTime;
                } else {
                    break;
                }
            }
        }
        String[] answer = new String[readResult.size() + 1];
        for (int i = 0; i < readResult.size(); i++) {
            answer[i] = readResult.get(i);
        }
        answer[answer.length - 1] = Integer.toString(totalSpendTime);
        return answer;
    }

    private void init(String[] processes) {
        for (int i = 0; i < processes.length; i++) {
            String[] process = processes[i].split(" ");
            String job = process[0];
            int startTime = Integer.parseInt(process[1]);
            int spendTime = Integer.parseInt(process[2]);
            int from = Integer.parseInt(process[3]);
            int to = Integer.parseInt(process[4]);
            if (job.equals("write")) {
                int value = Integer.parseInt(process[5]);
                writeQueue.offer(new WriteJob(startTime, spendTime, from, to, value));
            } else {
                readQueue.offer(new Job(startTime, spendTime, from, to));
            }
        }
    }

    private class Job {
        int startTime;
        int spendTime;
        int from;
        int to;

        public Job(int startTime, int spendTime, int from, int to) {
            this.startTime = startTime;
            this.spendTime = spendTime;
            this.from = from;
            this.to = to;
        }
    }

    private class WriteJob extends Job {
        private int value;

        public WriteJob(int startTime, int spendTime, int from, int to, int value) {
            super(startTime, spendTime, from, to);
            this.value = value;
        }
    }
}