package baekjun.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Tomatoes2_7576 {
    static int N, M;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        Queue<int[]> queue = new LinkedList<>(); // 익은 토마토(시작점) 넣을 큐

        boolean flag = true;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    queue.offer(new int[]{i, j});
                }
                if (map[i][j] == 0) flag = false;
            }
        }
        if (flag) { // 모두 익은 토마토일 경우
            System.out.println(0);
            return;
        }

        bfs(queue);

        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) { // 다 익지 못하는 경우
                    System.out.println(-1);
                    return;
                }
                max = Math.max(max, map[i][j]);
            }
        }
        System.out.println(max-1);
    }

    static void bfs(Queue<int[]> queue) {
        int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int[] dir : dirs) {
                int nx = cur[0] + dir[0];
                int ny = cur[1] + dir[1];

                if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                    if (map[nx][ny] == 0) {
                        queue.offer(new int[]{nx, ny});
                        map[nx][ny] = map[cur[0]][cur[1]] + 1;
                    }
                }
            }
        }
    }
}
