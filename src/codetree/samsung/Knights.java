package codetree.samsung;

import java.util.*;
import java.io.*;

public class Knights {
    static int L, N, Q;
    static int[][] dirs = {{-1,0}, {0,1}, {1,0}, {0,-1}}; // 위쪽 오른쪽 아래쪽 왼쪽
    static int[][] map; // 함정과 벽 정보
    static int[][] knightMap; // 기사 위치 정보
    static int damage = 0;
    static List<Knight> list = new ArrayList<>(); // 기사 정보 저장할 리스트
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken()); // L * L 크기 체스판
        N = Integer.parseInt(st.nextToken()); // 기사 수
        Q = Integer.parseInt(st.nextToken()); // 왕 명령 수
        map = new int[L+1][L+1];
        knightMap = new int[L+1][L+1];
        for (int i = 1; i <= L; i++) { // 맵 정보 저장
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= L; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println();
        System.out.println("함정, 벽");
        printMap(map);

        list.add(new Knight(0, 0, 0, 0, 0, 0, false)); // 인덱스 맞추기 위한 가짜 데이터
        for (int i = 1; i <= N; i++) { // 초기 기사 정보 저장
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()); // y좌표
            int c = Integer.parseInt(st.nextToken()); // x좌표
            int h = Integer.parseInt(st.nextToken()); // 세로길이 y
            int w = Integer.parseInt(st.nextToken()); // 가로길이 x
            int k = Integer.parseInt(st.nextToken()); // 초기 체력
            list.add(new Knight(i, r, c, h, w, k, true));

            for (int j = r; j < r+h; j++) {
                for (int p = c; p < c+w; p++) {
                    knightMap[j][p] = i; // 기사들은 인덱스로 맵에 저장
                }
            }
        }
        System.out.println("초기 기사 위치");
        printMap(knightMap);

        for (int i = 1; i <= Q; i++) { // Q번 명령 수행
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken()); // 수행할 기사 idx
            int d = Integer.parseInt(st.nextToken()); // 방향

            System.out.println("수행할 기사: " + idx);
            printKnights();
            if (!list.get(idx).canMove) continue; // 생존하지 않은 기사일 경우 넘어감

            move(idx, d); // 기사 이동
            System.out.println(i + "번째 턴 수행 이후");
            printKnights();
            printMap(knightMap);
        }

        System.out.println(damage);
    }

    static void move(int idx, int d) {
        int[] go = dirs[d];

        Set<Integer> set = canMoveKnights(go, idx); // 모든 기사 밀 수 있는지 확인
        System.out.println("set출력");
        for (Integer i : set) {
            System.out.println(i);
        }

        if (set.size() > 0) { // 모든 기사 밀 수 있는 경우, 실제로 모든 기사 위치 이동
            moveKnights(go, set);
            refreshMap(idx, set); // map에 이동한 기사 위치 반영, 소비 체력 계산
        }
    }

    static void moveKnights(int[] go, Set<Integer> set) { // 기사 위치 이동
        for (int i : set) {
            Knight k = list.get(i);
            k.y += go[0];
            k.x += go[1];

        }
    }

    static Set<Integer> canMoveKnights(int[] go, int idx) { // 이동 위치에 있는 모든 칸 탐색하며 모든 기사가 움직일 수 있는 확인
        int sy = list.get(idx).y; // 탐색 시작할 칸 위치
        int sx = list.get(idx).x;
        int h = list.get(idx).h;
        int w = list.get(idx).w;
        System.out.println("탐색 시작 위치: " + sy + " " + sx);
        System.out.println("방향: " + go[0] + " " + go[1]);
        Set<Integer> set = new HashSet<>(); // 움직여야 하는 기사 담는 셋
        set.add(idx);
        for (int tmpy = sy; tmpy < sy+h; tmpy++) {
            for (int tmpx = sx; tmpx < sx+w; tmpx++) {
                int y = tmpy + go[0];
                int x = tmpx + go[1];

                if (!isRange(y, x)) return new HashSet<>();

                int nowIdx = knightMap[y][x]; // 현재 칸 기사 인덱스
                System.out.println("y = " + y + " x = " + x);
            System.out.println("nowIdx: " + nowIdx);
                if (nowIdx != 0 && !set.contains(nowIdx)) {
//                System.out.println("y = " + y + " x = " + x);
                    int nowH = list.get(nowIdx).h;
                    int nowW = list.get(nowIdx).w;
                    set.add(nowIdx);
                    System.out.println("nowIdx: " + nowIdx);
                    if (!isRangeKnight(y+go[0], x+go[1], nowH, nowW)) { // 움직였을 때 범위 밖으로 벗어나는 경우
                        return new HashSet<>();
                    }
                }
            }
        }
        return set;
    }


    static boolean isRange(int y, int x) { // 해당 좌표가 체스판 안 인지 체크
        return (y >= 1 && y <= L && x >= 1 && x <= L);
    }

    static boolean isRangeKnight(int y, int x, int h, int w) { // 기사가 체스판 밖이 아니고, 벽이 아닌지 체크
        for (int j = y; j < y+h; j++) {
            for (int p = x; p < x+w; p++) {
                System.out.println("체크 j= " + j + " p= " + p);
                if (j < 1 || j > L || p < 1 || p > L) return false;
                if (map[j][p] == 2) return false;
            }
        }
        return true;
    }

    static void refreshMap(int notCal, Set<Integer> moveSet) { // 기사 맵 업데이트, 체력 계산
        for (int n : moveSet) {
            System.out.println("moveSet: "+ n);
        }
        knightMap = new int[L+1][L+1];
        Set<Integer> Dset = new HashSet<>(); // 사라진 기사 저장
        for (Knight k : list) {
            if (!k.canMove) continue; // 사라진 기사는 넘어감
            int y = k.y;
            int x = k.x;
            int idx = k.idx;
            int h = k.h;
            int w = k.w;

            int nowD = 0;
            for (int i = y; i < y+h; i++) {
                for (int j = x; j < x+w; j++) {
                    knightMap[i][j] = idx; // 기사들은 인덱스로 맵에 저장
                    if ((idx != notCal) && (map[i][j] == 1) && moveSet.contains(idx)) { // 만약 움직인 위치에 함정이 있는 경우 체력 -1
                        k.k -= 1;
                        nowD += 1;
                        if (k.k == 0) {
                            Dset.add(idx);
                        }
                    }
                }
            }
            System.out.println("기사 idx: " + idx + " 현재 데미지: " + nowD);

            if (Dset.contains(idx)) {
                k.canMove = false;
                for (int i = y; i < y+h; i++) { // 사라진 기사 맵에서 삭제
                    for (int j = x; j < x+w; j++) {
                        knightMap[i][j] = 0;
                    }
                }
            } else damage += nowD;
        }
    }

    static void printMap(int[][] arr) {
        for (int i = 1; i <= L; i++) {
            System.out.println();
            for (int j = 1; j <= L; j++) {
                System.out.print(arr[i][j] + " ");
            }
        }
        System.out.println();
    }

    static void printKnights() {
        for (Knight k : list) {
            if (k.idx == 0) continue;
            System.out.println("idx = " + k.idx + ", y = " + k.y + ",x = " + k.x + ", k = " + k.k + ", canMove = " + k.canMove);
        }
        System.out.println();
    }
}

class Knight {
    int idx, y, x, h, w, k;
    boolean canMove;

    public Knight(int idx, int y, int x, int h, int w, int k, boolean canMove) {
        this.idx = idx;
        this.y = y;
        this.x = x;
        this.h = h;
        this.w = w;
        this.k = k;
        this.canMove = canMove;
    }
}