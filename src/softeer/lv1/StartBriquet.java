package softeer.lv1;

import java.io.*;
import java.util.*;

public class StartBriquet {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int ans = 1;

        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken()); // 처음엔 먼저 넣어줌
        int tmp1 = Integer.parseInt(st.nextToken());

        int dist = tmp1 - a;

        for (int i = 1; i < n-1; i++) {
            int tmp2 = Integer.parseInt(st.nextToken());
            int sub = tmp2 - tmp1;

            if (sub < dist) {
                dist = sub;
                ans = 1; // 초기화
            } else if (sub == dist) {
                ans++;
            }
            tmp1 = tmp2;
        }
        System.out.println(ans);

    }
}
