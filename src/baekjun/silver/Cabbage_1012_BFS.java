package baekjun.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Cabbage_1012_BFS {
    static boolean [][] visited;
    static int [][] arr;
    static int M, N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine()); // 테스트 케이스

        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken()); // 가로 길이
            N = Integer.parseInt(st.nextToken()); // 세로 길이
            K = Integer.parseInt(st.nextToken()); // 배추 개수

            arr = new int[M][N];
            visited = new boolean[M][N];

            for (int j = 0; j < K; j++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                arr[x][y] = 1;
            }

            int ans = 0;

            for (int k = 0; k < M; k++) {
                for (int p = 0; p < N; p++) {
                    if (!visited[k][p] && arr[k][p] == 1) {
                        ans += 1;
                        BFS(k, p);
                    }
                }
            } System.out.println(ans);
        }
    }

    static void BFS(int x, int y) {
        Queue<int []> q = new LinkedList<>();
        q.offer(new int[] {x, y});
        visited[x][y] = true;

        int [] dx = {0, 0, 1, -1};
        int [] dy = {1, -1, 0, 0}; // 상하좌우 4방향

        while (!q.isEmpty()) {
            int[] poll = q.poll();

            for (int i = 0; i < 4; i++) {
                int xx = poll[0] + dx[i];
                int yy = poll[1] + dy[i];

                if (xx < M && xx >= 0 && yy < N && yy >= 0) {
                    if (!visited[xx][yy] && arr[xx][yy] == 1) {
                        q.offer(new int[] {xx, yy});
                        visited[xx][yy] = true;
                    }
                }
            }
        }
    }
}
