package baekjun.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class SortingCard_1715 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // 우선순위 큐 사용
        int ans = 0; // 최소 비교 횟수

        for (int i = 0; i < N; i++) {
            pq.add(Integer.parseInt(br.readLine())); // 우선순위 큐에 하나씩 넣어줌
        }

        while(pq.size() > 1) {
            int tmp = 0;
            tmp = pq.poll() + pq.poll(); // 제일 작은 카드 두 묶음을 뽑아서 합침
            ans += tmp;
            pq.add(tmp); // 합친 것을 다시 우선순위 큐에 넣음
        }

        System.out.println(ans);
    }
}
