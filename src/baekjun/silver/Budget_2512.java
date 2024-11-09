package baekjun.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Budget_2512 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        long max = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, arr[i]);
        }
        long M = Integer.parseInt(br.readLine());

        long min = 0;
        long mid = 0;

        while (min <= max) {
            long sum = 0;
            mid = (max + min) / 2;

            for (int i = 0; i < N; i++) {
                if (arr[i] <= mid) {
                    sum += arr[i];
                } else {
                    sum += mid;
                }
            }

            if (sum <= M) {
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }
        System.out.println(max);
    }
}
