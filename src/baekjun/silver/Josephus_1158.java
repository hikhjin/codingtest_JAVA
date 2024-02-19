package baekjun.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Josephus_1158 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        Queue<Integer> queue = new LinkedList<>(); // 큐 선언

        for (int i = 0; i < n; i++) {
            queue.add(i+1);
        }

        int k = Integer.parseInt(st.nextToken());

        while (queue.size() != 1) { // queue에 값이 하나 남을 때까지 반복
            for (int i = 0; i < k - 1; i++) {
                int tmp = queue.poll(); // poll은 queue에 값이 없으면 null을 반환하기 때문에 반복문에서 값이 1개 남을 때까지만 반복하도록 함
                queue.offer(tmp);
            }
            sb.append(queue.poll()).append(", ");
        }
        sb.append(queue.peek()); // 마지막 하나 append

        System.out.println("<" + sb + ">");
    }
}
