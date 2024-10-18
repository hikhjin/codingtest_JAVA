package baekjun.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Cow_5972 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Map<Integer, List<Cow>> graph = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph.get(u).add(new Cow(v, c));
            graph.get(v).add(new Cow(u, c));
        }

        int[] costs = new int[N+1];
        Arrays.fill(costs, Integer.MAX_VALUE);
        PriorityQueue<Cow> pq = new PriorityQueue<>();
        pq.offer(new Cow(1, 0));
        costs[1] = 0;

        while (!pq.isEmpty()) {
            Cow cur = pq.poll();
            if (cur.cost > costs[cur.node]) continue;

            for (Cow next : graph.get(cur.node)) {
                int nextCost = next.cost + costs[cur.node];
                if (nextCost < costs[next.node]) {
                    costs[next.node] = nextCost;
                    pq.offer(new Cow(next.node, nextCost));
                }
            }
        }
        System.out.println(costs[N]);
    }
}

class Cow implements Comparable<Cow> {
    int node;
    int cost;

    public Cow(int node, int cost) {
        this.node = node;
        this.cost = cost;
    }

    @Override
    public int compareTo(Cow o) {
        return Integer.compare(this.cost, o.cost);
    }
}
