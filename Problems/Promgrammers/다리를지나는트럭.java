import java.util.*;

class Solution {
    public int solution(int bridgeLength, int weight, int[] truckWeights) {
        Queue<Truck> truckQueue = new LinkedList<>();
        int curWeight = 0;
        int curTime = 0;
        int truckIndex = 0;
        while (truckIndex < truckWeights.length) {
            curTime++;
            if (!truckQueue.isEmpty() && curTime - truckQueue.peek().startTime == bridgeLength) {
                Truck truck = truckQueue.poll();
                curWeight -= truck.weight;
            }
            if (curWeight + truckWeights[truckIndex] <= weight) {
                truckQueue.offer(new Truck(truckWeights[truckIndex], curTime));
                curWeight += truckWeights[truckIndex];
                truckIndex++;
            }
        }
        while (!truckQueue.isEmpty()) {
            curTime++;
            if (curTime - truckQueue.peek().startTime == bridgeLength) {
                Truck truck = truckQueue.poll();
                curWeight -= truck.weight;
            }
        }
        return curTime;
    }

    class Truck {
        int weight;
        int startTime;

        Truck(int weight, int startTime) {
            this.weight = weight;
            this.startTime = startTime;
        }
    }
}