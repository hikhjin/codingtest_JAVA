package codetree.samsung;

import java.util.*;
import java.io.*;

public class Explore {
    static int k, m;
    static int[][] map = new int[5][5]; // 기본 맵
    static Queue<Integer> pieces = new LinkedList<>(); // 새로운 유물 조각
    static boolean[][] visited = new boolean[5][5];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int k = Integer.parseInt(st.nextToken()); // 탐사 반복 횟수
        int m = Integer.parseInt(st.nextToken()); // 유물 조각 개수

        for (int i = 0; i < 5; i++) { // map 저장
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) { // 새로운 유물 조각 저장
            int tmp = Integer.parseInt(st.nextToken());
            pieces.offer(tmp);
        }
        int ans = 0;

        for (int i = 0; i < k; i++) { // k번 탐사
            int val = 0;
            int xx = 0; // 이번 턴 최종 x좌표
            int yy = 0; // 이번 턴 최종 y좌표
            int angle = 0; // 이번 턴 최종 각도
            boolean flag = false; // 유물이 더 나오는지 확인

            // 1. 회전(총 9번 * 3각도)
            for (int a = 0; a < 3; a++) { // 각도가 가장 작은 방법 (0:90, 1: 180, 2: 270)
                for (int y = 1; y <= 3; y++) { // 열이 가장 작은 구간
                    for (int x = 1; x <= 3; x++) { // 중심 좌표 기준(행이 가장 작은 구간)
                        int[][] nowMap = makeMap(x, y, a); // 현재 맵
                        visited = new boolean[5][5];
                        // 2. 유물 획득
                        int tmp = 0;
                        for (int j = 0; j < 5; j++) {
                            for (int p = 0; p < 5; p++) {
                                if (!visited[j][p]) {
                                    tmp += bfs(j, p, nowMap);
                                }
                            }
                        }
                        // 3. 가치 계산
                        if (val < tmp) { // 가치 업데이트
                            val = tmp;
                            xx = x;
                            yy = y;
                            angle = a;
                        }
                    }
                }
            }

            // 4. 실제로 회전하고 맵 업데이트
            if (xx == 0 && yy == 0) break;
            map = makeMap(xx, yy, angle);
            val = 0;

            while (true) {
                int tmp = 0;

                visited = new boolean[5][5];
                for (int j = 0; j < 5; j++) {
                    for (int p = 0; p < 5; p++) {
                        if (!visited[j][p]) {
                            tmp += bfs(j, p, map);
                        }
                    }
                }
                if (tmp <= 0) break;
                val += tmp;
                refreshMap(); // 맵 채우기

            }

            sb.append(val).append(" ");
        }

        System.out.println(sb);
    }

    static int[][] copyMap(int[][] target) { // 맵 복사
        int[][] tmp = new int[5][5];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                tmp[i][j] = target[i][j];
            }
        }
        return tmp;
    }

    static int[][] makeMap(int x, int y, int angle) { // 각도 별 회전한 맵 만들기
        int[][] tmp = copyMap(map);

        switch(angle) {
            case 0: // 90도
                tmp = rotation(x, y, tmp);
                break;
            case 1: // 180도
                for (int i = 0; i < 2; i++) {
                    tmp = rotation(x, y, tmp);
                }
                break;
            case 2: // 270도
                for (int i = 0; i < 3; i++) {
                    tmp = rotation(x, y, tmp);
                }
                break;
        }
        return tmp;
    }

    static int[][] rotation(int x, int y, int[][] arr) { // 회전한 배열 만들기
        int[][] tmp3 = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tmp3[j][2-i] = arr[i+x-1][j+y-1];
            }
        }

        int[][] tmp5 = copyMap(arr);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tmp5[x + i - 1][y + j - 1] = tmp3[i][j];
            }
        }
        return tmp5;
    }

    static int bfs(int x, int y, int[][] nowMap) { // 연결된 유물 조각 개수 (3개 이상일 때 cnt리턴, 3개 미만일 경우 0 리턴)
        int[][] dirs = {{1,0}, {0,1}, {-1, 0}, {0, -1}}; // 상하좌우 방향
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;
        int cnt = 1; // 유물 조각 개수
        List<int[]> list = new ArrayList<>(); // 사라질 유물 위치

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            list.add(new int[]{cur[0], cur[1]});

            for (int[] dir : dirs) {
                int dx = cur[0] + dir[0];
                int dy = cur[1] + dir[1];

                if (dx >= 0 && dx < 5 && dy >= 0 && dy < 5) {
                    if (!visited[dx][dy] && (nowMap[dx][dy] == nowMap[cur[0]][cur[1]])) { // 방문하지 않았고 같은 종류면
                        cnt += 1;
                        list.add(new int[]{dx, dy});
                        queue.offer(new int[]{dx, dy});
                        visited[dx][dy] = true;
                    }
                }
            }
        }
        if (cnt >= 3) { // 연결된 유물이 3개 이상일 경우
            for (int[] cur : list) {
                nowMap[cur[0]][cur[1]] = 0;
            }
            return cnt;
        }
        return 0;
    }

    static void refreshMap() { // 사용된 유물인 경우 유물을 채워넣음
        for (int x = 0; x < 5; x++) {
            for (int y = 4; y >= 0; y--) {
                if (map[y][x] == 0 && !pieces.isEmpty()) {
                    map[y][x] = pieces.poll();
                }
            }
        }
    }

    static void printMap(int[][] map) {
        for (int b = 0; b < map.length; b++) {
            System.out.println();
            for (int c = 0; c < map[0].length; c++) {
                System.out.print(map[b][c] + " ");
            }
        }
        System.out.println();
    }
}