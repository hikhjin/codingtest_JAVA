package SWEA.D_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Farm_2805 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {
            sb.append("#").append(i).append(" ");
            int N = Integer.parseInt(br.readLine());
            if (N == 1) {
                sb.append(Integer.parseInt(br.readLine()));
                if (i != T) sb.append("\n");
                continue;
            }

            int[][] arr = new int[N][N];
            for (int j = 0; j < N; j++) {
                String s = br.readLine();
                for (int k = 0; k < N; k++) {
                    arr[j][k] = s.charAt(k) - '0';
                }
            }

            int sum = 0;
            int mid = N/2;
            for (int j = 0; j <= mid; j++) {
                for (int k = mid - j; k <= mid + j; k++) {
                    if (j != mid) {
                        sum += arr[N-j-1][k];
                    }
                    sum += arr[j][k];
                }
            }
            sb.append(sum);
            if (i != T) sb.append("\n");
        }
        System.out.println(sb);
    }
}
