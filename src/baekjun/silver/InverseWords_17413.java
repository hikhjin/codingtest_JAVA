package baekjun.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class InverseWords_17413 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        
        String s = br.readLine();
        int check = 0; // <> 안에 있는 문자인지 확인

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '<') {
                check = 1;
                while (!stack.isEmpty()) {
                    sb.append(stack.pop()); // 스택에 있던 문자 모두 pop
                }
                sb.append(s.charAt(i));
            } else if (s.charAt(i) == '>') {
                check = 0;
                sb.append(s.charAt(i));
            } else if (check == 1){
                sb.append(s.charAt(i));
            }
            else if (s.charAt(i) == ' ') {
                if (check == 1) {
                    sb.append(s.charAt(i));
                } else {
                    while (!stack.isEmpty()) {
                        sb.append(stack.pop());
                    }
                    sb.append(s.charAt(i));
                }
            }
            else if (check == 0){
                stack.push(s.charAt(i));
            }
        }
        while (!stack.isEmpty()) {
            sb.append(stack.pop()); // 스택에 남아있는 문자 모두 pop
        }
        System.out.println(sb);
    }
}
