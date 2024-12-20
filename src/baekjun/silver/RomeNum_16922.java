package baekjun.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class RomeNum_16922 {
    static int N;
    static int[] rome = {1, 5, 10, 50};
    static Set<Integer> set = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        dfs(0, 0, 0);

        System.out.println(set.size());
    }

    static void dfs(int idx, int start, int sum) {
        if (idx == N) {
            set.add(sum);
            return;
        }

        for (int i = start; i < 4; i++) {
            dfs(idx + 1, i, sum + rome[i]);
        }
    }
}
