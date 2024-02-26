package baekjun.bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Alphabet_10809 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] arr = br.readLine().toCharArray();
        int[] ans = new int[26];

        for (int i = 0; i < ans.length; i++) {
            ans[i] = -1; // -1로 초기화
        }

        for (int i = 0; i < arr.length; i++) {
            if (ans[arr[i]-97] == -1) { // 처음 나오는 알파벳만
                ans[arr[i]-97] = i;
            }
        }
        for (int an : ans) {
            System.out.print(an + " ");
        }
    }
}
