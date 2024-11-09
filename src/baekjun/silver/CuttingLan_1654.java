package baekjun.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CuttingLan_1654 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int[] lines = new int[K];
        long max = 0;

        for (int i = 0; i < K; i++) {
            lines[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, lines[i]);
        }

        max++;
        long min = 0;
        long mid = 0;

        while (min <= max) {
            mid = (max + min) / 2;

            long count = 0;

            for (int i = 0; i < K; i++) {
                count += lines[i] / mid;
            }

            if (count < N) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        System.out.println(min - 1);

    }
}
