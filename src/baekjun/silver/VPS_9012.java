package baekjun.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class VPS_9012 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < N; i++) {
            String s = br.readLine(); // 한 줄 입력받기
            Stack<Character> stack = new Stack<Character>(); // 한 글자씩 확인하므로 Character

            for(int j = 0; j < s.length(); j++) {
                if (s.charAt(j) == '(') { // 문자열.charAt(숫자) -> 문자열의 숫자번째 문자를 반환
                    stack.push(s.charAt(j)); // '(' 면 스택에 push
                } else if (stack.empty()) { // ')' 일 때 스택이 비어있으면 스택에 push하고 반복문 빠져나가기
                    stack.push(s.charAt(j));
                    break;
                } else {
                    stack.pop(); // ')'일 때 스택이 비어 있지 않으면 pop
                }
            }
            if (stack.empty()) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}
