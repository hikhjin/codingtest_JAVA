package SWEA.D_3;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SecretCode_1230 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int T = 1; T <= 10; T++) {
            int N = Integer.parseInt(br.readLine());
            List<Integer> list = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                list.add(Integer.parseInt(st.nextToken()));
            }

            int M = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                char c = st.nextToken().charAt(0);
                switch (c) {
                    case 'I':
                        int x = Integer.parseInt(st.nextToken());
                        int y = Integer.parseInt(st.nextToken());
                        for (int j = 0; j < y; j++) {
                            int tmp = Integer.parseInt(st.nextToken());
                            list.add(x+1, tmp);
                            x++;
                        }
                        break;
                    case 'D':
                        int x2 = Integer.parseInt(st.nextToken());
                        int y2 = Integer.parseInt(st.nextToken());
                        for (int j = 0; j < y2; j++) {
                            list.remove(x2+1);
                        }
                        break;
                    case 'A':
                        int y3 = Integer.parseInt(st.nextToken());
                        for (int j = 0; j < y3; j++) {
                            int tmp = Integer.parseInt(st.nextToken());
                            list.add(tmp);
                        }
                        break;
                }
            }

            System.out.print("#" + T + " ");
            for (int i = 1; i <= 10; i++) {
                System.out.print(list.get(i));
                if (i != 10) System.out.print(" ");
            }
            if (T != 10) System.out.println();
        }
    }
}
