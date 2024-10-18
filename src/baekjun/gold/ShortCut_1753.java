package baekjun.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ShortCut_1753 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine()); // 시작 노드

        Map<Integer, List<Node>> graph = new HashMap<>();

        for (int i = 1; i <= V; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int i = 1; i <= E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph.get(u).add(new Node(v, w));
        }

        int[] costs = new int[V + 1];
        Arrays.fill(costs, Integer.MAX_VALUE);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(K, 0));
        costs[K] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (costs[cur.node] < cur.cost) continue;

            for (Node next : graph.get(cur.node)) {
                int nextCost = next.cost + costs[cur.node];
                if (nextCost < costs[next.node]) {
                    costs[next.node] = nextCost;
                    pq.offer(new Node(next.node, nextCost));
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= V; i++) {
            if (i == K) sb.append(0).append("\n");
            else if (costs[i] == Integer.MAX_VALUE) sb.append("INF\n");
            else sb.append(costs[i]).append("\n");
        }

        System.out.println(sb);
    }
}

class Node implements Comparable<Node> {
    int node;
    int cost;

    public Node(int node, int cost) {
        this.node = node;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.cost, o.cost);
    }
}
