package SWEA.D_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Price2_1244 {
    static String num;
    static int change;
    static int[] arr;
    static int ans = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            sb.append("#").append(t).append(" ");
            st = new StringTokenizer(br.readLine());
            num = st.nextToken();
            change = Integer.parseInt(st.nextToken());
            if (change > num.length()) change = num.length();
            arr = new int[num.length()];
            for (int i = 0; i < num.length(); i++) {
                arr[i] = num.charAt(i) - '0';
            }

            dfs(0);
            sb.append(ans);
            ans = 0;
            if (t != T) sb.append("\n");
        }
        System.out.println(sb);
    }

    static void dfs(int cnt) {
        if (cnt == change) {
            String tmp = "";
            for (int i = 0; i < num.length(); i++) {
                tmp += arr[i];
            }
            ans = Math.max(ans, Integer.parseInt(tmp));
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                swap(i, j);
                dfs(cnt + 1);
                swap(i, j);
            }
        }
    }

    static void swap(int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
