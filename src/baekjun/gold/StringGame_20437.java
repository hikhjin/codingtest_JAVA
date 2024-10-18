package baekjun.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StringGame_20437 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수

        for (int i = 0; i < T; i++) {
            String s = br.readLine();
            int K = Integer.parseInt(br.readLine());
            if (K == 1) {
                sb.append("1 1\n");
                continue;
            }

            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            int[] alpha = new int[26];
            for (int j = 0; j < s.length(); j++) {
                alpha[s.charAt(j) - 'a']++;
            }

            for (int j = 0; j < s.length(); j++) {
                if (alpha[s.charAt(j) - 'a'] < K) continue;
                int cnt = 1;

                for (int k = j+1; k < s.length(); k++) {
                    if (s.charAt(k) == s.charAt(j)) {
                        cnt++;
                    }
                    if (cnt == K) {
                        int len = k - j + 1;
                        min = Math.min(min, len);
                        max = Math.max(max, len);
                        break;
                    }
                }
            }
            if (min == Integer.MAX_VALUE || max == Integer.MIN_VALUE) sb.append(-1).append("\n");
            else sb.append(min).append(" ").append(max).append("\n");
        }
        System.out.println(sb);
    }
}
