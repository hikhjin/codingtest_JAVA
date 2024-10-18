package baekjun.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DecreaseNum_1038 {
    static List<Long> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        if (N < 10) {
            System.out.println(N);
            return;
        }

        if (N >= 1023) {
            System.out.println(-1);
            return;
        }
        backtrack();

        Collections.sort(list);
        System.out.println(list.get(N));
    }

    static void backtrack() {
        Queue<Long> queue = new LinkedList<>();

        for (int i = 0; i < 10; i++) {
            queue.offer((long) i);
        }

        while (!queue.isEmpty()) {
            long cur = queue.poll();
            list.add(cur);

            int lastDigit = (int) (cur % 10);
            for (int i = 0; i < lastDigit; i++) {
                queue.offer(cur * 10 + i);
            }
        }

    }
}

