package baekjun.silver;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Paint_1926 {
    static boolean[][] visited;
    static int[][] arr;
    static int n, m;
    static int area = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int cnt = 0; // 그림의 개수

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 세로
        m = Integer.parseInt(st.nextToken()); // 가로

        visited = new boolean[n][m];
        arr = new int[n][m];

        for (int i = 0; i < n; i++) { // 배열에 값 입력
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visited[i][j] == false && arr[i][j] == 1) {
                    cnt++; // 그림 개수 추가
                    BFS(i, j);
                }
            }
        }
        System.out.println(cnt);
        System.out.println(area);
    }

    static void BFS(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};
        int tmp = 1; // 넓이 저장

        queue.offer(new int[] {x, y});
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = poll[0] + dx[i];
                int ny = poll[1] + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                    if (visited[nx][ny] == false && arr[nx][ny] == 1) {
                        queue.offer(new int[] {nx, ny});
                        visited[nx][ny] = true;
                        tmp++;
                    }
                }
            }
        }
        if (tmp > area) {
            area = tmp;
        }
    }
}
