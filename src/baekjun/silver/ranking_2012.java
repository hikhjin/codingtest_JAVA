package baekjun.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ranking_2012 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 사람 수
        int[] arr = new int[N]; // 예상 등수 배열
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);
        long ans = 0;

        for (int i = 1; i <= N; i++) {
            ans += Math.abs(arr[i-1] - i); // 예상 등수 절댓값 계산
        }

        System.out.println(ans);

    }
}
