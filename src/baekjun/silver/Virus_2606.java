package baekjun.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Virus_2606 {

    static boolean [] visited;
    static int [][] arr;
    static int C, N, ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        C = Integer.parseInt(br.readLine()); // 컴퓨터 수
        N = Integer.parseInt(br.readLine()); // 컴퓨터 쌍의 수

        visited = new boolean[10001];
        arr = new int[101][101];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            arr[u][v] = arr[v][u] = 1;
        }

        DFS(1);
        System.out.println(ans);
    }

    static void DFS(int v) {
        visited[v] = true;
        for (int i = 1; i <= C; i++) {
            if (!visited[i] && arr[v][i] == 1) {
                ans += 1;
                DFS(i);
            }
        }

    }

}
