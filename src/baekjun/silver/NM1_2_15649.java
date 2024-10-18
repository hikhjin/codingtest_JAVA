package baekjun.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class NM1_2_15649 {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 1부터 n까지
        int M = Integer.parseInt(st.nextToken()); // 중복 없이 M개 고른 수열
        int[] arr = new int[M + 1]; // 수열 저장할 배열
        boolean[] check = new boolean[N + 1]; // 사용한 수 확인하는 배열

        backtrack(N, M, 0, arr, check);

        System.out.println(sb);

    }

    static void backtrack(int N, int M, int idx, int[] arr, boolean[] check) {
        if (idx == M) {
            for (int i = 0; i < M; i++) {
                sb.append(arr[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 1; i <= N; i++) {
            if (!check[i]) {
                check[i] = true;
                arr[idx] = i;
                backtrack(N, M, idx + 1, arr, check);
                check[i] = false;
            }
        }
    }
}
