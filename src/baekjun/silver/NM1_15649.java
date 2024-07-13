package baekjun.silver;

import java.io.*;
import java.util.StringTokenizer;

public class NM1_15649 {
    static int[] arr = new int[10]; //
    static boolean[] check = new boolean[10]; // 사용한 수 확인하는 배열
    static StringBuilder sb  = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        solve(N, M, 0);

        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

    static void solve(int N, int M, int K) {
        if (K == M) {
           for (int i = 0; i < M; i++) {
               sb.append(arr[i] + " ");
           }
           sb.append("\n");
           return;
        }

        for (int i = 1; i <= N; i++) {
            if (!check[i]) {
                arr[K] = i;
                check[i] = true;
                solve(N, M, K + 1);
                check[i] = false;
            }
        }
    }
}
