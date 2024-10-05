package baekjun.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Colors_10026 {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        char[][] map = new char[N][N];
        char[][] map2 = new char[N][N]; // 적록색약
        boolean[][] visited = new boolean[N][N];
        boolean[][] visited2 = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = s.charAt(j);
                map2[i][j] = s.charAt(j);
                if (map2[i][j] == 'G') {
                    map2[i][j] = 'R';
                }
            }
        }

        int cnt1 = 0;
        int cnt2 = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    cnt1++;
                    char c = map[i][j];
                    bfs(i, j, c, map, visited);
                }
                if (!visited2[i][j]) {
                    cnt2++;
                    char c2 = map2[i][j];
                    bfs(i, j, c2, map2, visited2);
                }
            }
        }
        System.out.println(cnt1 + " " + cnt2);
    }

    static void bfs(int x, int y, char c, char[][] board, boolean[][] visited) {
        Queue<int[]> queue = new LinkedList<>();
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        queue.offer(new int[]{x, y});
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int[] dir : dirs) {
                int nx = cur[0] + dir[0];
                int ny = cur[1] + dir[1];

                if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                    if (!visited[nx][ny] && board[nx][ny] == c) {
                        visited[nx][ny] = true;
                        queue.offer(new int[]{nx, ny});
                    }
                }
            }
        }
    }
}
