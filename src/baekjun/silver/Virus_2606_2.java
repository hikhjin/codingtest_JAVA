package baekjun.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Virus_2606_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine()); // 전체 컴퓨터 수
        int M = Integer.parseInt(br.readLine()); // 연결된 컴퓨터 수

        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int i = 1; i <= N; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        boolean[] visited = new boolean[N + 1];
        visited[1] = true; // 1번 컴퓨터
        int ans = BFS(1, graph, visited);
        System.out.println(ans);
    }

    static int BFS(int start, Map<Integer, List<Integer>> graph, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        int cnt = 0;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int computer : graph.get(cur)) {
                if (!visited[computer]) {
                    cnt++;
                    visited[computer] = true;
                    queue.offer(computer);
                }
            }
        }
        return cnt;
    }
}
