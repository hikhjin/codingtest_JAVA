//package baekjun.gold;
//
//import java.io.*;
//import java.util.Stack;
//
//import static baekjun.silver.Apartment_2667.tmp;
//
//public class BracketValue_2504 {
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        Stack<Character> stack = new Stack<Character>();
////        Stack<Object> stack = new Stack<>();
//        String s = br.readLine();
//        s = s.replace("()", "2");
//        s = s.replace("[]", "3"); // 전처리
//
//        int ans = 0;
//
//        for (char c : s.toCharArray()) {
//            if (c == ']' || c == ')') {
//
//                if (stack.empty()) {
//                    System.out.println(0);
//                    return;
//                }
//
//                char first = stack.pop();
//                char second = stack.pop();
//
//                if (second == '[') {
//                    int tmp = Character.getNumericValue(first) * 3;
//                    System.out.println((char) (tmp + '0'));
//                    stack.push((char) (tmp + '0'));
//                } else if (second == '(') {
//                    int tmp = Character.getNumericValue(first) * 2;
//                    System.out.println((char) (tmp + '0'));
//                    stack.push((char) (tmp + '0'));
//                } else { // 숫자일 경우 더하기
//                    int tmp = Character.getNumericValue(first) + Character.getNumericValue(second);
//                    System.out.println(first); // 9
//                    System.out.println(second); // 2
//                    System.out.println(tmp); // 11 인데 Character가 35까지 밖에 표현이 안 돼서 불가능
//                    System.out.println((char) (tmp + '0'));
//                    stack.push((char) (tmp + '0'));
//                }
//            } else {
//                stack.push(c);
//            }
//        }
//
//        ans = Character.getNumericValue(stack.pop());
//
//        if (!stack.empty()) {
//            ans = 0;
//        }
//
//        System.out.println(ans);
//    }
//}


package baekjun.gold;

import java.io.*;
import java.util.Stack;

public class BracketValue_2504 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Object> stack = new Stack<>();
        String s = br.readLine();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[') {
                stack.push(c);
            } else {
                int tmp = 0;

                while (!stack.isEmpty() && stack.peek() instanceof Integer) { // 스택이 비어있지 않고 맨 위 값이 정수일 경우
                    tmp += (int) stack.pop();
                }

                if (stack.isEmpty()) {
                    System.out.println(0);
                    return;
                }

                if (c == ')') {
                    if (stack.peek().equals('(')) {
                        stack.pop();
                        tmp = (tmp == 0) ? 2 : 2 * tmp;
                    } else {
                        System.out.println(0);
                        return;
                    }
                } else if (c == ']') {
                    if (stack.peek().equals('[')) {
                        stack.pop();
                        tmp = (tmp == 0) ? 3 : 3 * tmp;
                    } else {
                        System.out.println(0);
                        return;
                    }
                }

                stack.push(tmp);
            }
        }

        int ans = 0;
        while (!stack.isEmpty()) {
            if (stack.peek().equals('(') || stack.peek().equals('[')) {
                System.out.println(0);
                return;
            }
            ans += (int) stack.pop();
        }

        System.out.println(ans);
    }
}
