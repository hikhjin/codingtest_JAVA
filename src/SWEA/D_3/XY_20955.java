package SWEA.D_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class XY_20955 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) {
            if (i != 1) sb.append("\n");
            sb.append("#").append(i);

            String S = br.readLine();
            String E = br.readLine();
            boolean flag = false;
            while (S.length() <= E.length()) {
                if (S.equals(E)) {
                    flag = true;
                    break;
                }
                if (E.charAt(E.length() - 1) == 'X') {
                    E = E.substring(0, E.length() - 1);
                } else {
                    StringBuilder tmp = new StringBuilder(E.substring(0, E.length() - 1));
                    E = tmp.reverse().toString();
                }
            }

            if (flag) {
                sb.append(" Yes");
            } else sb.append(" No");
        }

        System.out.println(sb);
    }
}
