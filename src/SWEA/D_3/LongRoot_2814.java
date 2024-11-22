package SWEA.D_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class LongRoot_2814 {
    static int max = 0;
    static int N;
    static int[][] arr;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            sb.append("#").append(t).append(" ");
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            if (M == 0) {
                sb.append(1);
                if (t != T) sb.append("\n");
                continue;
            }

            arr = new int[N+1][N+1];
            for (int i = 1; i <= M; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                arr[x][y] = 1;
                arr[y][x] = 1;
            }

            visited = new boolean[N+1];
            for (int i = 0; i < N; i++) {
                visited[i] = true;
                dfs(i, 1);
                visited[i] = false;
            }
            sb.append(max);
            max = 0;
            if (t != T) sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    static void dfs(int i, int cnt) {
        if (cnt > max) max = cnt;

        for (int j = 1; j <= N; j++) {
            if (!visited[j] && arr[i][j] == 1) {
                visited[j] = true;
                dfs(j, cnt+1);
                visited[j] = false;
            }
        }
    }
}
