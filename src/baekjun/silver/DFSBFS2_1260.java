package baekjun.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DFSBFS2_1260 {
    static boolean[] visited;
    static int N, M, V;
    static StringBuilder sb = new StringBuilder();
    static Map<Integer, List<Integer>> graph = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 정점 개수
        M = Integer.parseInt(st.nextToken()); // 간선 개수
        V = Integer.parseInt(st.nextToken()); // 시작 노드

        visited = new boolean[N + 1];

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

        for (int i = 1; i <= N; i++) {
            Collections.sort(graph.get(i));
        }

        dfs(V);
        sb.append("\n");
        Arrays.fill(visited, false);
        bfs(V);

        System.out.println(sb);

    }

    static void dfs(int start) {
        visited[start] = true;
        sb.append(start).append(" ");

        for (int v : graph.get(start)) {
            if (!visited[v]) {
                dfs(v);
            }
        }
    }

    static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            sb.append(cur).append(" ");

            for (int v : graph.get(cur)) {
                if (!visited[v]) {
                    queue.offer(v);
                    visited[v] = true;
                }
            }
        }
    }
}
