package softeer.lv2;
import java.util.*;
import java.io.*;

public class SizeOfBriquet {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        int ans = 1;

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 2; i <= 100; i++) {
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                if (arr[j] % i == 0) {
                    cnt++;
                }
            }
            // System.out.println(cnt);
            if (ans < cnt) {
                ans = cnt;
            }
        }

        System.out.println(ans);
    }
}
