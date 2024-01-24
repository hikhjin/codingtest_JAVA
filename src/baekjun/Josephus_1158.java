package baekjun;

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

        while (queue.size() != 1) {
            for (int i = 0; i < k - 1; i++) {
                if
                sb.append(queue.poll());
            }
        }

        System.out.println("<" + sb + ">");
    }
}
