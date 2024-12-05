package codetree.samsung;

import java.io.*;
import java.util.*;

public class MagicForest {
    static int[][] map; // 숲
    static int ans = 0;
    static int R, C;
    static int[][] dirs = {{-1,0}, {0,1}, {1,0}, {0,-1}}; //북, 동, 남, 서 (y,x)
    static boolean[][] exit; // 출구 저장하는 배열
    static int golem = 1; // 같은 골렘은 같은 숫자로 체크

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken()); // 정령 수
        map = new int[R+3][C+1];
        exit = new boolean[R+3][C+1];

        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()); // 현재 열 x
            int d = Integer.parseInt(st.nextToken()); // 골렘 출구 방향: 0,1,2,3은 북, 동, 남, 서
            int y = 0; // 현재 행 y

            while (true) {
                int dir = moveDir(y, x);
                if (dir == -1) break; // 움직일 수 없는 경우

                if (dir == 1) { // 출구 방향 변경(동), x y 업데이트
                    d = (d+1) % 4;
                    x += 1;
                    y += 1;
                    System.out.println("동 x, y: " + x + ", " + y);
                }
                if (dir == 3) { // 출구 방향 변경(서), x y 업데이트
                    d = (d+3) % 4;
                    x -= 1;
                    y += 1;
                    System.out.println("이후 서 x, y: " + x + ", " + y);
                }
                if (dir == 2) { // x y 업데이트 (남)
                    y += 1;
                    System.out.println("남 x, y: " + x + ", " + y);
                }
            }

            if (!isForest(y, x)) { // 골렘이 숲 밖으로 벗어나 있는 경우
                map = new int[R+3][C+1]; // map 초기화
                exit = new boolean[R+3][C+1]; // 출구 초기화
                continue;
            }

            for (int[] dir : dirs) { // 골렘 위치 저장
                int nowR = dir[0] + y;
                int nowC = dir[1] + x;
                map[nowR][nowC] = golem;
            }
            map[y][x] = golem; // 가운데 위치
            golem++;

            int dR = y + dirs[d][0]; // 출구 위치 찾기
            int dC = x + dirs[d][1];
            exit[dR][dC] = true; // 출구 위치 저장

            for (int k = 0; k < R+3; k++) {
                System.out.println();
                for (int j = 1; j < C+1; j++) {
                    System.out.print(map[k][j] + " ");
                }
            }

            System.out.println();
            System.out.println("출구: " + d);

            int result = bfs(y, x) - 2;
            System.out.println(result);
            // 골렘 내부에서 정령 이동
            ans += result;

        }

        System.out.println(ans);
    }

    static int moveDir(int y, int x) { // 1,2,3은 동, 남, 서
        if (canMoveSouth(y, x, 2)) {
            return 2; // 남
        } else {
            if (canMoveSouth(y, x-1, 3)) {
                if(x-2 < 1 || map[y][x-2]!=0) return -1;
                else return 3; // 서
            } else if (canMoveSouth(y, x+1, 1)) {
                if (x+2 > C || map[y][x+2]!=0) return -1;
                return 1; // 동
            } else return -1; // 더 이상 움직이지 못하는 경우
        }
    }

    static boolean canMoveSouth(int y, int x, int dir) { // 남쪽으로 내려갈 수 있는지

        if (y+2 <= R+2 && x-1 >= 1 && x+1 <= C) {
            if (map[y+1][x-1] == 0 && map[y+2][x] == 0 && map[y+1][x+1] == 0) { // 내려갈 세 칸 비어있는 경우
                return true;
            }
        }
        return false;
    }



    static boolean isForest(int y, int x) { // 골렘이 숲 범위 안에 있는지 확인
        return 3 < y && y < R+2 && 1 < x && x < C;
    }

    static boolean isRange(int y, int x) { //(y,x)가 숲 범위 안에 있는지 확인
        return 2 < y && y < R+3 && 0 < x && x <= C;
    }

    static int bfs(int y, int x) {
        int maxR = y;

        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[R+3][C+1];
        queue.offer(new int[]{y, x});
        visited[y][x] = true;

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            maxR = Math.max(maxR, cur[0]);

            for (int[] dir : dirs) {
                int dy = cur[0] + dir[0];
                int dx = cur[1] + dir[1];

                if (isRange(dy, dx) && !visited[dy][dx] && ((map[dy][dx] == map[cur[0]][cur[1]]) || (map[dy][dx] != 0 && exit[cur[0]][cur[1]]))) {
                    queue.offer(new int[]{dy, dx});
                    visited[dy][dx] = true;
                }
            }
        }
        return maxR;
    }
}
