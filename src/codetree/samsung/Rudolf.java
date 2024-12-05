package codetree.samsung;

import java.util.*;
import java.io.*;

public class Rudolf {
    static int N, M, P, C, D, rx, ry;
    static int[][] map;
    static List<Santa> list = new ArrayList<>(); // 인덱스 순서대로 산타 위치 저장
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken()); // 게임판 크기
        M = Integer.parseInt(st.nextToken()); // 게임 턴 수
        P = Integer.parseInt(st.nextToken()); // 산타 수
        C = Integer.parseInt(st.nextToken()); // 루돌프 힘
        D = Integer.parseInt(st.nextToken()); // 산타 힘
        map = new int[N+1][N+1]; // 게임판

        st = new StringTokenizer(br.readLine());
        ry = Integer.parseInt(st.nextToken());
        rx = Integer.parseInt(st.nextToken());
        map[ry][rx] = -1; // 루돌프 위치 -1로 저장

        list.add(new Santa(0, 0,0,0,-2)); // 인덱스 맞추기용 데이터

        for (int i = 0; i < P; i++) { // 게임판에 산타 인덱스로 각 산타 위치 저장
            st = new StringTokenizer(br.readLine());
            int pi = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            map[y][x] = pi; // 산타 위치 저장
            list.add(new Santa(pi, y, x, 0, -2));
        }

        Collections.sort(list);

        for (int turn = 1; turn <= M; turn++) { // M번 진행
            System.out.println(turn + "번째턴");
            moveRudolf(turn); // 루돌프 움직이기

            refreshMap(); //맵 업데이트
            printMap();

            //2. 산타 움직임
            for (int s = 1; s <= P; s++) { // 순서대로
                int y = list.get(s).y;
                int x = list.get(s).x;
                int canMove = list.get(s).canMove; // 기절한 턴
                if (isRange(y, x)) { // 탈락하지 않은 산타 체크
                    if (canMove == -2 || turn-canMove == 2) {
                        list.get(s).canMove = -2;
                        moveSanta(s, turn); // 현재 턴 이동 금지가 아닐 경우
                    }

                    refreshMap();
                }
            }

            // 산타가 맵에 남아있는지 확인
            int sCnt = 0;
            for (int s = 1; s <= P; s++) {
                Santa tmp = list.get(s);
                if (isRange(tmp.y, tmp.x)) {
                    sCnt++;
                    tmp.score += 1;
                }
//                System.out.println("idx: " + tmp.idx + " score: " + tmp.score);
            }

            if (sCnt == 0) { // 맵에 산타가 없으면 즉시 종료
                break;
            }
        }
        // 점수 출력
        Collections.sort(list);
        for (int s = 1; s <= P; s++) {
            Santa tmp = list.get(s);
            sb.append(tmp.score + " ");
        }
        System.out.println(sb);
    }

    static void moving(int[] dir, int idx, int who) { // 부딪혀서 이동
        int y = list.get(idx).y; // 시작 y
        int x = list.get(idx).x; // 시작 x
        if (who == 0) {
            who = C; // 루돌프가 부딪혔을 경우
        } else who = D;
        System.out.println("튕기기 시작 y: " + y);
        System.out.println("튕기기 시작 x: " + x);
        if (!isRange(y + dir[0]*who, x+ dir[1]*who)) {
            santaOut(idx); // 범위 벗어나면 탈락처리
            return;
        }
        y += dir[0] * who;
        x += dir[1] * who;
        list.get(idx).y = y;
        list.get(idx).x = x;

        while (true) { // 밀려나기
            if (map[y][x] != 0) { // 움직인 칸에 산타가 있을 경우
                int nowIdx = map[y][x]; // 부딪힌 산타 idx
                if (isRange(y+dir[0], x+dir[1])) {
                    list.get(nowIdx).y += dir[0];
                    list.get(nowIdx).x += dir[1];
                    y += dir[0];
                    x += dir[1];
                } else {
                    santaOut(nowIdx); // 범위 밖이면 산타 탈락
                    return;
                }
            } else return;
        }
    }

    static void moveSanta(int idx, int turn) {
        int y = list.get(idx).y; // 기준 y
        int x = list.get(idx).x; // 기준 x
        int startDist = calDist(ry, rx, y, x); // 현재 산타 위치와 루돌프 간의 거리
        int endY = y;
        int endX = x;
        int dist = startDist; // 움직이지 않았을 때(현재) 기준 거리로 초기화
        int[][] dirs = {{-1,0}, {0,1}, {1,0}, {0,-1}}; // 상 우 하 좌 순서
        int[] endDir = new int[2];

        for (int[] dir : dirs) {
            endY = y + dir[0];
            endX = x + dir[1];

            if (isRange(endY, endX) && (map[endY][endX] == 0 || map[endY][endX] == -1)) { // 범위 내에 있고 해당 칸에 아무도 없거나 루돌프면
                int tmpDist = calDist(ry, rx, endY, endX);
                if (tmpDist < dist) {
                    dist = tmpDist;
                    list.get(idx).y = endY;
                    list.get(idx).x = endX;
                    endDir[0] = dir[0];
                    endDir[1] = dir[1];
                }
            }
        }

        if (dist != startDist) { // 움직였을 경우
            if (map[list.get(idx).y][list.get(idx).x] == -1) { // 도착한 칸에 루돌프가 있는 경우
                list.get(idx).score += D;
                list.get(idx).canMove = turn;
                map[list.get(idx).y-endDir[0]][list.get(idx).x-endDir[1]] = 0;
                moving(otherDir(endDir), idx, 1); // 반대 방향으로 밀려나기
            }
        }
    }

    static int[] otherDir (int[] dir) {
        if (dir[0] == -1 && dir[1] == 0) return new int[]{1,0};
        if (dir[0] == 0 && dir[1] == 1) return new int[]{0,-1};
        if (dir[0] == 1 && dir[1] == 0) return new int[]{-1,0};
        else return new int[]{0,1}; //(dir[0] == 0 && dir[1] == -1)
    }

    static void moveRudolf(int turn) {
        int santaX = 0;
        int santaY = 0;
        int dist = 99999999;

        for (int y = N; y >= 1; y--) { // y좌표 큰 산타부터
            for (int x = N; x >= 1; x--) { //x좌표 큰 산타부터
                if (map[y][x] == 0 || map[y][x] == -1) continue;
                int tmpDist = calDist(ry, rx, y, x);
                if (tmpDist < dist) { // 거리, y, x 업데이트
                    dist = tmpDist;
                    santaX = x;
                    santaY = y;
                }
            }
        }

        int[] go = whereToGoR(santaY, santaX); // 최종 산타 위치로 움직일 거리 찾기
        ry += go[0]; // 루돌프 움직이기
        rx += go[1];

        if (map[ry][rx] != 0) { // 산타와 루돌프가 충돌 했을 경우
            int sIdx = map[ry][rx]; // 충돌한 산타 인덱스
            list.get(sIdx).score += C; // 점수 얻기
            moving(go, sIdx, 0);
            list.get(sIdx).canMove = turn; // 기절 상태로 변경
        }
    }

    static void santaOut(int idx) { // 산타 탈락 처리
        map[list.get(idx).y][list.get(idx).x] = 0;
        list.get(idx).x = 0;
        list.get(idx).y = 0; // 맵 밖으로 빼기
    }

    static int[] whereToGoR(int sy, int sx) { // 루돌프가 어느 방향으로 가야하는지 움직여야하는 y,x 거리 리턴
        int[][] dirs = {{1,1}, {1,0}, {1,-1}, {0,1}, {0,-1}, {-1,1}, {-1,0}, {-1,-1}};
        int dist = 99999999;
        int[] go = new int[2];
        System.out.println("ry: " + ry + " rx: " + rx);

        for (int[] dir : dirs) {
            int y = ry + dir[0];
            int x = rx + dir[1];
            int tmpDist = calDist(y, x, sy, sx);
            if (tmpDist < dist) {
                dist = tmpDist;
                go[0] = dir[0]; // y
                go[1] = dir[1]; // x
            }
        }
        System.out.println("go: " + go[0] + ", " + go[1]);
        return go;
    }

    static int calDist(int y1, int x1, int y2, int x2) { // 산타와 루돌프 사이 거리 계산
        return (y2-y1)*(y2-y1) + (x2-x1)*(x2-x1);
    }

    static boolean isRange(int y, int x) { // 맵 범위 안에 있는지 확인
        return (y >= 1 && y <= N && x >= 1 && x <= N);
    }

    static void refreshMap() {
        map = new int[N+1][N+1];
        map[ry][rx] = -1; // 루돌프
        for (int i = 1; i <= P; i++) {
            Santa s = list.get(i);
            if (isRange(s.y, s.x)) { // 범위 내에 있는 산타만 기록
                map[s.y][s.x] = i;
            }
        }
    }

    static void printMap() {
        for (int i = 1; i < N+1; i++) {
            System.out.println();
            for (int j = 1; j < N+1; j++) {
                System.out.print(map[i][j] + " ");
            }
        }
        System.out.println();
    }
}

class Santa implements Comparable<Santa> {
    int idx, y, x, score, canMove;

    public Santa (int idx, int y, int x, int score, int canMove) {
        this.idx = idx;
        this.y = y;
        this.x = x;
        this.score = score;
        this.canMove = canMove;
    }

    @Override
    public int compareTo(Santa o) {
        return this.idx - o.idx;
    }
}