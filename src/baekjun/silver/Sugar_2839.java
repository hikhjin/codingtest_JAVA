package baekjun.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Sugar_2839 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N+1];
        Arrays.fill(dp, 9999); // Integer.MAX_VALUE 하면 +1 할 때 오버플로우 발생

        if (N < 5) {
            if (N == 3) {
                System.out.println(1);
            } else {
                System.out.println(-1);
            }
            return;
        }

        dp[3] = 1;
        dp[5] = 1;

        for (int i = 6; i <= N; i++) {
            dp[i] = Math.min(dp[i-3]+1, dp[i-5]+1);
        }
        if (dp[N] >= 9999) System.out.println(-1);
        else System.out.println(dp[N]);
    }
}
