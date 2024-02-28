package baekjun.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Apartment_2667 {
    static int [][] map;
    static int N, tmp;
    static boolean [][] visited;
    static int cnt; // 총 단지수
    static List<Integer> ans = new ArrayList<>(); // 단지 내 집의 수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine()); // 지도 크기
        map = new int[N][N]; // 지도 배열
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = Character.getNumericValue(s.charAt(j));
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && map[i][j] == 1) {
                    cnt += 1;
                    DFS(i, j);
                    ans.add(tmp+1);
                    tmp = 0;
                }
            }
        }
        System.out.println(cnt);

        ans.sort(Comparator.naturalOrder());
        for (Integer an : ans) {
            System.out.println(an);
        }
    }

    static void DFS(int x, int y) {
        int [] dx = {0, 0, 1, -1};
        int [] dy = {1, -1, 0, 0};
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int xx = x + dx[i];
            int yy = y + dy[i];

            if (xx < N && xx >= 0 && yy < N && yy >= 0) {
                if (!visited[xx][yy] && map[xx][yy] == 1) {
                    tmp += 1;
                    DFS(xx, yy);
                }
            }

        }
    }
}
