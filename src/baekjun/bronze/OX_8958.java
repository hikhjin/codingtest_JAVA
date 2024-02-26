package baekjun.bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OX_8958 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            char[] arr = br.readLine().toCharArray();
            int ans = 0;
            int cnt = 0;
            for (int j = 0; j < arr.length; j++) {
                if (arr[j] == 'O') {
                    cnt++;
                } else {
                    cnt = 0;
                }
                ans += cnt;
            }
            System.out.println(ans);
        }

    }
}
