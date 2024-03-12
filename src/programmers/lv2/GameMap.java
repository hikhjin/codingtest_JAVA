package programmers.lv2;

import java.util.*;

class Solution_GameMap {
    static int[][] visited;

    public int solution(int[][] maps) {
        visited = new int[maps.length][maps[0].length];
        BFS(0, 0, maps);
        int answer = visited[maps.length - 1][maps[0].length - 1];
        if (answer == 0) {
            answer = -1;
        }

        return answer;
    }

    static void BFS(int x, int y, int[][] maps) {
        Queue<int[]> queue = new LinkedList<>();
        int [] dx = {0, 0, 1, -1};
        int [] dy = {1, -1, 0, 0};

        queue.offer(new int[] {x, y});
        visited[x][y] = 1;

        while(!queue.isEmpty()) {
            int poll[] = queue.poll();

            for (int i = 0; i < 4; i++) {
                int xx = poll[0] + dx[i]; // 다음 좌표
                int yy = poll[1] + dy[i];

                if (xx >= 0 && yy >= 0 && xx < maps.length && yy < maps[0].length) {
                    if (visited[xx][yy] == 0 && maps[xx][yy] == 1) {
                        visited[xx][yy] = visited[poll[0]][poll[1]] + 1;
                        queue.offer(new int[] {xx, yy});
                    }
                }
            }
        }

    }
}
