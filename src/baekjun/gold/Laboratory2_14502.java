package baekjun.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Laboratory2_14502 {
    static int N, M;
    static int[][] map;
    static int ans = 0;
    static List<int[]> start = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    start.add(new int[]{i, j});
                }
            }
        }

        wall(0);

        System.out.println(ans);
    }

    static void wall(int cnt) { // 벽 세우기
        if (cnt == 3) {
            bfs();
            return;
        }
        for (int j = 0; j < N; j++) {
            for (int k = 0; k < M; k++) {
                if (map[j][k] == 0) {
                    map[j][k] = 1;
                    wall(cnt + 1);
                    map[j][k] = 0;
                }
            }
        }
    }

    static void bfs() {
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int[][] copyMap = new int[N][M];
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < N; i++) { // 시작점 (바이러스) 추가
            for (int j = 0; j < M; j++) {
                copyMap[i][j] = map[i][j];
            }
        }

        for (int[] s : start) {
            queue.offer(s);
        }

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int[] dir : dirs) {
                int nx = cur[0] + dir[0];
                int ny = cur[1] + dir[1];

                if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                    if (copyMap[nx][ny] == 0) {
                        copyMap[nx][ny] = 2;
                        queue.offer(new int[]{nx, ny});
                    }
                }
            }
        }

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (copyMap[i][j] == 0) {
                    cnt++;
                }
            }
        }
        ans = Math.max(cnt, ans);
    }
}
