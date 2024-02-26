package baekjun.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Cabbage_1012 {
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
                        DFS(k, p);
                    }
                }
            } System.out.println(ans);
        }
    }
    static void DFS(int x, int y) {
        int [] dx = {0, 0, 1, -1};
        int [] dy = {1, -1, 0, 0}; // 상하좌우 4방향
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) { // 4 방향 모두 확인
            int xx = x + dx[i];
            int yy = y + dy[i]; // 좌표 변경

            if (xx < M && xx >= 0 && yy < N && yy >= 0) { // 주어진 배열 넘어가지 않도록
                if (!visited[xx][yy] && arr[xx][yy] == 1) {
                    DFS(xx, yy);
                }
            }
        }
    }
}
