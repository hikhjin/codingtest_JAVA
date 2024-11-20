package SWEA.D_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Hamburger2_5215 {
    static int N;
    static int limitCal;
    static int[][] arr;
    static int maxScore = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            sb.append("#" + t + " ");
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            limitCal = Integer.parseInt(st.nextToken());
            arr = new int[N][2];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                arr[i][0] = Integer.parseInt(st.nextToken());
                arr[i][1] = Integer.parseInt(st.nextToken());
            }
            dfs(0, 0, 0);
            sb.append(maxScore);
            maxScore = 0;
            if (t != T) sb.append("\n");
        }
        System.out.println(sb);
    }

    static void dfs(int sumCal, int idx, int score) {
        if (sumCal > limitCal) return;
        if (idx == N) {
            maxScore = Math.max(maxScore, score);
            return;
        }

        dfs(sumCal, idx + 1, score);
        dfs(sumCal + arr[idx][1], idx + 1, score + arr[idx][0]);
    }
}
