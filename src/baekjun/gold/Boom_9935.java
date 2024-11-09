package baekjun.gold;

import java.io.*;
import java.util.Stack;

public class Boom_9935 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String s = br.readLine();
        String boom = br.readLine();
        int blen = boom.length();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            stack.push(s.charAt(i));

            if (stack.size() >= blen) {
                boolean flag = true;
                for (int j = 0; j < blen; j++) {
                    if (stack.get(stack.size() - blen + j) != boom.charAt(j)) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    for (int j = 0; j < blen; j++) {
                        stack.pop();
                    }
                }
            }
        }

        if (stack.isEmpty()) {
            bw.write("FRULA");
        } else {
            StringBuilder sb = new StringBuilder();
            for (char c : stack) {
                sb.append(c);
            }
            bw.write(sb.toString());
        }
        bw.flush();
        bw.close();
    }
}
