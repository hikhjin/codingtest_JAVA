package baekjun.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CuttingTrees_2805 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 나무의 수
        int M = Integer.parseInt(st.nextToken()); // 잘라야 할 총 길이
        int[] trees = new int[N];
        long max = 0;
        long min = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, trees[i]);
        }

        long mid = 0;

        while (min <= max) {
            long len = 0;
            mid = (min + max) / 2;

            for (int i = 0; i < trees.length; i++) {
                if (trees[i] >= mid) {
                    len += trees[i] - mid;
                }
            }
            if (len <= M) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        System.out.println(min);

    }
}
