package SWEA.D_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Price_1244 {
    static int[] prices;
    static int C;
    static int max = -9999;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String N = st.nextToken();
            C = Integer.parseInt(st.nextToken());
            prices = new int[N.length()];

            if (C > N.length()) C = N.length();

            for (int j = 0; j < N.length(); j++) {
                prices[j] = N.charAt(j) - '0';
            }

            dfs(0, 0);
            System.out.println("#" + i + " " + max);
            max = 0;
        }
    }

    static void dfs(int start, int idx) {
        if (idx == C) {
            String res = "";
            for (int i = 0; i < prices.length; i++) {
                res += prices[i];
            }
            max = Math.max(max, Integer.parseInt(res));
            return;
        }

        for (int i = start; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                swap(i, j);
                dfs(i, idx + 1);
                swap(i, j);
            }
        }
    }

    static void swap(int i, int j) {
        int temp = prices[i];
        prices[i] = prices[j];
        prices[j] = temp;
    }
}
