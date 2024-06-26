package baekjun.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class IronBar_10799 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int ans = 0;
        int cnt = 0; // 현재 막대기 개수
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
//                stack.push(c);
                cnt++;
            } else {
                if (s.charAt(i-1) == '(') { // 레이저
                    cnt--;
                    ans += cnt;
                } else {
                    cnt--;
                    ans++;
                }
            }
        }

//        for (char c : s.toCharArray()) {
//            System.out.println(c);
//            if (c == '(') {
//                stack.push(c);
//                cnt++;
//                System.out.println("c='('");
//            } else { // c == ')'
//                if (stack.peek() == '(') { // 레이저
//                    ans += cnt;
//                    cnt--;
//                    stack.pop();
//                    System.out.println("레이저");
//                } else {
//                    cnt--;
//                    ans++;
//                    System.out.println("c=')'");
//                }
//            }
//        }
        System.out.println(ans);
    }
}
