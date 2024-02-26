package baekjun.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class DFSBFS_1260 {

    static int N, M, V;
    static boolean[] visited;
    static int[][] arr;
    static Queue<Integer> queue = new LinkedList<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 정점 개수
        M = Integer.parseInt(st.nextToken()); // 간선 개수
        V = Integer.parseInt(st.nextToken()); // 탐색 시작 번호

        visited = new boolean[10001]; // 방문 했는지 확인
        arr = new int[1001][1001];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            arr[u][v] = arr[v][u] = 1; // 양방향 간선 저장
        }
        DFS(V);
        sb.append("\n");
        visited = new boolean[10001]; // BFS 실행 위한 초기화
        BFS(V);

        System.out.println(sb);
    }
    static void DFS(int v) {
        visited[v] = true; // 방문한 노드 true로 변경
        sb.append(v).append(" ");
        for (int i = 1; i <= N; i++) {
            if (!visited[i] && arr[v][i] == 1) { // 방문하지 않았고 간선으로 연결되어 있을 경우
                DFS(i); // 재귀 호출
            }
        }
    }

    static void BFS(int v){
        queue.offer(v); // queue에 시작 정점 추가
        visited[v] = true; // 방문한 노드 true로 변경
        sb.append(v).append(" ");

        while (!queue.isEmpty()) { // 큐가 빌 때까지 반복
            int tmp = queue.poll(); // 큐에서 하나 뻄 (방문)

            for (int i = 0; i <= N; i++) {
                if (!visited[i] && arr[tmp][i] == 1) { // 방문하지 않았고 간선으로 연결되어 있을 경우
                    queue.offer(i); // 큐에 추가
                    visited[i] = true;
                    sb.append(i).append(" ");
                }
            }
        }

    }

}
