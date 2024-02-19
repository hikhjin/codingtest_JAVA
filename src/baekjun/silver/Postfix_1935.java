package baekjun.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Postfix_1935 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        double[] arr = new double[N]; // 피연산자 담을 배열
        Stack<Double> stack = new Stack<>(); // 연산값 담을 스택

        String s = br.readLine();

        for (int i = 0; i < N; i++) {
            arr[i] = Double.parseDouble(br.readLine()); // 피연산자 담기
        }

        for (int j = 0; j < s.length(); j++) {
            if ('A' <= s.charAt(j) && s.charAt(j) <= 'Z') {
                stack.push(arr[s.charAt(j) - 'A']); // ex) arr[0]에 A가 들어있으므로 A-A를 하면 0, 인덱스처럼 사용 가능
            } else if (s.charAt(j) == '+') {
                stack.push(stack.pop() + stack.pop());
            } else if (s.charAt(j) == '-') {
                double tmp1 = stack.pop();
                double tmp2 = stack.pop();
                stack.push(tmp2 - tmp1);
            } else if (s.charAt(j) == '*') {
                stack.push(stack.pop() * stack.pop());
            } else if (s.charAt(j) == '/') {
                double tmp1 = stack.pop();
                double tmp2 = stack.pop();
                stack.push(tmp2 / tmp1);
            }
        }
        double res = stack.pop();
        System.out.println(String.format("%.2f", res));
    }
}
