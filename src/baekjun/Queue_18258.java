package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Queue_18258 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        LinkedList<Integer> queue = new LinkedList<>();
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            switch(s) {
                case "push":
                    queue.add(Integer.parseInt(st.nextToken()));
                    break;
                case "pop":
                    if (queue.size() == 0) { // queue에 아무것도 없을 때
                        sb.append(-1).append("\n");
                    } else {
                        sb.append(queue.getFirst()).append("\n");
                        queue.removeFirst();
                    }
                    break;
                case "size":
                    sb.append(queue.size()).append("\n");
                    break;
                case "empty":
                    if (queue.size() == 0) {
                        sb.append(1).append("\n");
                    } else {
                        sb.append(0).append("\n");
                    }
                    break;
                case "front":
                    if (queue.size() == 0) {
                        sb.append(-1).append("\n");
                    } else {
                        sb.append(queue.getFirst()).append("\n");
                    }
                    break;
                case "back":
                    if (queue.size() == 0) {
                        sb.append(-1).append("\n");
                    } else {
                        sb.append(queue.getLast()).append("\n");
                    }
                    break;
            }
        }
        System.out.println(sb);
    }
}
