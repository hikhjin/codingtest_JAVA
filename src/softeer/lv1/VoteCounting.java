package softeer.lv1;

import java.io.*;
import java.util.*;

public class VoteCounting {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb;
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine());
            sb = new StringBuilder();

            if (n < 5) {
                for (int j = 0; j < n; j++) {
                    sb.append(" |");
                }
            } else {
                int a = n / 5; // 몫
                int b = n % 5; // 나머지
                for (int j = 0; j < a; j++) {
                    sb.append(" ++++");
                }
                if (b > 0) {
                    sb.append(" ");
                }

                for (int j = 0; j < b; j++) {
                    sb.append("|");
                }
            }
            if (i < T-1) {
                sb.append("\n");
            }
            System.out.println(sb.toString().trim());
        }
    }
}

