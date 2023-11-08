import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        Stack<StockPrice> priceStack = new Stack<>();
        int[] answer = new int[prices.length];
        int curTime = 0;
        while (curTime < prices.length) {
            while (!priceStack.isEmpty() && priceStack.peek().price > prices[curTime]) {
                StockPrice price = priceStack.pop();
                answer[price.second] = curTime - price.second;
            }
            priceStack.push(new StockPrice(curTime, prices[curTime]));
            curTime++;
        }
        while (!priceStack.isEmpty()) {
            StockPrice price = priceStack.pop();
            answer[price.second] = curTime - price.second - 1;
        }
        return answer;
    }

    class StockPrice {
        int second;
        int price;

        StockPrice(int second, int price) {
            this.second = second;
            this.price = price;
        }

        @Override
        public String toString() {
            return "{"
                    + "second = " + second
                    + ", price = " + price
                    + "}";
        }
    }
}