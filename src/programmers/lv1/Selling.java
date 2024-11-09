package programmers.lv1;

import java.util.Stack;

public class Selling {
    public int solution(int[] prices) {
        Stack<Integer> stack = new Stack<>();
        int max = 0;

        stack.push(prices[0]);
        for (int i = 1; i < prices.length; i++) {
            if (stack.peek() > prices[i]) {
                stack.push(prices[i]);
            } else {
                max = Math.max(max, prices[i] - stack.peek());
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Selling solution = new Selling();
        int[] prices = {3, 2, 4, 6, 1};
        int result = solution.solution(prices);
        System.out.println(result);
    }
}
