package SWEA.D_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Subsequence_2817 {
    static int cnt = 0;
    static int N;
    static int K;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for (int i = 1; i <= T; i++) {
            sb.append("#").append(i).append(" ");
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            arr = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }
            dfs(0,0);
            sb.append(cnt);
            cnt = 0;
            if(i != T) sb.append("\n");
        }
        System.out.println(sb);
    }

    static void dfs(int sum, int now) {
        if (sum == K) {
            cnt++;
            return;
        }
        if (now == N) return;

        dfs(sum + arr[now], now + 1);
        dfs(sum, now + 1);

    }
}
