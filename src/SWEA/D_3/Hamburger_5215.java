package SWEA.D_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Hamburger_5215 {
    static int maxScore;
    static int limitCal;
    static int N;
    static int[][] hamburger;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {
            sb.append("#").append(i).append(" ");
            st = new StringTokenizer(br.readLine());

            maxScore = 0;
            N = Integer.parseInt(st.nextToken()); // 음식 수
            limitCal = Integer.parseInt(st.nextToken()); // 제한 칼로리
            hamburger = new int[N][N];
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                hamburger[j][0] = Integer.parseInt(st.nextToken()); // 점수
                hamburger[j][1] = Integer.parseInt(st.nextToken()); // 칼로리
            }

            dfs(0, 0, 0);
            sb.append(maxScore);
            if (i != T) sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    static void dfs(int score, int calSum, int idx) {
        if (calSum > limitCal) return;
        if (idx == N) {
            if (calSum <= limitCal) {
                maxScore = Math.max(maxScore, score);
                return;
            }
        };

        dfs(score+hamburger[idx][0], calSum+hamburger[idx][1], idx+1);
        dfs(score, calSum, idx+1);
    }
}
