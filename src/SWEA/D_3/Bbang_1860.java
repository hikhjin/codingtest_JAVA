package SWEA.D_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Bbang_1860 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            sb.append("#" + t + " ");
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // 손님 수
            int M = Integer.parseInt(st.nextToken()); // M초에
            int K = Integer.parseInt(st.nextToken()); // 붕어빵 K개

            st = new StringTokenizer(br.readLine());
            int[] arr = new int[N];
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(arr);

            boolean flag = true;
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                cnt = (arr[i] / M) * K - i;
                if (cnt <= 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) sb.append("Possible");
            else sb.append("Impossible");
            if (t != T) sb.append("\n");
        }
        System.out.println(sb);
    }
}
