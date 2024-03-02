package baekjun.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Play_1697 {
    static int K, N;
    static int visited[] = new int[100001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if (N == K) { // N과 K가 같은 위치 일 경우 0초
            System.out.println(0);
        } else {
            BFS(N);
        }
    }

    static void BFS(int n) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(n);
        visited[n] = 1;

        while(!q.isEmpty()) {
            int tmp = q.poll();

            for (int i = 0; i < 3; i++) {
                int next;
                if (i == 0) {
                    next = tmp + 1;
                } else if (i == 1) {
                    next = tmp - 1;
                } else {
                    next = tmp * 2;
                }

                if (next == K) {
                    System.out.println(visited[tmp]);
                    return;
                }

                if (next >= 0 && next < visited.length && visited[next] == 0) {
                    q.offer(next);
                    visited[next] = visited[tmp] + 1;
                }
            }
        }
    }

}
