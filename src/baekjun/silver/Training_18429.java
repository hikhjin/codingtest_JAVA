package baekjun.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Training_18429 {
    static int N, K;
    static int cnt = 0;
    static int[] arr;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // N일 동안 N개
        K = Integer.parseInt(st.nextToken()); // 중량 감소
        arr = new int[N];
        visited = new boolean[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        backtrack(0, 500);
        System.out.println(cnt);
    }

    static void backtrack(int idx, int sum) {
        if (idx == N) {
            if (sum >= 500) {
                cnt++;
            }
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                if (sum + arr[i] - K >= 500) {
                    backtrack(idx + 1, sum + arr[i] - K);
                }
                visited[i] = false;
            }
        }
    }
}
