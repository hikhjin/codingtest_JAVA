package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Stack_10828 {
    public static int[] stack = new int[10001]; // stack 배열 선언
    public static int size = 0; // 사이즈 초기화
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 명령 수
        StringBuilder sb = new StringBuilder(); // StringBuilder는 String과 다르게 변경이 가능하다.

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String S = st.nextToken();
            switch (S) {
                case "push":
                    push(Integer.parseInt(st.nextToken())); // string을 int로 형변환 해준 후 push
                    break;
                case "pop":
                    sb.append(pop()).append("\n");
                    break;
                case "size":
                    sb.append(size()).append("\n");
                    break;
                case "empty":
                    sb.append(empty()).append("\n");
                    break;
                case "top":
                    sb.append(top()).append("\n");
                    break;
            }
        }
        System.out.println(sb);
    }

    public static void push(int n) {
        stack[size] = n;
        size++;
    }

    public static int pop() {
        if (size == 0) {
            return -1;
        } else {
            int result = stack[size-1];
            size--;
            return result;
        }
    }

    public static int size() {
        return size;
    }

    public static int empty() {
        if (size == 0) {
            return 1;
        } else {
            return 0;
        }
    }

    public static int top() {
        if (size == 0) {
            return -1;
        } else {
            return stack[size-1];
        }
    }
}
