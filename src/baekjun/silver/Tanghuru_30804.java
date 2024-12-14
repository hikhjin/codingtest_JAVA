package baekjun.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Tanghuru_30804 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[N];
        int[] tmp = new int[10];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = 0;
        int max = 0;
        while (end < N) {
            tmp[arr[end]]++;
            while (cnt(tmp) > 2) {
                tmp[arr[start]]--;
                start++;
            }
            max = Math.max(max, end - start + 1);
            end++;
        }
        System.out.println(max);

    }

    static int cnt(int[] tmp) {
        int count = 0;
        for (int i = 0; i < tmp.length; i++) {
            if (tmp[i] > 0) {
                count++;
            }
        }
        return count;
    }
}
