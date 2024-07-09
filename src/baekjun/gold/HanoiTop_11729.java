package baekjun.gold;

import java.io.*;

public class HanoiTop_11729 {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        System.out.println((int)(Math.pow(2, N) - 1));
        solve(N, 1, 2, 3);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void solve(int N, int start, int mid, int end) {
        if (N == 1) {
            sb.append(start).append(" ").append(end).append("\n");
            return;
        }
        solve(N-1, start, end, mid);
        sb.append(start).append(" ").append(end).append("\n");
        solve(N-1, mid, start, end);
    }
}
