package codetree.samsung;

import java.io.*;
import java.util.*;

public class MazeRunner {
    static int[][] maze;
    static int[] exit = new int[2]; // 출구 y, x 좌표 계산
    static int N, M, K;
    static int ans = 0;
    static int[][] runners;
    static int[][] dirs = {{-1,0}, {1,0}, {0,-1}, {0,1}}; // 상 하 좌 우

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 미로 크기
        M = Integer.parseInt(st.nextToken()); // 참가자 수
        K = Integer.parseInt(st.nextToken()); // 게임 시간
        maze = new int[N+1][N+1];
        runners = new int[M][2];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                maze[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            if (maze[y][x] < 0 && maze[y][x] != -11) { // 만약 이미 해당 칸에 참여자가 있다면
                maze[y][x] -= 1; // 카운트 +1
                continue;
            }
            maze[y][x] = -1; // 참가자는 음수로 표현
            runners[i][0] = y;
            runners[i][1] = x;
        }

        st = new StringTokenizer(br.readLine());
        exit[0] = Integer.parseInt(st.nextToken());
        exit[1] = Integer.parseInt(st.nextToken());
        maze[exit[0]][exit[1]] = -11; // 출구는 -11로 표현

         System.out.println("초기 맵");
         printMap(maze);
         System.out.println();

        for (int i = 1; i <= K; i++) {
            if (isEveryoneEscape()) break;
            moveRunner();
             System.out.println("runner 이동 후");
             printMap(maze);
            rotate();
             System.out.println("정사각형 회전 후");
             printMap(maze);
        }

        System.out.println(ans);
        System.out.println(exit[0] + " " + exit[1]);
    }

    // 참가자 움직이기
    static void moveRunner() {
        boolean[][] visited = new boolean[N+1][N+1]; // 이미 움직인 참가자 저장


        for (int i = 1; i <= runners.length; i++) {

        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (maze[i][j] != -11 && maze[i][j] < 0 && !visited[i][j]) { // 현재 칸이 아직 움직이지 않은 참가자일 경우
                    int nowY = i;
                    int nowX = j;
                    int y = i; // 바꿀 인덱스 임시저장
                    int x = j;
                    int dist = calDist(y, x);
                    System.out.println("시작 y " + y + " x " + x);
                    System.out.println(dist);
                    System.out.println("exit: " + exit[0] + " " + exit[1]);

                    for (int[] dir : dirs) { // 상하좌우 순서로 이동
                        int nextY = nowY + dir[0];
                        int nextX = nowX + dir[1];
                        System.out.println("nextY: " + nextY + " nextX: " + nextX);
                        System.out.println(calDist(nextY, nextX));

                        if (isRange(nextY, nextX) && !(maze[nextY][nextX] > 0)) { // 범위 내에 있고 벽이 아니면
                            int nextDist = calDist(nextY, nextX);
                            System.out.println("nextDist: " + nextDist);
                            if (nextDist < dist) { // 거리 업데이트
                                System.out.println("update");
                                System.out.println("y: " + nextY + " x: " + nextX);
                                dist = nextDist;
                                y = nextY;
                                x = nextX;
                                break;
                            }
                        }
                    }

                    if (y == nowY && x == nowX) continue; // 움직이지 않았을 경우 continue
                    int cnt = -maze[nowY][nowX];
                    ans += cnt;

                    if (maze[y][x] < 0 && maze[y][x] != -11) cnt += -maze[y][x]; // 이동할 칸에 이미 참가자가 있는 경우 카운트 증가
                    System.out.println("최종 인덱스 " + y + " " + x);
                    maze[nowY][nowX] = 0; // 맵 업데이트
                    if (maze[y][x] == -11) { // 탈출했을 경우
                         System.out.println("탈출");
                    } else {
                        maze[y][x] = -cnt; // 탈출하지 않았을 경우는 맵 업데이트
                        visited[y][x] = true;
                    }
                    printMap(maze);
                     System.out.println("ans: " + ans);
                }
            }
        }
    }

    // 정사각형 회전
    static void rotate() {
        for (int h = 2; h <= N; h++) { // 정사각형 한 면 크기는 최소 2부터 시작
            for (int y = 1; y <= N-h+1; y++) {
                for (int x = 1; x <= N-h+1; x++) {
                    if (canRotate(y, x, h)) { // 회전 가능할 경우
                         System.out.println("rotate " + y + " " + x + " " + h);
                        rotateRec(y, x, h); // 정사각형 회전
                        return;
                    }
                }
            }
        }
    }

    // 주어진 정사각형을 돌릴 수 있는지
    static boolean canRotate(int y, int x, int h) {
        int cnt = 0; // 정사각형 안 참가자 수
        boolean findExit = false; // 출구 포함 여부

        for(int i = y; i < y+h; i++) {
            for (int j = x; j < x+h; j++) {
                if (maze[i][j] < 0 && maze[i][j] != -11) cnt++;
                if (maze[i][j] == -11) findExit = true;
            }
        }
        return (cnt > 0 && findExit); // 참가자가 1명 이상이고 출구를 포함하는지 여부
    }

    // 정사각형 회전
    static void rotateRec(int y, int x, int h) {
        int[][] tmpArr = new int[h][h];

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < h; j++) {
                tmpArr[j][h-1-i] = maze[i+y][j+x];
                if (tmpArr[j][h-1-i] > 0) tmpArr[j][h-1-i] -= 1; // 내구도 하락
            }
        }

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < h; j++) {
                maze[i+y][j+x] = tmpArr[i][j];
            }
        }
        putExit();
    }

    // 출구와 참가자 거리 계산
    static int calDist(int y, int x) {
        int exitY = exit[0];
        int exitX = exit[1];
        return Math.abs(exitX - x) + Math.abs(exitY - y);
    }

    // exit에 출구 체크
    static void putExit() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (maze[i][j] == -11) {
                    exit[0] = i;
                    exit[1] = j;
                }
            }
        }
    }

    // 좌표가 maze 범위 안인지 확인
    static boolean isRange(int y, int x) {
        return (y >= 1 && y <= N && x >= 1 && x <= N);
    }

    // 모두 탈출했는지 확인
    static boolean isEveryoneEscape() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (maze[i][j] != -11 && maze[i][j] < 0) return false;
            }
        }
        return true;
    }

    static void printMap(int[][] arr) {
        for (int i = 1; i <= N; i++) {
            System.out.println();
            for (int j = 1; j <= N; j++) {
                System.out.print(arr[i][j] + " ");
            }
        }
        System.out.println();
    }

}