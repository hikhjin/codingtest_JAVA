package baekjun.gold;

import java.io.*;

public class NQueen_9663 {
    static boolean[] checkCol; // 가로 (열)
    static boolean[] checkDigL; // 왼쪽 아래에서 오른쪽 위로 가는 대각선
    static boolean[] checkDigR; // 왼쪽 위에서 오른쪽 아래로 가는 대각선
    static int N;
    static int queen;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        checkCol = new boolean[N];
        checkDigL = new boolean[N * 2 - 1];
        checkDigR = new boolean[N * 2 - 1];

        solve(0);
        System.out.println(queen);
    }

    static void solve(int cnt) {
        if (cnt == N) {
            queen++;
            return;
        }

        for (int i = 0; i < N; i++) {
            // 퀸을 놓을 수 없는 경우
            if (checkCol[i] || checkDigL[i + cnt] || checkDigR[i - cnt + N - 1]) { // 인덱스 범위 양수로 만들기 위해 N-1 더함
                continue;
            }

            // 현재 위치에 퀸을 놓고 체크 배열 갱신
            checkCol[i] = true;
            checkDigL[i + cnt] = true;
            checkDigR[i - cnt + N - 1] = true;

            // 다음 행으로 이동
            solve(cnt + 1);

            // 퀸을 놓았던 위치를 초기화
            checkCol[i] = false;
            checkDigL[i + cnt] = false;
            checkDigR[i - cnt + N - 1] = false;
        }
    }
}
