package baekjun.silver;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Maze_2_2178 {
    static int N, M;
    static int[][] arr;
    static int[][] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        dist = new int[N][M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = s.charAt(j) - '0';
                dist[i][j] = -1; // 초기화
            }
        }

        BFS(0,0);
        System.out.println(dist[N-1][M-1]);
    }

    static void BFS(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};

        queue.offer(new int[]{x, y});
        dist[x][y] = 1; // 1부터 시작

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = poll[0] + dx[i];
                int ny = poll[1] + dy[i];

                if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                    if (dist[nx][ny] == -1 && arr[nx][ny] == 1) {
                        queue.offer(new int[]{nx, ny});
                        dist[nx][ny] = dist[poll[0]][poll[1]] + 1; // 이전에 머물렀던 거리보다 +1
                    }
                }
            }
        }
    }
}
