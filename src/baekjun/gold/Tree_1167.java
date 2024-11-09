package baekjun.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Tree_1167 {
    static int[] distance;
    static boolean[] visited;
    static Map<Integer, ArrayList<Tree>> graph = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int V = Integer.parseInt(br.readLine());

        for (int i = 1; i <= V; i++) {
            graph.put(i, new ArrayList<>());
        }
        distance = new int[V+1];

        for (int i = 0; i < V; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            while (true) {
                int v = Integer.parseInt(st.nextToken());
                if (v == -1) break;
                int w = Integer.parseInt(st.nextToken());
                graph.get(u).add(new Tree(v, w));
            }
        }
        visited = new boolean[V+1];
        bfs(1);
        visited = new boolean[V+1];
        int max = Integer.MIN_VALUE;
        int idx = 0;
        for (int i = 1; i <= V; i++) {
            if (max < distance[i]) {
                max = distance[i];
                idx = i;
            }
        }
        Arrays.fill(distance, 0);
        bfs(idx);
        Arrays.sort(distance);
        System.out.println(distance[V]);
    }

    static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (Tree t : graph.get(cur)) {
                int n = t.node;
                int w = t.weight;
                if (!visited[n]) {
                    visited[n] = true;
                    queue.offer(n);
                    distance[n] = distance[cur] + w;
                }
            }
        }
    }
}

class Tree implements Comparable<Tree> {
    int node;
    int weight;

    public Tree(int node, int weight) {
        this.node = node;
        this.weight = weight;
    }

    @Override
    public int compareTo(Tree o) {
        return Integer.compare(this.weight, o.weight);
    }
}
