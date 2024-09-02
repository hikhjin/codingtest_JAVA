package baekjun.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sum_10986 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        long[] dp = new long[N + 1];
        long[] cnt = new long[M]; // 각 나머지 개수 저장
        long ans = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            dp[i] = (Integer.parseInt(st.nextToken()) + dp[i-1]) % M;

            if (dp[i] == 0) ans++;

            cnt[(int)dp[i]]++;
        }

        for (int i = 0; i < M; i++) {
            if (cnt[i] != 0) {
                ans += ((cnt[i] * (cnt[i]-1)) / 2);
            }
        }
        System.out.println(ans);
    }
}
