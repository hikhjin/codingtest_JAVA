package baekjun.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MinCost_1916 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 도시 개수
        int M = Integer.parseInt(br.readLine()); // 버스 개수
        Map<Integer, List<Bus>> graph = new HashMap<>();
        StringTokenizer st;

        for (int i = 1; i <= N; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph.get(u).add(new Bus(v, w));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        int[] costs = new int[N+1];
        Arrays.fill(costs, Integer.MAX_VALUE);

        PriorityQueue<Bus> pq = new PriorityQueue<>();
        pq.offer(new Bus(start, 0));
        costs[start] = 0;

        while (!pq.isEmpty()) {
            Bus cur = pq.poll();
            if (cur.cost > costs[cur.node]) continue;

            for (Bus next : graph.get(cur.node)) {
                int nextCost = next.cost + costs[cur.node];
                if (nextCost < costs[next.node]) {
                    costs[next.node] = nextCost;
                    pq.offer(new Bus(next.node, nextCost));
                }
            }
        }
        System.out.println(costs[end]);
    }
}

class Bus implements Comparable<Bus> {
    int node;
    int cost;

    public Bus(int node, int cost) {
        this.node = node;
        this.cost = cost;
    }

    @Override
    public int compareTo(Bus o) {
        return Integer.compare(this.cost, o.cost);
    }
}
