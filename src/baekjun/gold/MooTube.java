package baekjun.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MooTube {
    static int N;
    static Map<Integer, List<Moo>> graph = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= N; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            int u = Integer.parseInt(st.nextToken());

            graph.get(p).add(new Moo(q, u));
            graph.get(q).add(new Moo(p, u));
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            int res = bfs(k, v);
            sb.append(res).append("\n");
        }

        System.out.println(sb);
    }

    static int bfs(int k, int v) {
        boolean[] visited = new boolean[N+1];
        Queue<Integer> queue = new LinkedList<>();
        int cnt = 0;
        queue.offer(v);
        visited[v] = true;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (Moo next : graph.get(cur)) {
                if (!visited[next.node] && next.cost >= k) {
                    visited[next.node] = true;
                    queue.offer(next.node);
                    cnt++;
                }
            }
        }
        return cnt;
    }
}

class Moo implements Comparable<Moo> {
    int node;
    int cost;

    public Moo(int node, int cost) {
        this.node = node;
        this.cost = cost;
    }

    @Override
    public int compareTo(Moo o) {
        return Integer.compare(this.cost, o.cost);
    }
}
