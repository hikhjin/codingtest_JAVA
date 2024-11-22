package SWEA.D_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class LoogRoot2_2814 {
    static Map<Integer, List<Integer>> graph;
    static int max = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            sb.append("#" + t + " ");
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            if (M == 0) {
                sb.append(1);
                if (t != T) sb.append("\n");
                continue;
            }
            graph = new HashMap<>();

            for (int i = 1; i <= N; i++) {
                graph.put(i, new ArrayList<>());
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                graph.get(x).add(y);
                graph.get(y).add(x);
            }

            for (int i = 1; i <= N; i++) {
                boolean[] visited = new boolean[N+1];
                visited[i] = true;
                dfs(i, 1, visited);
                visited[i] = false;
            }

            sb.append(max);
            max = 0;
            if (t != T) sb.append("\n");
        }
        System.out.println(sb);
    }

    static void dfs(int start, int cnt, boolean[] visited) {
        max = Math.max(cnt, max);

        for (int n : graph.get(start)) {
            if (!visited[n]) {
                visited[n] = true;
                dfs(n, cnt + 1, visited);
                visited[n] = false;
            }
        }
    }
}
