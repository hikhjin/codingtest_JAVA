package baekjun.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Blog_21921 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(slidingWindow(arr, x));
    }

    static StringBuilder slidingWindow(int[] arr, int x) {
        int max = 0;
        int sum = 0;
        int cnt = 1;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < x; i++) {
            sum += arr[i];
        }
        max = sum;

        for (int i = x; i < arr.length; i++) {
            sum += arr[i] - arr[i - x];
            if (sum > max) {
                max = sum;
                cnt = 1;
            } else if (sum == max) {
                cnt++;
            }
        }

        if (max == 0) sb.append("SAD");
        else sb.append(max).append("\n").append(cnt);

        return sb;
    }
}
