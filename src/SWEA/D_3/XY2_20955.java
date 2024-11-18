package SWEA.D_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class XY2_20955 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= T; t++) {
            sb.append("#").append(t).append(" ");
            String s = br.readLine();
            String e = br.readLine();
            boolean flag = false;

            while (s.length() <= e.length()) {
                if (s.length() == e.length()) {
                    if (s.equals(e)) {
                        flag = true;
                        break;
                    }
                }

                if (e.charAt(e.length() - 1) == 'X') {
                    e = e.substring(0, e.length() - 1);
                } else {
                    e = e.substring(0, e.length() - 1);
                    StringBuilder tmp = new StringBuilder(e);
                    e = tmp.reverse().toString();
                }
            }
            if (flag) sb.append("Yes");
            else sb.append("No");

            if (t != T) sb.append("\n");
        }
        System.out.println(sb);
    }
}
