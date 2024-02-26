package baekjun.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class robot_14503 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 방 크기
        int M = Integer.parseInt(st.nextToken());

        int [][] arr = new int[N][M]; // 방 배열

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken()); // 로봇청소기 좌표
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken()); // 방향

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = 0;
        boolean working = true;
        while (working) {
            if (arr[r][c] == 0) { // 청소 되어 있지 않은 칸 -> 청소하기
                arr[r][c] = 10; // 청소된 상태 : 10
                ans += 1;
//                System.out.println("ans = " + ans);
//                System.out.println("r = " + r);
//                System.out.println("c = " + c);
//                System.out.println("d = " + d);
//                for (int i = 0; i < arr.length; i++) {
//                    int[] inArr = arr[i];
//                    for (int j = 0; j < inArr.length; j++) {
//                        System.out.print(inArr[j] + " ");
//                    }
//                    System.out.println();
//                }
            }
            if ((arr[r-1][c] == 0) || (arr[r][c-1] == 0)
                    || (arr[r+1][c] == 0) || (arr[r][c+1] == 0)) { // 주변 4칸 중 청소되지 않은 곳이 있는 경우
                switch (d) {
                    case 0:
                        if (arr[r][c-1] == 0) { // 반시계방향으로 90도 회전한 방향의 앞 칸이 청소가 되어있지 않을 경우
                            c -= 1; // 좌표 변경
                        }
                        d = 3; // 방향 변경
                        break;
                    case 1:
                        if (arr[r-1][c] == 0) {
                            r -= 1;
                        }
                        d = 0;
                        break;
                    case 2:
                        if (arr[r][c+1] == 0) {
                            c += 1;
                        }
                        d = 1;
                        break;
                    case 3:
                        if (arr[r+1][c] == 0) {
                            r += 1;
                        }
                        d = 2;
                        break;
                }
            } else { // 청소되지 않은 빈칸이 없을 경우
                switch (d) {
                    case 0:
                        if (arr[r+1][c] == 1) { // 후진 시 뒤쪽 칸이 벽일 경우
                            working = false; // 작동 멈춤
                        } else {
                            r += 1;
                        } break;
                    case 1:
                        if (arr[r][c-1] == 1) {
                            working = false;
                        } else {
                            c -= 1;
                        } break;
                    case 2:
                        if (arr[r-1][c] == 1) {
                            working = false;
                        } else {
                            r -= 1;
                        } break;
                    case 3:
                        if (arr[r][c+1] == 1) {
                            working = false;
                        } else {
                            c += 1;
                        } break;
                    }
            }
        }
        System.out.println(ans);
    }
}
