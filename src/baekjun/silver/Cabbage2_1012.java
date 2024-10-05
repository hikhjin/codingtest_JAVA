package baekjun.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Cabbage2_1012 {
    static int[][] map, visited;
    static int M, N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken()); // 가로
            N = Integer.parseInt(st.nextToken()); // 세로
            K = Integer.parseInt(st.nextToken()); // 배추 개수

            map = new int[M][N];
            visited = new int[M][N];
            int cnt = 0;
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                map[x][y] = 1;
            }

            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == 1 && visited[i][j] == 0) {
                        cnt++;
                        BFS(i, j);
                    }
                }
            }
            sb.append(cnt + "\n");
        }
        System.out.println(sb);
    }

    static void BFS(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};

        queue.offer(new int[]{x, y});
        visited[x][y] = 1;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = poll[0] + dx[i];
                int ny = poll[1] + dy[i];

                if (nx >= 0 && nx < M && ny >= 0 && ny < N) {
                    if (map[nx][ny] == 1 && visited[nx][ny] == 0) {
                        queue.offer(new int[]{nx, ny});
                        visited[nx][ny] = 1;
                    }
                }
            }
        }

    }
}
