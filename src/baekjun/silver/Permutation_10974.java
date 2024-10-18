package baekjun.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Permutation_10974 {
    static int N;
    static boolean[] visited;
    static StringBuilder ans = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        visited = new boolean[N+1];
        permutation(0, new StringBuilder());
        System.out.println(ans);
    }

    static void permutation(int index, StringBuilder sb) {
        if (index == N) {
            sb.delete(sb.length()-1, sb.length()); // 공백 제거
            ans.append(sb).append('\n');
            return;
        }

        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                int len = sb.length();
                sb.append(i);
                permutation(index + 1, sb.append(" "));
                sb.delete(len, sb.length());
                visited[i] = false;
            }
        }
    }
}
