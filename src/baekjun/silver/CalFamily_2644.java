package baekjun.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CalFamily_2644 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 전체 사람 수
        StringTokenizer st = new StringTokenizer(br.readLine());
        int num1 = Integer.parseInt(st.nextToken());
        int num2 = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(br.readLine()); // 관계 개수

        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int i = 1; i <= n; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            graph.get(n1).add(n2);
            graph.get(n2).add(n1);
        }

        boolean[] visited = new boolean[n+1];

        int ans = BFS(num1, num2, graph, visited);
        System.out.println(ans);
    }

    static int BFS(int start, int target, Map<Integer, List<Integer>> graph, boolean[] visited) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{start, 0});
        visited[start] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int node = cur[0];
            int chonsu = cur[1];

            if (node == target) {
                return chonsu;
            }

            for (int nextNode : graph.get(node)) {
                if (!visited[nextNode]) {
                    visited[nextNode] = true;
                    queue.offer(new int[]{nextNode, chonsu + 1});
                }
            }
        }
        return -1;
    }
}
