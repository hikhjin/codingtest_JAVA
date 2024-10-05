package programmers.lv3;

import java.util.*;

class Solution_oil {
    static int n, m;
    static boolean[][] visited;
    static int[] oil;

    public int solution(int[][] land) {
        n = land.length;
        m = land[0].length;
        visited = new boolean[n][m];
        oil = new int[m];
        int max = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && land[i][j] == 1) {
                    bfs(i, j, land);
                }
            }
        }

        for (int i = 0; i < m; i++) {
            max = Math.max(max, oil[i]);
        }
        System.out.println(max);
        return max;
    }

    static void bfs(int x, int y, int[][] land) {
        Queue<int[]> queue = new LinkedList<>();
        int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        int cnt = 0;
        queue.offer(new int[] {x, y});
        visited[x][y] = true;
        Set<Integer> set = new HashSet<>();

        while (!queue.isEmpty()) {
            int cur[] = queue.poll();
            cnt++;
            set.add(cur[1]);

            for (int[] dir : dirs) {
                int nx = cur[0] + dir[0];
                int ny = cur[1] + dir[1];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                    if (!visited[nx][ny] && land[nx][ny] == 1) {
                        queue.offer(new int[]{nx, ny});
                        visited[nx][ny] = true;
                    }
                }
            }
        }

        for (int s : set) {
            oil[s] += cnt;
        }
    }
}

class oils {
    public static void main(String[] args) {
        Solution_oil o = new Solution_oil();
        int[][] land = {{0, 0, 0, 1, 1, 1, 0, 0}, {0, 0, 0, 0, 1, 1, 0, 0}, {1, 1, 0, 0, 0, 1, 1, 0}, {1, 1, 1, 0, 0, 0, 0, 0}, {1, 1, 1, 0, 0, 0, 1, 1}};
        o.solution(land);
    }
}
