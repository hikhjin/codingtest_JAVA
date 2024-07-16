package baekjun.silver;

import java.io.*;
import java.util.StringTokenizer;

public class NM2_15650 {
    static int[] arr = new int[10];
    static boolean[] check = new boolean[10];
    static StringBuilder sb = new StringBuilder();
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        solve(0, 1);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void solve(int pos, int start) {
        if (pos == M) {
            for (int i = 0; i < M; i++) {
                sb.append(arr[i] + " ");
            }
            sb.append("\n");
            return;
        }

        for (int i = start; i <= N; i++) {
            if (!check[i]) {
                arr[pos] = i;
                check[i] = true;
                solve(pos + 1, i + 1);
                check[i] = false;
            }
        }
    }
}
