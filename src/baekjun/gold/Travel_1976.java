package baekjun.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Travel_1976 {
    static int[] node;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine()); // 전체 도시 수
        int M = Integer.parseInt(br.readLine()); // 계획된 도시 수
        node = new int[N+1];
        int[] travel = new int[M+1];

        for (int i = 1; i <= N; i++) {
            node[i] = i;
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                if (Integer.parseInt(st.nextToken()) == 1) union(i, j);
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++) {
            travel[i] = Integer.parseInt(st.nextToken());
        }

        int pn = find(travel[1]);
        for (int i = 2; i <= M; i++) {
            if (pn != find(travel[i])) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            node[b] = a;
        }
    }

    static int find(int a) {
        if (node[a] == a) {
            return a;
        } else return find(node[a]);
    }
}
